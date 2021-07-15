package estadosReserva;

import static org.junit.jupiter.api.Assertions.assertThrows;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Reserva.Reserva;
import politicaDeCancelacion.PoliticaDeCancelacion;

public class EstadoEsperandoAprobacion extends Estado{

		 
		@Override
		public boolean estaEsperandoAprobacion() {
		
		return true;
		}
	
	
	public void consolidarEnElSitio(Reserva reserva) {
	
		reserva.setEstado(new EstadoConcretada() );
		reserva.consolidarEnElSitio();
	}
	
	public void propietarioRechaza(Reserva reserva) {
		this.rechazada(reserva);
		this.getPropietario(reserva).cancelar(reserva);
	}


}
