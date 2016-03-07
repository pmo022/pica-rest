package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TomcatServer {
	
	String server_directory;
	int port;
	
	public TomcatServer(){
		server_directory = System.getProperty("catalina.home");
		port = 8080;
	}
	
	public TomcatServer(String server_directory, int port){
		this.server_directory = server_directory;
		this.port = port;
		
	}
	
	public TomcatServer(String server_directory) {
		this.server_directory = server_directory;
	}
	
	public String getServerDirectory(){
		return server_directory;
	}
	
	public int getPort(){
		return port;
	}

	public void start(){
        try {
			Runtime.getRuntime().exec(server_directory+"\\bin\\startup.sh");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void restart(){
		stop(); start();
	}
	
	private void stop(){
		try {
	        Socket s = new Socket("localhost", port); // TODO mb null instead of localhost
	        if(s.isConnected()){
	            PrintWriter print = new PrintWriter(s.getOutputStream(),true);
	            //Stop tomcat if it is already started
	            print.println("SHUTDOWN"); 
	            print.close();
	        }
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}