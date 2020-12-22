package cl.hector.arqutipo_web.models.auth;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown =  true)
public class Mensaje {
	
	@JsonProperty("codigo")
	private Integer codigo;
	@JsonProperty("mensaje")
	private String mensaje;
	private Object objeto;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer v) {
		this.codigo = v;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String v) {
		this.mensaje = v;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object v) {
		this.objeto = v;
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ToStringBuilder.reflectionToString(this);
	}
}