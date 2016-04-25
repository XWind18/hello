package hello.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCUtils {
	
	
	public static String DRIVER;
	public static String URL;
	public static String USER_NAME;
	public static String PASSWORD;
	
	static {
		String path;
		try {
			path = JDBCUtils.class.getResource("").toURI().getPath();
			File file = new File(path+"jdbc.properties");
			InputStream is = new BufferedInputStream(
					new FileInputStream(file));
			Properties p = new Properties();
			p.load(is);
			
			DRIVER = p.getProperty("jdbc.driverClass");
			URL = p.getProperty("jdbc.URL");
			USER_NAME = p.getProperty("jdbc.userName");
			PASSWORD = p.getProperty("jdbc.password");
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JDBCUtils(){
		
	}
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getconnnection(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 关闭连接
	 * @param rs
	 * @param st
	 * @param con
	 */
	public static void close(ResultSet rs,Statement st,Connection con){
		try {
			try{
				if(rs!=null){
				rs.close();
				}
			}finally{
				try{
					if(st!=null){
						st.close();
					}				
				}finally{
					if(con!=null)
						con.close();		
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs){
		Statement st = null;
		Connection con = null;
		try {
			try {
				if(rs != null){
					st = rs.getStatement();
					rs.close();
				}
			} finally{
				try{
					if(st != null){
						con = st.getConnection();
						st.close();
					}
				}finally{
					if(con != null){
						con.close();
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 关闭连接
	 * @param st
	 * @param con
	 */
	public static void close(Statement st,Connection con){
		try {
			try{
				if(st!=null){
					st.close();
				}				
			}finally{
				if(con!=null)
					con.close();		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增、修改、删除
	 * @param sql
	 * @param args
	 * @return
	 */
	public static int update(String sql,Object ... args){
		int result = 0;
		Connection con = getconnnection();
		PreparedStatement ps = null; 
		try {
			ps = con.prepareStatement(sql);
			if(args != null){
				for (int i = 0; i < args.length; i++) {
					ps.setObject((i+1), args[i]);
				}
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(ps,con);
		}
		
		return result;
	}
	/**
	 * 查询，返回ResultSet，需手动关闭资源  不推荐使用
	 * @param sql
	 * @param args
	 * @return
	 */
	@Deprecated
	public static ResultSet query(String sql,Object ... args){
		ResultSet result = null;
		Connection con = getconnnection();
		PreparedStatement ps = null; 
		try {
			ps = con.prepareStatement(sql);
			if(args != null){
				for (int i = 0; i < args.length; i++) {
					ps.setObject((i+1), args[i]);
				}
			}
			result = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询单条记录，并且返回一个Map
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Map<String,Object> queryForMap(String sql,Object ... args){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = queryForList(sql, args);
		if(list.size() > 0){
			result = list.get(0);
		}
		return result;
	}
	
	/**
	 * 查询单条记录，并且返回一个对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public static <T> T queryForObject(String sql,Class<T> clz,Object ... args){
		T result = null;
		List<T> list =queryForList(sql, clz, args);
		if(list.size()>0){
			result = list.get(0);
		}
		return result;
	}
	
	/**
	 * 查询单条记录，并且返回多个Map
	 * @param sql
	 * @param args
	 * @return
	 */
	public static List<Map<String,Object>> queryForList(String sql,Object ... args){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = getconnnection();
			ps = con.prepareStatement(sql);
			if(args != null){
				for (int i = 0; i < args.length; i++) {
					ps.setObject((i+1), args[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for (int i = 1; i <= columnCount; i++) {
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));	
				}
				result.add(map);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, ps, con);
		}
		return result;
	}
	
	/**
	 * 查询单条记录，并且返回多个对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public static <T> List<T> queryForList(String sql,Class<T> clz,Object ... args){
		List<T> result = new ArrayList<T>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getconnnection();
			ps = con.prepareStatement(sql);
			if(args != null){
				for (int i = 0; i < args.length; i++) {
					ps.setObject((i+1), args[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				T obj = clz.newInstance();
				for (int i = 1; i <= columnCount ; i++) {					
					String columnName = rsmd.getColumnName(i);
					String methodName = "set"+columnName.substring(0,1).toUpperCase()+
							columnName.substring(1, columnName.length());
					Method method [] = clz.getMethods();
					for (Method meth : method) {
						if(methodName.equals(meth.getName())){
							if(rs.getObject(i) != null){
								meth.invoke(obj, rs.getObject(i));	
							}
						}
					}
				}
				result.add(obj);
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, ps, con);
		}
		
		return result;
	}
}
