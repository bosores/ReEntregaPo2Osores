package estadosReserva;

import Reserva.*;
import Sitio.Usuario;
import publicacion.Inmueble;
import publicacion.Publicacion;

public abstract class Estado {

	/**
	 * 
	 * @author bian
	 * lo que tengo en cuenta es que  ningun estado podrá implementar los mensajes. 
	 * luego cada uno reescribirá sólo el mensaje que le interesa 
	 * para realizar la accion correspondiente dependiendo de las reglas de negocio 
	 * @throws Exception 
	 */
	public void puntuarInmueble(Reserva reserva, Integer puntos, CategoriaDePuntaje categoria) throws Exception {
		this.errorDePuntuacion();
	}
	public void puntuarUsuario(Reserva reserva, Integer puntos, CategoriaDePuntaje categoria) throws Exception {
		this.errorDePuntuacion();
	}
	public void consolidarEnElSitio(Reserva reserva) {
		this.errorDeConsolidacion();
	}
	
	public void cancelar( Reserva reserva) {
		this.errorDeCancelacion();
	}

	
	public Usuario getPropietario(Reserva reserva) {
		return(reserva.propietarioDePublicacion());
	}
	
	public Inmueble getInmueble(Reserva reserva) {
		return(reserva.getInmuebleDePublicacion());
	}
	
	private void errorDeCancelacion() {
		
		 new Exception("no se puede cancelar la reserva en este momento");
	}
	private void errorDePuntuacion()throws Exception { 
		new Exception("no se puede puntuar en este momento");

	}
	private void errorDeConsolidacion() {
		 new Exception("no se puede consolidar la reserva en este momento");

	}
	public void rechazada(Reserva reserva) {
		this.getPropietario(reserva).enviarMailDeCancelacionPara(reserva.getUsuarioInteresado());
		reserva.setEstado(new EstadoCancelada());
		reserva.getEstado().cancelar(reserva);
	}
	
	/** @author bian estos mensajes solo son para finalidades de testeo **/
	
	public boolean estaCancelado() {
		return(false);
	}
	
	public boolean estaConcretado() {
		return(false);
	}
	
	public boolean estaEsperandoAprobacion() {
		return(false);
	}
	
}


