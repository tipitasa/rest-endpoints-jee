package si.assignment.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.assignment.entity.AssignmentTable;

/**
 * Session Bean implementation class AssignmentManagerBean
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AssignmentManagerBean {

	@PersistenceContext
	protected EntityManager em;

	public String getHelloWorld() {
		return "Hello Svetek!";
	}

	public String saveTheDate(String stringDateToSave) {
		// yyyy-mm-dd
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		LocalDate dateToSave = LocalDate.parse(stringDateToSave, formatter);

		AssignmentTable at = new AssignmentTable();
		at.setSomeDate(dateToSave);

		em.persist(at);

		return "Save OK";
	}

}
