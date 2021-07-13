package estadosReserva;

import Reserva.Puntaje;

public abstract class Estado {
	public abstract void puntuar(Puntaje puntaje) ;

	public abstract boolean puedePuntuar(Puntaje puntaje);
	
}


