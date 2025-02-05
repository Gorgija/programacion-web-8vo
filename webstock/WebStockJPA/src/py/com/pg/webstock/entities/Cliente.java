package py.com.pg.webstock.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Cliente
 * 
 */
@Entity
public class Cliente extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String ruc;
	private Date fechaAlta;
	private String telefono;
	private Double saldo;

	public Cliente() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return getId() + " - " + getNombre();
	}

}
