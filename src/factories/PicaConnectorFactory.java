package factories;

import connecters.IPicaConnector;
import connecters.PicaEclipseConnector;

public class PicaConnectorFactory {

	static final String[] validConnectors = {"eclipse"}; // TODO add more connectors
	static String defaultConnector = "eclipse";
	
	public static IPicaConnector getDefaultConnector(){
		/*
		if(defaultConnector.equals("eclipse")){
			return getEclipseConnector();
		}
		else if (connector equals another connector return that one) ...
		*/
		return getEclipseConnector();
	}
	
	public static IPicaConnector getEclipseConnector(){
		return PicaEclipseConnector.getInstance();
	}
	
	public static String[] getValidConnectors(){
		return validConnectors;
	}
	
	public static boolean setDefaultConnector(String connectorName){
		for(String s : validConnectors){
			if (s.equals(connectorName)){
				defaultConnector = connectorName;
				return true; // default connector has been updated
			}
		}
		return false; // connectorName was not found
	}
}
