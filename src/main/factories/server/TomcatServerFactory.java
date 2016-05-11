package main.factories.server;

import main.server.TomcatServer;

/**
 * Class responsible for programmatically handling the server
 * 
 * needs system variable System.getProperty("catalina.home")
 * 	to be set to the directory of your Tomcat installation, 
 * 	e.g. /home/%USER%/Documents/apache-tomcat-8.0/
 * 		(or wherever you installed your local tomcat)
 * 		//TODO maybe put tomcat installation in this pica-rest folder and put it out on git
 * 			keeping a relative path instead?
 * 	
 * 
 * @author patmon
 *
 */
public class TomcatServerFactory {
	private static TomcatServer instance = null;
	
	public static TomcatServer getInstance(){
		if (instance == null){
			initialize();
		}
		return instance;
	}

	private static void initialize(){
		String server_location = System.getProperty("catalina.home");
		if(server_location == null){
			server_location = "/home/patmon/Documents/apache-tomcat-8.0/";
		}
		instance = new TomcatServer(server_location);
	}
	
}


