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


@Path("/files")
public class FileHandlerService {
	
	private static String rootFolder = "/home/patmon/Desktop/TestFiles/"; // TODO make relative
							// perhaps using new File("").getAbsolutePath(); to find root
	
	@GET
	@Produces("application/xml")
	public String listFiles(){
		File folder = new File(rootFolder);
		StringBuilder result = new StringBuilder().append("<filehandlerservice>");
		listFilesHelper(result, folder);
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
 
	@Path("{filename}")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFile(@PathParam("filename") String filename) {
		filename = filename.replace('.', '/');
		
		File file = new File(getAbsoluteFileLocation(filename));
		
		if(!file.exists()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
	      .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
	      .build();

	}
	

	@PUT
	@Path("/{filename}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response uploadFile(@PathParam("filename") String filename, byte[] fileBytes) {
		filename = filename.replace('.', '/');
		FileOutputStream out;
		try {
			out = new FileOutputStream(getAbsoluteFileLocation(filename));
			out.write(fileBytes);
			out.close();
			return Response.ok().build();

		} catch (IOException e) {
			e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();

		}
			 
	}
	
	private String getAbsoluteFileLocation(String filename){
		return rootFolder + filename + ".java";
	}
	
	

}
