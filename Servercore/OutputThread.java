package hello.Servercore;

import hello.common.TranObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OutputThread extends Thread{
	private OutputThreadMap map;
	private ObjectOutputStream oos;
	private TranObject massage;
	private boolean isStart = true;
	private Socket socket;
	
	public OutputThread(Socket socket, OutputThreadMap map) {
		
		try {
			this.map = map;
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public void setMessage(TranObject massage){
		this.massage = massage;
		synchronized (this) {
			notify();
		}
	}
	@Override
	public void run() {
		
		try {
			while(isStart){
				synchronized (this) {
					wait();
				}
				if(massage != null){
					oos.writeObject(massage);
					oos.flush();
				}
			}
			if(oos != null){
				oos.close();
			}
			if(socket != null){
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
