package py.com.webstock.ejb.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import py.com.pg.webstock.ejb.ws.ObjectFactory;
import py.com.pg.webstock.ejb.ws.PagoDTO;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.4-b01 Generated
 * source version: 2.2
 * 
 */
@WebService(name = "PagoServiceWS", targetNamespace = "http://ws.ejb.webstock.pg.com.py/")
@XmlSeeAlso({ ObjectFactory.class })
public interface PagoServiceWS {

	/**
	 * 
	 * @param arg0
	 * @return returns java.util.List<py.com.pg.webstock.ejb.ws.PagoDTO>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "agregarPagos", targetNamespace = "http://ws.ejb.webstock.pg.com.py/", className = "py.com.pg.webstock.ejb.ws.AgregarPagos")
	@ResponseWrapper(localName = "agregarPagosResponse", targetNamespace = "http://ws.ejb.webstock.pg.com.py/", className = "py.com.pg.webstock.ejb.ws.AgregarPagosResponse")
	public List<PagoDTO> agregarPagos(
			@WebParam(name = "arg0", targetNamespace = "") List<PagoDTO> arg0);

}
