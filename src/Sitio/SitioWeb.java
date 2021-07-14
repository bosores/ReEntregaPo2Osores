package Sitio;

import java.time.LocalDate;
import java.util.List;

import Reserva.Reserva;
import publicacion.FormaDePago;
import publicacion.Inmueble;
import publicacion.OcupacionDeInmueble;
import publicacion.Publicacion;

public class SitioWeb {

	private List<OcupacionDeInmueble> ocupaciones;

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

	public void generarOcupacionDeReserva(Reserva reserva) {
		OcupacionDeInmueble ocupacion = this.ocupacionDeReserva(reserva);
		this.getOcupaciones().add(ocupacion);
	}

	private List<OcupacionDeInmueble> getOcupaciones() {
		return this.ocupaciones;
	}
	
	private OcupacionDeInmueble ocupacionDeReserva(Reserva reserva) {
		Usuario inquilino = reserva.getUsuarioInteresado();
		Inmueble inmubeleAlquilado = reserva.getInmuebleDePublicacion();
		LocalDate checkIn = reserva.getCheckIn(); 
		LocalDate checkOut = reserva.getCheckOut(); 
		return( new OcupacionDeInmueble(inquilino, inmubeleAlquilado, checkIn, checkOut));
	}
	

}
