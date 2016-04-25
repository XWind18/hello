package hello.clientcore;

import hello.common.TranObject;
import hello.common.TranObjectType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class InputThread extends Thread{
	private Socket socket;
	private ObjectInputStream ois;
	private TranObject message;
	private boolean isStart;
	private MessageListener messageListener;
	private boolean messageLin = false;
	

	public boolean isMessageLin() {
		return messageLin;
	}


	public void setMessageLin(boolean messageLin) {
		this.messageLin = messageLin;
	}


	public InputThread(Socket socket) {
		this.socket = socket;
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

	
	public MessageListener getMessageListener() {
		return messageListener;
	}





	public TranObject getMessage() {
		TranObject message = this.message;
		messageLin = false;
		synchronized (this) {
			notify();
		}
		return message;
	}


	@Override
	public void run() {
		try {
			while(isStart){
				message = (TranObject)ois.readObject();
				messageLin = true;
				synchronized (this) {
					wait();
				}
			}
			
			ois.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
