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
	 */
	public void puntuarInmueble(Reserva reserva, Integer puntos, CategoriaDePuntaje categoria) {
		this.errorDePuntuacion();
	}
	public void puntuarPropietario(Reserva reserva, Integer puntos, CategoriaDePuntaje categoria) {
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
		System.out.println("no se puede cancelar la reserva en este momento");
	}
	private void errorDePuntuacion() {
		System.out.println("no se puede puntuar en este momento");
	}
	private void errorDeConsolidacion() {
		System.out.println("no se puede consolidar la reserva en este momento");
	}
	public void rechazada(Reserva reserva) {
		this.getPropietario(reserva).enviarMailDeCancelacionPara(reserva.getUsuarioInteresado());
	}
	
	
	
	
}


