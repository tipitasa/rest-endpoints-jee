package si.assignment.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import si.assignment.bean.AssignmentManagerBean;

@Stateless
@Path("/helloWorld")
public class HelloWorldResource {

	@EJB
	private AssignmentManagerBean manager;

	@GET
	public String getHelloWorld() {
		return manager.getHelloWorld();
	}

	@POST
	@Path("{dateToSave}")
	public String saveTheDate(@PathParam("dateToSave") String dateToSave) {
		return manager.saveTheDate(dateToSave);
	}

	@GET
	@Path("fejkdatum")
	public String saveTheDate() {
		String dateToSave = "2020-03-17";
		return manager.saveTheDate(dateToSave);
	}
}
