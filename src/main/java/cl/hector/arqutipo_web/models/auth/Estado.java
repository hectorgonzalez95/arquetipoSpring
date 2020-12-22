package cl.hector.arqutipo_web.models.auth;

public enum Estado {
	ACTIVO("Registro Activo"), 
	INACTIVO("Registro Inactivo"),
	ELIMINADO("Registro Eliminado");

	private final String texto;

	Estado(String v) {
		this.texto = v;
	}

	public String getTexto() {
		return texto;
	}
}
