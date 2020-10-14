package si.assignment.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AssignmentTable
 *
 */
@Entity
public class AssignmentTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate someDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getSomeDate() {
		return someDate;
	}

	public void setSomeDate(LocalDate someDate) {
		this.someDate = someDate;
	}

	public AssignmentTable() {
		super();
	}

	@Override
	public String toString() {
		return "AssignmentTable [id=" + id + "someDate=" + someDate + "]";
	}

}
