package usuario;

import java.time.LocalDate;
import java.util.List;

import Reserva.Puntaje;
import Reserva.Reserva;
import publicacion.FormaDePago;
import publicacion.Publicacion;

public class Usuario {

	private String eMail;
	private String nombre;
	private int telefono;
	private SitioWeb sitio;
	private double deudaPendiente;
	private List<Puntaje> puntajes;

	public void setEmail(String email) {
		this.eMail = email;		
	}

	public void setNombre(String nombre) {
		this.nombre	 =	nombre ;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		
		return (this.nombre);
	}

	public String getEmail() {
		return (this.eMail);
	}

	public Integer getTelefono() {
		return (this.telefono);
	}

	public void registrarse(SitioWeb sitio) {
		this.setSitio(sitio);
	}

	private void setSitio(SitioWeb sitio) {
			this.sitio = sitio;
			sitio.registrar(this);
	}

	public SitioWeb getSitio() {
		return this.sitio;
	}
	public double getDeudaPendiente() {
		return (this.deudaPendiente );
	}


	public void reservar(Publicacion publi, FormaDePago efectivo, LocalDate checkIn, LocalDate checkOut) {

		this.getSitio().registrarReserva(this, publi,  efectivo,  checkIn, checkOut );
		 
	}

	public void cancelar(Reserva reserva) {
		this.getSitio().cancelarReserva(reserva);
	}

	public void incorporarDeuda(double deuda) {
		 this.deudaPendiente = this.deudaPendiente +  deuda;
	}

	public void recibirPuntaje(Puntaje puntaje ) {
		this.getPuntajes().add(puntaje);
	}

	
	public List<Puntaje> getPuntajes() {
		return this.puntajes;
	}

	
	
	
	
	
	
	
}
