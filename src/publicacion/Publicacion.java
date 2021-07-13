package publicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import Reserva.Reserva;
import politicaDeCancelacion.PoliticaDeCancelacion;
import usuario.Usuario;

public class Publicacion {

	private Usuario propietario;
	private Inmueble inmueble;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Double precioPorDia;

	private PoliticaDeCancelacion politicaDeCancelacion;
	private List<Reserva> reservasEncoladas = new ArrayList<Reserva>();
	private List<Comentario> comentarios    = new ArrayList<Comentario>();
	private ArrayList<FormaDePago> formasDePago;
		
	public Publicacion(Usuario propietario, Inmueble inmueble,
					   ArrayList<FormaDePago> formasDePago, LocalDate checkIn,
			LocalDate checkOut, Double precio, PoliticaDeCancelacion politica) {

		this.setPropietario(propietario);
		this.setInmueble(inmueble);
		this.setFormasDePago(formasDePago);
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setPrecioPorDia(precio);
		this.setPoliticaDeCancelacion(politica);
	
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public ArrayList<FormaDePago> getFormasDePago() {
		return formasDePago;
	}
	//Getters and setters
	public void setFormasDePago(ArrayList<FormaDePago> formasDePago) {
		this.formasDePago = formasDePago; 
	}
	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
	public List<Reserva> getReservasEncoladas() {
		return reservasEncoladas;
	}
	public void setReservasEncoladas(List<Reserva> reservasEncoladas) {
		this.reservasEncoladas = reservasEncoladas;
	}
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return politicaDeCancelacion;
	}
	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politicaDeCancelacion) {
		this.politicaDeCancelacion = politicaDeCancelacion;
	}
	public void setPrecioPorDia(Double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public void encolarReserva(Reserva reserva) {
		this.añadirReserva(reserva);
	}
	
	public Double getPrecioPorDia() {
		return (this.precioPorDia);
	}
	public LocalDate getCheckIn() {
	return this.checkIn;
	}
	public LocalDate getCheckOut() {
		return this.checkOut;
	}
	public Usuario   getPropietario() {
		return this.propietario;
	}

	public Inmueble getInmueble() {
		return(this.inmueble);
	}

	private void añadirReserva(Reserva reserva) {
		this.getReservasEncoladas().add(reserva);
	}

	public void cancelar(Reserva reserva) {
		//this.cobrarConCancelacion(reserva);
		this.getReservasEncoladas().remove(reserva);	
	}

	private void cobrarConCancelacion(Reserva reserva) {
			this.getPoliticaDeCancelacion().cobrar(reserva);
			
	}
	public boolean aceptaFormaDePago(FormaDePago formaDePago) {
		return (this.getFormasDePago().contains(formaDePago) ) ;
	}




}
