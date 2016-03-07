package factories.pica;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jdt.annotation.Nullable;
import org.nuthatchery.pica.ConsolePicaInfra;
import org.nuthatchery.pica.Pica;
import org.nuthatchery.pica.resources.ILanguage;
import org.nuthatchery.pica.resources.IProjectManager;
import org.nuthatchery.pica.resources.IWorkspaceConfig;
import org.nuthatchery.pica.resources.handles.IFileHandle;
import org.nuthatchery.pica.resources.managed.IManagedCodeUnit;
import org.nuthatchery.pica.resources.storage.IStorage;

import connectors.IPicaConnector;
import connectors.PicaConnector;

public class PicaConnectorFactory {

	static final String[] validConnectors = {"eclipse"}; // TODO add more connectors
	static String defaultConnector = "eclipse";
	static boolean picaIsInitialized = false;
	
	
	public static IPicaConnector getDefaultConnector(){
		initializePica();
		/*
		if(defaultConnector.equals("eclipse")){
			return getEclipseConnector();
		}
		else if (connector equals another connector return that one) ...
		*/
		return getEclipseConnector();
	}
	
	public static IPicaConnector getEclipseConnector(){		
		return PicaConnector.getInstance();
	}
	
	public static String[] getValidConnectors(){
		return validConnectors;
	}
	
	/**
	 * Initialized Pica on the workspace. May need some changes, TODO ask Anya
	 */
	private static void initializePica(){
		if(picaIsInitialized){
			return;
		}
		Pica.set(new ConsolePicaInfra(new IWorkspaceConfig() {
			@Override
			public Collection<String> getActiveNatures() {
				return Collections.EMPTY_LIST;
			}


			@Override
			public void initCompiler() {
			}


			@Override
			public IManagedCodeUnit makePackage(IProjectManager manager, IFileHandle res, @Nullable IStorage storage, Object id, ILanguage lang) {
				throw new UnsupportedOperationException();
			}

		}));
		picaIsInitialized = true;
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
