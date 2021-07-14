package estadosReserva;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Reserva.Reserva;
import politicaDeCancelacion.PoliticaDeCancelacion;

public class EstadoEsperandoAprobacion extends Estado{

	public void consolidarEnElSitio(Reserva reserva) {
		reserva.setEstado(new EstadoConcretada() );
		reserva.consolidarEnElSitio();
	}
	
	public void propietarioAcepta(Reserva reserva) {
		this.consolidarEnElSitio(reserva);
		
	}

	public void propietarioRechaza(Reserva reserva) {
		this.getPropietario(reserva).rechazarReserva(reserva);
		this.rechazada(reserva);
		this.getPropietario(reserva).cancelar(reserva);
	}

}
