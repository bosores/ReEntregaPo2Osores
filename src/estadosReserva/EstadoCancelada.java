package estadosReserva;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Reserva.Reserva;
import politicaDeCancelacion.PoliticaDeCancelacion;
import publicacion.Publicacion;

public class EstadoCancelada extends Estado{
	
	@Override 
	public boolean estaCancelado()          { return (true);}
	
	@Override
	public void cancelar( Reserva reserva) {
		reserva.getPublicacion().cancelar(reserva);
	}

	@Override
	public void rechazada(Reserva reserva) {
		 this.getPropietario(reserva).cancelar(reserva);
	}
	
	
}
