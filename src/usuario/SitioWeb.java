package usuario;

import java.time.LocalDate;
import java.util.List;

import Reserva.Reserva;
import publicacion.FormaDePago;
import publicacion.Publicacion;

public class SitioWeb {

	public SitioWeb() {

	}

	public void registrar(Usuario usuario) {
	}

	public void registrarReserva(Usuario usuarioInteresado, Publicacion publi, 
								 FormaDePago forma, LocalDate checkIn, LocalDate checkOut) {
		
			Reserva reserva =  new Reserva(usuarioInteresado, publi,forma, checkIn, checkOut  );
			publi.encolarReserva(reserva);
	}
	

	public void cancelarReserva(Reserva reserva) {
		this.publiCancelarReserva(reserva.getPublicacion(), reserva);
	}

	private void publiCancelarReserva(Publicacion publicacion, Reserva reserva) {
			publicacion.cancelar(reserva);
	}
	
	

}
