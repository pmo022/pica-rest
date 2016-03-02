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
 * This class restfully exposes projects to the web by accessing them through the pica-library.
 * @author patmon
 *
 */
@Path("/projects")
public class ProjectHandlerService {
	
	/**
	 * List all projects on the workspace
	 * @return an xml representation of available files
	 */
	@GET
	@Produces("application/xml")
	public String listProjects(){
		StringBuilder result = new StringBuilder().append("<projecthandlerservice>");
		
		// TODO access pica library to list available projects
		Object projects = PicaConnectorFactory.getDefaultConnector().getProjects();
		
	    result.append("<projectoutput> @Produces(\"application/xml\") Output: \n\nProjectHandler found the following projects: \n\n </projectoutput></projecthandlerservice>");
		return result.toString();
	}
	
	/**
	 * Gets a project named "projectName"
	 * @param projectName the name of the project
	 * @return the details of the project
	 */
	@Path("{projectName}")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM) // TODO make an xml representation of project?
	public Response getProject(@PathParam("projectName") String projectName) {
		
		// TODO do something worthwile with it (perhaps transform to xml?)
		Object project = PicaConnectorFactory.getDefaultConnector().getProject(projectName);
		
		if(project == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(project, MediaType.APPLICATION_OCTET_STREAM)
	      .header("Content-Disposition", "attachment; filename=\"" + projectName + "\"" ) 
	      .build();

	}
	
	/**
	 * Creates or updates a the project associated with projectName should one exist
	 * @param projectName the name of the project
	 * @param projectBytes the bytes of the project
	 * @return Response detailing how the operation went
	 */
	@PUT
	@Path("/{projectName}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response uploadFile(@PathParam("projectName") String projectName, byte[] projectBytes) {
		
		boolean updateOK = true; // TODO implement
		if(updateOK)
			return Response.ok().build();
		else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
			 
	}
}
