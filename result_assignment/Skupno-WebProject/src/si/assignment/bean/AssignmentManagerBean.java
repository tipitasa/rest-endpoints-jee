package si.assignment.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import si.assignment.dto.ResponseDTO;
import si.assignment.entity.AssignmentTable;

/**
 * Session Bean implementation class AssignmentManagerBean
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AssignmentManagerBean {

	@PersistenceContext
	protected EntityManager em;

	public Response getHelloWorld() {
		String helloWorldString = "Hello world!";
		ResponseDTO dto = new ResponseDTO();
		dto.setResponse(helloWorldString);

		return Response.status(Response.Status.OK).entity(dto).build();
	}

	/**
	 * 
	 * @param stringDateToSave in JSON format
	 * @return HTTP response code and the ID of saved entity
	 */
	public Response saveTheDate(String stringDateToSave) {
		// yyyy-mm-dd
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate dateToSave;

		try {
			dateToSave = LocalDate.parse(stringDateToSave, formatter);

		} catch (DateTimeParseException e) {
			String errorMessage = "Date could not be parsed. Please provide it in the yyyy-MM-dd format.";
			return Response.status(Status.FORBIDDEN).entity(errorMessage).build();
		}

		AssignmentTable at = new AssignmentTable();
		at.setSomeDate(dateToSave);

		em.persist(at);

		// flush to force persist to get id for return
		em.flush();

		int createdAtId = at.getId();
		ResponseDTO dto = new ResponseDTO();
		dto.setResponse(String.valueOf(createdAtId));
		return Response.status(Response.Status.CREATED).entity(dto).build();
	}

	public void deleteAssignmentTable(final String rowId) {
		// flush to force persist
		em.flush();
		AssignmentTable at = em.getReference(AssignmentTable.class, Integer.parseInt(rowId));
		em.remove(at);
	}

}
