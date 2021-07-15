package Sitio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Reserva.Reserva;
import publicacion.FormaDePago;
import publicacion.Inmueble;
import publicacion.OcupacionDeInmueble;
import publicacion.Publicacion;

public class SitioWeb {

	private List<OcupacionDeInmueble> ocupaciones;
	private ServicioMail servicioMail = new ServicioMail();
	public SitioWeb() {
		this.ocupaciones = new ArrayList<OcupacionDeInmueble>();

	}

	public void registrar(Usuario usuario) {
		
	}

	public void registrarReserva(Usuario usuarioInteresado, Publicacion publi, 
								 FormaDePago forma, LocalDate checkIn, LocalDate checkOut) {
		
			Reserva reserva =  new Reserva(usuarioInteresado, publi,forma, checkIn, checkOut  );
			publi.encolarReserva(reserva);
	}
	

	public void cancelarReserva(Reserva reserva) {
		reserva.getPublicacion().cancelar(reserva);
	}

	public void generarOcupacionDeReserva(Reserva reserva) {
		
		this.getOcupaciones().add(this.ocupacionDeReserva(reserva));
	}

	public List<OcupacionDeInmueble> getOcupaciones() {
		return this.ocupaciones;
	}
	
	public OcupacionDeInmueble ocupacionDeReserva(Reserva reserva) {
		 
		return( new OcupacionDeInmueble(reserva));
	}
	
	public boolean registraOcupacionDe(Reserva reserva) {
		return(this.getOcupaciones().stream().anyMatch( o -> o.pertenece(reserva) ) ) ;
	}

}
