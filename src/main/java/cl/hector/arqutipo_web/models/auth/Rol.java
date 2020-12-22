package cl.hector.arqutipo_web.models.auth;

import org.springframework.security.core.GrantedAuthority;

public class Rol implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	private Long idRol;
	private String nombre;
	private String descripcion;
	private Integer region;   
	private Integer deprov;
	private Integer comuna;
	private Estado estado;
	
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long v) {
		this.idRol = v;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String v) {
		this.nombre = v;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String v) {
		this.descripcion = v;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer v) {
		this.region = v;
	}
	public Integer getDeprov() {
		return deprov;
	}
	public void setDeprov(Integer v) {
		this.deprov = v;
	}
	public Integer getComuna() {
		return comuna;
	}
	public void setComuna(Integer v) {
		this.comuna = v;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado v) {
		this.estado = v;
	}
	
	@Override
	public String getAuthority() {
		return this.nombre;
	}	
}
