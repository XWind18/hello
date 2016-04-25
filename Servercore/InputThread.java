package hello.Servercore;

import hello.common.TranObject;
import hello.common.TranObjectType;
import hello.dao.MemberDao;
import hello.entity.Member;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;




public class InputThread extends Thread{
	private Socket socket ;
	private OutputThread out;
	private OutputThreadMap map;
	private ObjectInputStream ois;
	private boolean isStart = true;
	
	public InputThread(Socket socket, OutputThread out, OutputThreadMap map){
		this.socket = socket;
		this.out = out;
		this.map = map;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(isStart){
				readMessage();
			}
			if(ois != null){
				ois.close();
			}
			if(socket != null){
				socket.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	public void readMessage() throws Exception{
		TranObject readObject = (TranObject) ois.readObject();
		TranObjectType tType = readObject.getType();
		switch(tType){
		case REGISTER:
			
			String  send = new MemberDao().query();
			TranObject<String> object = new TranObject<String>();
			object.setObject(send);
			object.setType(TranObjectType.REGISTER);
			out.setMessage(object);
			break;
		case LOGIN:
			Member member = new Member();
			
			
			

			break;
		
			
		default:
			break;
		}
	}
	
	
}
