package estadosReserva;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Reserva.Reserva;
import politicaDeCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;

public class EstadoCancelada extends Estado{
	
	@Override
	public void cancelar( Reserva reserva) {
		reserva.getPublicacion().cancelar(reserva);
	}
	
}
