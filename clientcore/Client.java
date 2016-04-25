package hello.clientcore;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	private Socket socket;
	private ClientThread clientthread;
	private String ip;
	private int port;
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public boolean start(){
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(ip,port),3000);
			if(socket.isConnected()){
				clientthread = new ClientThread(socket);
				clientthread.setStart(true);
				clientthread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public InputThread getInputThread(){
		return clientthread.getIn();
	}
	public OutputThread getOutputThread(){
		return clientthread.getOut();
	}
	public void setIstart (boolean isStart){
		clientthread.getIn().setStart(isStart);
		clientthread.getOut().setStart(isStart);
	}
}
