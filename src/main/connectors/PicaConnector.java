package main.connectors;

import org.nuthatchery.pica.IPica;
import org.nuthatchery.pica.Pica;
import org.nuthatchery.pica.resources.IWorkspaceManager;

/**
 * Encapsulates access to the Pica library for ease of use in the webapp
 * @author patmon
 */
public class PicaConnector implements IPicaConnector {
	
	static PicaConnector instance;
	private PicaConnector() {}

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
			instance = new PicaConnector();
		}
		return instance;
	}

	@Override
	public Object getWorkspace() {
		IPica pica = Pica.get();
		IWorkspaceManager wm = pica.getWorkspaceManager();
		
		
		return null;
	}

}
