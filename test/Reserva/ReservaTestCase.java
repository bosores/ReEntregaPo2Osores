package Reserva;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.*;
import Sitio.*;
import estadosReserva.*;
import publicacion.*;

class ReservaTestCase {
	Reserva reserva;
	Usuario usuarioInteresado;
	Publicacion publicacion;
	FormaDePago formaDePago;
	LocalDate checkIn;
	LocalDate checkOut;
	Estado estado;
	Usuario propietario;	Usuario interesado ;
	SitioWeb sitio;
	
	
	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		 propietario = new Usuario(); propietario.registrarse(sitio);
		 interesado = new Usuario();  interesado.registrarse(sitio);
		
		publicacion = mock(Publicacion.class);
		formaDePago = mock(FormaDePago.class);
		
				
		reserva = new Reserva(usuarioInteresado, publicacion, formaDePago, checkIn, checkOut);
		
	}

	@Test
	void testReservaGettersAndSetters() {
		assertEquals(usuarioInteresado , reserva.getUsuarioInteresado());
		assertEquals(publicacion , reserva.getPublicacion());
		assertEquals(formaDePago , reserva.getFormaDePago());
		assertEquals(checkIn , reserva.getCheckIn());
		assertEquals(checkOut , reserva.getCheckOut());
	}
	
	@Test
	void testReservaConocePrecioPorDiaDePublicacion() {
		publicacion = mock(Publicacion.class);
		when(publicacion.getPrecioPorDia()).thenReturn(5.0);
		
		reserva.setPublicacion(publicacion);
		
		assertEquals(5.0, reserva.getPrecioDePublicacionPorDia() );
		
	}
	@Test 
	void testReservaConoceCuántosDiasTieneDisponibles() {
		LocalDate checkIn = LocalDate.of(2001, 02, 10);
		LocalDate checkOut = LocalDate.of(2001, 02, 15);
		
		reserva.setCheckIn(checkIn); reserva.setCheckOut(checkOut);
		long diasDeReserva = reserva.getDiasDeReserva();
		assertEquals(5 , diasDeReserva);
		
	}
	@Test
	void tesReservaConoceElInmuebleDeLaPublicacion() {
		Inmueble inmubleDePubli = mock(Inmueble.class);
		publicacion = mock(Publicacion.class);
		when(publicacion.getInmueble()).thenReturn(inmubleDePubli);
		
		reserva.setPublicacion(publicacion);
		assertEquals(inmubleDePubli ,  reserva.getInmuebleDePublicacion());
	}
	@Test
	void testReservaFueCreadaConDiasDeAnterioridadTiene() {
		/**@author bian desde su check in hasta el check in de la publicación
		 * de esta forma conozco si le corresponde la politica de cancelacion establecida
		 */
		LocalDate checkIn = LocalDate.of(2001, 02, 05);
		LocalDate checkInPubli= LocalDate.of(2001, 02, 10);
		publicacion = mock(Publicacion.class);
		when(publicacion.getCheckIn()).thenReturn(checkInPubli);
		
		reserva.setPublicacion(publicacion);
		reserva.setCheckIn(checkIn);
			
		assertTrue( reserva.fueCreadaConDiasDeAnterioridad(5));
		
	}
	@Test
	void testReservaNOFueCreadaConDiasDeAnterioridadTiene() {
		
		LocalDate checkIn = LocalDate.of(2001, 02, 9);
		LocalDate checkInPubli= LocalDate.of(2001, 02, 10);
		publicacion = mock(Publicacion.class);
		when(publicacion.getCheckIn()).thenReturn(checkInPubli);
		
		reserva.setPublicacion(publicacion);
		reserva.setCheckIn(checkIn);
			
		assertFalse( reserva.fueCreadaConDiasDeAnterioridad(5));
		
	}
	@Test
	void testReservaDisparaMailDeConfirmacionAlInteresado() {
		Usuario propietario = new Usuario();
		Usuario interesado = new Usuario();
		
		when(publicacion.getPropietario()).thenReturn(propietario);
		reserva.dispararMailConfirmacion(interesado);
		
		assertEquals(1, interesado.getMailsDeConfirmacionRecibidos()); 
	}
	
	/** @author bian testeo de estados de una reserva**/
	
	@Test 
	void testCuandoUnaReservaSeCreaSuEstadoEsEsperandoAprobacion() {
		/**@author bian
		 * entiendo que  cuando una reserva se crea está esperando que se genere la confirmacion 
		 * o el rechazo por parte del propietario. #Primer estado o estado Inicial
		 */
		
		Reserva reserva = new Reserva(usuarioInteresado, 
									  publicacion, formaDePago,
									  checkIn, checkOut);
		
		assertTrue(reserva.getEstado().estaEsperandoAprobacion() );
		assertFalse(reserva.getEstado().estaConcretado() );
		assertFalse(reserva.getEstado().estaCancelado() );
	}
	void testCuandoUnaReservaSeCreaSuEstadoEsEsperandoAprobacionNoSePuedeConsolidar() {
		try {
			reserva.consolidarEnElSitio();
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> reserva.consolidarEnElSitio() );
		}
	}
	@Test
	void testCuandoUnaReservaEstáEnEsperandoAprobacionNoSePuedeConcretarNingunPuntaje() {

        
		Reserva reserva = new Reserva(usuarioInteresado,  publicacion, formaDePago,	  checkIn, checkOut);
		
		CategoriaDePuntaje category = new CategoriaDePuntaje();
		CategoriaDePuntaje categoryInmueble = new CategoriaDePuntaje();
		
		try {
			reserva.puntuarPropietario(5, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() ->reserva.puntuarPropietario(5, category) );
		}
		
		try {
			reserva.puntuarInmueble(5, categoryInmueble);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() ->reserva.puntuarPropietario(2, categoryInmueble) );
		}
	}
	@Test
	void testCuandoUnaReservaEstáEnEsperandoAprobacionYSeConsolidaCambiaSuEstado() {
		
		Reserva reserva = new Reserva(interesado,  publicacion, formaDePago,	  checkIn, checkOut);
		when(publicacion.getPropietario()).thenReturn(propietario);

		reserva.consolidarEnElSitio();
			
	
		assertEquals(1, interesado.getMailsDeConfirmacionRecibidos()); 	
		assertTrue(reserva.getEstado().estaConcretado());
		
	}
	
	@Test
	void testUnaReservaEstáEnEstadoConsolidadoEnElSitioElSitioRegistraOcupacion() {
		Inmueble inmueble = mock(Inmueble.class);
	
		Reserva reserva = new Reserva(interesado,  publicacion, formaDePago,	  checkIn, checkOut);
		when(publicacion.getPropietario()).thenReturn(propietario);
		when(publicacion.getInmueble()).thenReturn(inmueble);

		reserva.consolidarEnElSitio();
		
		assertEquals(1, interesado.getMailsDeConfirmacionRecibidos()); 	
		assertTrue(reserva.getEstado().estaConcretado());
		assertTrue(sitio.registraOcupacionDe(reserva) );
		
	}
	
	
	void testUnaReservaEstaEsperandoAprobacionSeCancelaYSeEliminaDelSitio() {
		Publicacion publi =new Publicacion();
		publi.setPropietario(propietario);
		Reserva reserva = new Reserva(usuarioInteresado, 
									  publi, formaDePago, checkIn, checkOut);
		reserva.fueRechazada();
		assertTrue(reserva.getEstado().estaCancelado() );
		assertFalse(sitio.registraOcupacionDe(reserva));
		assertFalse(publi.registra(reserva));
		
	}
	
	
	
	

}
