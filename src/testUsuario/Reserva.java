package testUsuario;

import java.time.LocalDate;

import publicacion.Publicacion;

import static java.time.temporal.ChronoUnit.DAYS;

public class Reserva {

	private Usuario usuarioInteresado;
	private Publicacion publicacion;
	private FormaDePago formaDePago;
	private LocalDate checkIn;
	private LocalDate checkOut;

	public Reserva(Usuario usuarioInteresado, Publicacion publi, FormaDePago forma, LocalDate checkIn,
			LocalDate checkOut) {
		this.setUsuarioInteresado(usuarioInteresado);
		this.setPublicacion(publi);
		this.setFormaDePago(forma);
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
	
	}

	public Usuario getUsuarioInteresado() {
		return usuarioInteresado;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	private void setCheckOut(LocalDate checkOut) {
		this.checkOut =checkOut;
	}

	private void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	private void setFormaDePago(FormaDePago forma) {
		this.formaDePago = forma;
	}

	private void setPublicacion(Publicacion publi) {
		this.publicacion =publi;
	}

	private void setUsuarioInteresado(Usuario usuarioInteresado) {
		this.usuarioInteresado = usuarioInteresado;
	}

	public Double getPrecioDePublicacionPorDia() {

		return (this.getPublicacion().getPrecioPorDia());
	}

	public long getDiasDeReserva() {
        long dias = DAYS.between(checkIn, checkOut);
		return(dias);
	}

	public boolean fueCreadaConDiasDeAnterioridad(int dias) {
        long diferencia = DAYS.between(checkIn, getPublicacion().getCheckIn());
		return ( diferencia >dias  );
	}

}
