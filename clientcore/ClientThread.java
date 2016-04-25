package hello.clientcore;

import hello.common.TranObject;
import hello.common.TranObjectType;
import hello.test.ClientTest;

import java.net.Socket;

public class ClientThread extends Thread{
	private InputThread in;
	private OutputThread out;
	private boolean isStart;
	
	
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public ClientThread(Socket socket) {
		in = new InputThread(socket);
		out= new OutputThread(socket);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		in.setStart(true);
		out.setStart(true);
		in.start();
		out.start();
		while(isStart){
			if(in.isMessageLin()){
				TranObject message = in.getMessage();
				TranObjectType str ;
				if(message != null){
					switch(str=message.getType()){
					case REGISTER:
						System.out.println(message.getObject());
						ClientTest  clietTest = ChatMap.getChatMap("1");
						clietTest.showMessage(message);
						break;
					
						default:
							break;
					}
				}
			}
		}
		
	}

	public InputThread getIn() {
		return in;
	}

	public OutputThread getOut() {
		return out;
	}
	
	
}
