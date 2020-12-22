package cl.hector.arqutipo_web.models.auth;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown =  false)
public class Sistema {

	@JsonProperty("idSistema") 
	private String idSistema;
	@JsonProperty("nombre") 
	private String nombre;
	@JsonProperty("idProyecto") 
	private String idProyecto;
	@JsonProperty("codigoSistema") 
	private String codigo;
	private List<Rol> roles;


	public String getIdSistema() {
		return idSistema;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIdProyecto() {
		return idProyecto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setIdSistema(String vIdSistema) {
		idSistema = vIdSistema;
	}

	public void setNombre(String vNombre) {
		nombre = vNombre;
	}

	public void setIdProyecto(String vIdProyecto) {
		idProyecto = vIdProyecto;
	}

	public void setCodigo(String vCodigo) {
		codigo = vCodigo;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> v) {
		this.roles = v;
	}
	
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ToStringBuilder.reflectionToString(this);
	}
}
