package hello.clientcore;

import hello.common.TranObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OutputThread extends Thread{
	private Socket socket;
	private ObjectOutputStream oos;
	private TranObject message;
	private boolean isStart = true;
	
	public OutputThread(Socket socket) {
		this.socket = socket;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void setmessage(TranObject message) {
		this.message = message;
		synchronized (this) {
			notify();
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
				if(message != null){
					oos.writeObject(message);
					oos.flush();
				}
				synchronized (this) {
					wait();
				}
				
			}
			oos.close();
			if(socket != null){
				socket.close();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
