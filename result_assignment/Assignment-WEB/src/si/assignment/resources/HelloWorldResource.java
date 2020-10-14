/**
 * 
 */
package si.assignment.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import si.assignment.managerBeans.AssignmentManagerBean;

/**
 * @author plantgirl
 *
 */
@Path("helloWorld")
@Stateless
//@Produces("application/json; charset=utf-8")
//@Consumes("application/json; charset=utf-8")
@Produces("text/plain")
public class HelloWorldResource {

	@EJB
	private AssignmentManagerBean manager;

	@GET
	public String getHelloWorld() {
//		return "Hi there!";
		return manager.getHelloWorld();
	}

	@GET
	@Path("dateToSave")
	public String saveTheDate() {
		String date = "2020-03-17";
		return manager.saveTheDate(date);
	}
	
//	@GET
//	@Produces("text/plain")
//	@Consumes("text/plain")
//	@Path("/{dateToSave}")
//	public String saveTheDate(@PathParam("dateToSave")String stringiDate) {
//		String saveTheDate = manager.saveTheDate(stringiDate);
//		return saveTheDate;
//	}
	
	
}
