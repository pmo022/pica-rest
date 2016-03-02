package connecters;

/**
 * Encapsulates access to the Pica library for ease of use in the webapp
 * @author patmon
 *
 */
public class PicaEclipseConnector implements IPicaConnector {
	
	static PicaEclipseConnector instance;
	private PicaEclipseConnector() {}

	@Override
	public Object getFile(String projectName, String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getFiles(String projectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateFile(String projectName, String fileName, byte[] file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getProject(String projectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public static IPicaConnector getInstance() {
		if(instance == null){
			instance = new PicaEclipseConnector();
		}
		return instance;
	}

}
