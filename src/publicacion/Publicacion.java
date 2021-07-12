package publicacion;

import java.time.LocalDate;
import java.util.List;

import politicaDeCancelacion.PoliticaDeCancelacion;
import testUsuario.Reserva;

public class Publicacion {

	private List<Reserva> reservasEncoladas;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Double precioPorDia;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public void encolarReserva(Reserva reserva) {
		this.añadirReserva(reserva);
	}

	private void añadirReserva(Reserva reserva) {
		this.getReservas().add(reserva);
	}

	public List<Reserva> getReservas() {
		
		return (this.reservasEncoladas);
	}

	public void cancelar(Reserva reserva) {
		this.cobrarConCancelacion(reserva);
		this.getReservas().remove(reserva);	
	}

	private void cobrarConCancelacion(Reserva reserva) {
			this.getPoliticaCancelacion().cobrar(reserva);
			
	}

	public PoliticaDeCancelacion getPoliticaCancelacion() {
		return (this.politicaDeCancelacion);
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



}
