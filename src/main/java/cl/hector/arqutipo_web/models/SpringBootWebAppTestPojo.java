package cl.hector.arqutipo_web.models;

import java.io.Serializable;

public class SpringBootWebAppTestPojo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long idTest;
	private String descripcion;
	
	public Long getIdTest() {
		return idTest;
	}
	public void setIdTest(Long idTest) {
		this.idTest = idTest;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}