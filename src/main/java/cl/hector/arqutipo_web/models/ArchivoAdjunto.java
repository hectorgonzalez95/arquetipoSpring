package cl.hector.arqutipo_web.models;

/**
 * POJO utilitario para adjuntar lista de archivos mediante MailSenderService
 * puedes crear una lista de estos objetos con nombre y bytearray para ser enviados
 * @param adjuntoByteArray
 */
public class ArchivoAdjunto {

	private String nombreAdjunto;
	private byte[] adjuntoByteArray;
	
	public ArchivoAdjunto(String nombre, byte[] adjunto){
		this.nombreAdjunto=nombre;
		this.adjuntoByteArray=adjunto;
	}
	
	public String getNombreAdjunto() {
		return nombreAdjunto;
	}
	public void setNombreAdjunto(String nombreAdjunto) {
		this.nombreAdjunto = nombreAdjunto;
	}
	public byte[] getAdjuntoByteArray() {
		return adjuntoByteArray;
	}
	public void setAdjuntoByteArray(byte[] adjuntoByteArray) {
		this.adjuntoByteArray = adjuntoByteArray;
	}
}
