package hello.dao;

import hello.entity.Member;
import hello.util.JDBCUtils;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

//import org.junit.Test;


public class MemberDao {
	public int delete(int id){
		String sql = "delete from member where id = ?";
		return JDBCUtils.update(sql, id);
	}
@Test
	public String query(){
		String result = "";
		String sql = "select * from member";
		List<Member> list = JDBCUtils.queryForList(sql, Member.class);
		Iterator<Member> it = list.iterator();
		while(it.hasNext()){
			result = result + it.next().toString()+ "\n";
		}
		return result;
	}
	
}
