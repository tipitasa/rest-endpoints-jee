package si.assignment.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import si.assignment.bean.AssignmentManagerBean;

//@RequestScoped
@Stateless
@Path("/helloWorld")
//@Produces({ "application/json", "text/plain" }) tole je delalo za helloworld
//@Consumes({ "application/xml", "application/json" })
//@Produces({ MediaType.TEXT_PLAIN })
//@Consumes({ MediaType.TEXT_PLAIN })
@Produces({"*"})
@Consumes({"*"})
public class HelloWorldResource {

	@EJB
	AssignmentManagerBean manager;

	@POST
	@Path("{shraniMe}")
	public String saveTheDate(@PathParam("shraniMe") String dateToSave) {
//		String dateToSave = "2020-03-17";
		return manager.saveTheDate(dateToSave);
	}
	
	@GET
	public String getHelloWorld() {
		return manager.getHelloWorld();
	}

	@POST
	@Path("shrani")
	public String saveTheDate() {
		String dateToSave = "2020-03-17";
		return manager.saveTheDate(dateToSave);
	}
}
