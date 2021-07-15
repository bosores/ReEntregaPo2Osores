package Reserva;

import java.time.LocalDate;

import Sitio.Usuario;
import estadosReserva.Estado;
import estadosReserva.EstadoEsperandoAprobacion;
import publicacion.FormaDePago;
import publicacion.Inmueble;
import publicacion.Publicacion;

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
	public Reserva() {
		this.setEstado(new EstadoEsperandoAprobacion());
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

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut =checkOut;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public void setFormaDePago(FormaDePago forma) {
		this.formaDePago = forma;
	}
	public void setPublicacion(Publicacion publi) {
		this.publicacion =publi;
	}
	public void setUsuarioInteresado(Usuario usuarioInteresado) {
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
	
	public Usuario propietarioDePublicacion() {
		return this.getPublicacion().getPropietario();
	}
	
	
	public boolean fueCreadaConDiasDeAnterioridad(int dias) {
        long diferencia = DAYS.between(checkIn, getPublicacion().getCheckIn());
		return ( diferencia >=dias  );
	}
	
	
	public Inmueble getInmuebleDePublicacion() {
				return (this.getPublicacion().getInmueble());
	}
	public void dispararMailConfirmacion(Usuario usuarioInteresado) {
		this.propietarioDePublicacion().enviarMailDeConfirmacionPara(usuarioInteresado);
	}

	public void puntuarPropietario(int valor, CategoriaDePuntaje category) throws Exception {
		
		this.getEstado().puntuarUsuario(this, valor, category);	
	}
	
	public void puntuarInmueble(int valor, CategoriaDePuntaje category) throws Exception {
		
		this.getEstado().puntuarInmueble(this, valor, category);	
	}

	public void consolidarEnElSitio() {
		this.getEstado().consolidarEnElSitio( this);
	}
	
	
	public void darseDeBaja() {
		this.getEstado().cancelar(this);
		this.establecerBajaDelSitio();
	}
	public void establecerBajaDelSitio() {
			this.propietarioDePublicacion().getSitio().cancelarReserva(this);
		
	}
	public void fueRechazada() {
		this.getEstado().rechazada(this);
	}

	
	
	
	
	

}
