package Sitio;

public class ServicioMail {

	public void mailDeConfirmacionPara(Usuario usuarioInteresado) {
		usuarioInteresado.setMailsRecibidos(usuarioInteresado.getMailsDeConfirmacionRecibidos()  +1)  ;
	}

	public void mailDeCancelacionPara(Usuario usuarioInteresado) {
		usuarioInteresado.setMailsRecibidos(usuarioInteresado.getMailsDeConfirmacionRecibidos()  +1)  ;
	}

}
