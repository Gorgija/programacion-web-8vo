package py.com.una.pol.rest.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EntidadBase
 * 
 */
@MappedSuperclass
public class EntidadBase implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private static final long serialVersionUID = 1L;

	public EntidadBase() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}