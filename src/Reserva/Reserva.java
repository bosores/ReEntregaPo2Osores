package Reserva;

import java.time.LocalDate;

import estadosReserva.Estado;
import estadosReserva.EstadoEsperandoAprobacion;
import publicacion.FormaDePago;
import publicacion.Publicacion;
import usuario.Usuario;

import static java.time.temporal.ChronoUnit.DAYS;

public class Reserva {

	private Usuario usuarioInteresado;
	private Publicacion publicacion;
	private FormaDePago formaDePago;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Estado estado;

	public Reserva(Usuario usuarioInteresado, Publicacion publi, FormaDePago forma, LocalDate checkIn,
			LocalDate checkOut) {
		this.setUsuarioInteresado(usuarioInteresado);
		this.setPublicacion(publi);
		this.setFormaDePago(forma);
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setEstado( new EstadoEsperandoAprobacion() ); //cuando la reserva es generada está esperando aprobacion
	
	}
	/**GETTERS AND STTERS**/
	

	public Usuario     getUsuarioInteresado() {
		return usuarioInteresado;
	}
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public FormaDePago getFormaDePago() {
		return formaDePago;
	}
	public LocalDate   getCheckIn() {
		return checkIn;
	}
	public LocalDate   getCheckOut() {
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
	public void  setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Double getPrecioDePublicacionPorDia() {
		return (this.getPublicacion().getPrecioPorDia());
	}
	public long   getDiasDeReserva() {
        long dias = DAYS.between(checkIn, checkOut);
		return(dias);
	}
	public Estado getEstado() {
		return (this.estado) ;
	}	
	
	public boolean fueCreadaConDiasDeAnterioridad(int dias) {
        long diferencia = DAYS.between(checkIn, getPublicacion().getCheckIn());
		return ( diferencia >dias  );
	}
	
	
	
	public void puntuarPropietario(int valor, CategoriaDePuntaje category) {
		
		Puntaje puntaje = new Puntaje( this.propietarioDePublicacion(), valor, category);
		this.getEstado().puntuar(puntaje);	
	}

	private Usuario propietarioDePublicacion() {
		return this.getPublicacion().getPropietario();
	}

	
	
	
	
	

}
