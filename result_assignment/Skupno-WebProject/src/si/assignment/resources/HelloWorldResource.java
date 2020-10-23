package si.assignment.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import si.assignment.bean.AssignmentManagerBean;

@Stateless
@Path("/helloWorld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

	@EJB
	private AssignmentManagerBean manager;

	@GET
	public Response getHelloWorld() {
		return manager.getHelloWorld();
	}

	@POST
	@Path("{dateToSave}")
	public Response saveTheDate(@PathParam("dateToSave") String dateToSave) {
		return manager.saveTheDate(dateToSave);
	}

	@GET
	@Path("fejkdatum")
	public Response saveTheDate() {
		String dateToSave = "2020-03-17";
		return manager.saveTheDate(dateToSave);
	}
	
	@DELETE
	@Path("delDate/{id}")
	public void delete(@PathParam("id") String id) {
		manager.deleteAssignmentTable(id);
	}
}
