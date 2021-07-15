package estadosReserva;

import Reserva.CategoriaDePuntaje;
import Reserva.Reserva;
import politicaDeCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;

public class EstadoConcretada extends Estado {
	
/**
 * @author bian
 * al concretarse la reserva es posible que el usuario interesado puntee al inmueble
 * al concretarse la reserva es posible que el usuario interesado puntee al propietario de la publicacion de la reserva
 * al concretarse la reserva se consolida en el sitio
 * al concretarse la reserva se puede cancelar para luego cobrar el monto correspondiente 
 */
	@Override
		public void puntuarInmueble(Reserva reserva, Integer puntos, CategoriaDePuntaje categoria) {
			this.getInmueble(reserva).registrarPuntajeDe(reserva.getUsuarioInteresado(),puntos, categoria);
		  }
	@Override
		public void puntuarUsuario(Reserva reserva, Integer puntos, CategoriaDePuntaje categoria) {
			this.getPropietario(reserva).registrarPuntajeDe(reserva.getUsuarioInteresado(),puntos, categoria);
	     }
	@Override
	public void consolidarEnElSitio(Reserva reserva) {
		reserva.propietarioDePublicacion().confirmarAlquiler(reserva);
		reserva.dispararMailConfirmacion(reserva.getUsuarioInteresado());
	    }
	
	@Override
	public void cancelar( Reserva reserva) {
		 Estado cancelado =  new EstadoCancelada();
		 reserva.setEstado(cancelado);
		 reserva.darseDeBaja();
		
	}

	@Override 
	public boolean estaConcretado() {
			
			return true;
		}
	
	
}
