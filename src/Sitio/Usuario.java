package Sitio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Reserva.CategoriaDePuntaje;
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
	private List<Puntaje> puntajes = new ArrayList<Puntaje>();
	private ServicioMail servicioMail = new ServicioMail();
	private int mailsRecibidos;

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
		reserva.getPublicacion().cancelar(reserva);
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

	public void confirmarAlquiler(Reserva reserva) {
		this.getSitio().generarOcupacionDeReserva(reserva);
		
	}


	public void registrarPuntajeDe(Usuario usuarioInteresado, Integer puntos, CategoriaDePuntaje categoria) {
		Puntaje puntajeRecibido =  new Puntaje(usuarioInteresado, puntos, categoria);
		this.getPuntajes().add(puntajeRecibido);
		
	}

	public void enviarMailDeConfirmacionPara(Usuario usuarioInteresado) {
		this.getServicioMail().mailDeConfirmacionPara(usuarioInteresado);
	}

	public int getMailsDeConfirmacionRecibidos() {
		return(this.mailsRecibidos);
	}
	private ServicioMail getServicioMail() {
		return (this.servicioMail);
	}

	public void rechazarReserva(Reserva reserva) {
			reserva.fueRechazada();
	}

	public void enviarMailDeCancelacionPara(Usuario usuarioInteresado) {
		this.getServicioMail().mailDeCancelacionPara(usuarioInteresado);

	}

	public void setMailsRecibidos(int mailsRecibidos) {
		this.mailsRecibidos = mailsRecibidos;
	}

	public void puntuarInmueble(Reserva reserva, Integer valor, CategoriaDePuntaje category) throws Exception {
		reserva.puntuarInmueble(valor, category);
	}
	
	public void puntuarPropietario(Reserva reserva, Integer valor, CategoriaDePuntaje category) throws Exception {
		reserva.puntuarPropietario(valor, category);
	}

	public Usuario() {
		super();
		this.puntajes = new ArrayList<Puntaje>();
		this.servicioMail = new ServicioMail();
	}
	
	
	
	
	
}
