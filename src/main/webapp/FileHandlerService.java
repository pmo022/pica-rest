package main.webapp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import connecters.IPicaConnector;
import factories.PicaConnectorFactory;

/**
 * This class restfully exposes files to the web by accessing them through the pica-library.
 * @author patmon
 *
 */
@Path("/projects/{projectName}/files")
public class FileHandlerService {
	
	/**
	 * List all available files
	 * @return an xml representation of available files
	 */
	@GET
	@Produces("application/xml")
	public String listFiles(@PathParam("projectName") String projectName){
		StringBuilder result = new StringBuilder().append("<filehandlerservice>");
		
		// TODO access pica library to get <file> and <folder>
		Object files = PicaConnectorFactory.getDefaultConnector().getFiles(projectName);
		
	    result.append("<fileoutput> @Produces(\"application/xml\") Output: \n\nFileHandler found the following files: \n\n </fileoutput></filehandlerservice>");
		return result.toString();
	}
	
	/**
	 * List files and folders recusively as XML into the StringBuilder output.
	 * @param output
	 * 		The stringbuilder which we list our files and folders into
	 * @param folder
	 * 		The folder from which we start listing files and folders
	 */
	public void listFilesHelper(StringBuilder output, File folder){
		File[] listOfFiles = folder.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        output.append("<file>").append(listOfFiles[i].getName()).append("</file>");
	      } else if (listOfFiles[i].isDirectory()) {
	    	output.append("<folder name=\"").append(listOfFiles[i].getName()).append("\">");
	        listFilesHelper(output, listOfFiles[i]);
	    	output.append("</folder>");
	      }
	    }
	}
 
	/**
	 * Gets a file names "filename"
	 * @param filename the name of the file
	 * @return the file associated with filename
	 */
	@Path("{filename}")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("projectName") String projectName,
							@PathParam("filename") String filename) {
		
		filename = filename.replace('.', '/'); // TODO better solution
		
		// TODO might need updating
		Object file = PicaConnectorFactory.getDefaultConnector().getFile(projectName, filename);
		
		if(file == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
	      .header("Content-Disposition", "attachment; filename=\"" + filename + "\"" ) 
	      .build();

	}
	
	/**
	 * Creates or updates a the file associated with filename should one exist
	 * @param filename the name of the file
	 * @param fileBytes the bytes of the file
	 * @return Response detailing how the operation went
	 */
	@PUT
	@Path("/{filename}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response uploadFile(@PathParam("projectName") String projectName, 
			@PathParam("filename") String filename, byte[] fileBytes) {
		
		filename = filename.replace('.', '/'); // TODO better solution
		
		boolean fileUpdated = PicaConnectorFactory.getDefaultConnector().updateFile(projectName, filename, fileBytes);
		if(fileUpdated)
			return Response.ok().build();
		else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
			 
	}
}
