package publicacion;

import java.time.LocalDate;

import Reserva.Reserva;
import Sitio.Usuario;

public class OcupacionDeInmueble {

	
	private Inmueble inmueble;

	public OcupacionDeInmueble(Usuario inquilino, Inmueble inmubeleAlquilado, LocalDate checkIn,LocalDate checkOut ) {
		
	}

	public OcupacionDeInmueble(Reserva reserva) {

		 this.inmueble = reserva.getInmuebleDePublicacion();	}

	public boolean pertenece(Reserva reserva) {
		return (reserva.getInmuebleDePublicacion().equals(this.getInmueble()));
	}

	public Inmueble getInmueble() {
		return (this.inmueble);
	}
	
}
