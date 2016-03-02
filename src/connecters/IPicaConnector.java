package connecters;


/**
 * Encapsulates access to the Pica library for ease of use in the webapp
 * @author patmon
 *
 */

public interface IPicaConnector {
		
	public Object getFile(String projectName, String filename);
	
	public Object getFiles(String projectName);
	
	public boolean updateFile(String projectName, String fileName, byte[] file);
	
	public Object getProject(String projectName);

	public Object getProjects();
}
