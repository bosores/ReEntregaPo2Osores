package sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Reserva.Reserva;
import Sitio.SitioWeb;
import Sitio.Usuario;
import estadosReserva.EstadoEsperandoAprobacion;
import politicaDeCancelacion.PoliticaDeCancelacion;
import politicaDeCancelacion.PoliticaDeCancelacionGratuita;
import publicacion.Efectivo;
import publicacion.FormaDePago;
import publicacion.Publicacion;

class UsuarioTestCase {

		Usuario usuario;
		Usuario usuarioP;
		SitioWeb sitio; //DOT
		
	@BeforeEach
	void setUp() throws Exception {
		
		usuario = new Usuario();
		usuario.setNombre("Teresa");
		usuario.setEmail("teresa@gmail.com");
		usuario.setTelefono(123456);
		usuarioP = new Usuario();
		sitio = mock(SitioWeb.class);
		usuario.registrarse(sitio);

	}

	@Test
	void testUsuarioTieneNombreTelefonoEmail() {
		assertEquals("Teresa", usuario.getNombre() );
		assertEquals("teresa@gmail.com", usuario.getEmail() );
		assertEquals(123456, usuario.getTelefono() );
	}

	@Test
	void testUsuarioSeRegistraEnSitio() {
		SitioWeb sitio = mock(SitioWeb.class);
		usuario.registrarse(sitio);
		
		verify(sitio , times(1) ).registrar(usuario);
		assertEquals(sitio, usuario.getSitio() );
				
	}
	
	@Test
	void testUsuarioReservarUnaPublicacion() {
		Publicacion publi = mock(Publicacion.class);
		FormaDePago efectivo = mock(Efectivo.class);  //Array<Object> (); 
		LocalDate checkIn =  LocalDate.now();  
		LocalDate checkOut = checkIn.plusDays(2);  
		 
		usuario.registrarse(sitio);

		usuario.reservar(publi, efectivo,checkIn, checkOut);
		  
		 
		verify(sitio, times(1)).registrarReserva(usuario, publi, efectivo, checkIn, checkOut);
		
	}
	
	@Test
	void testUsuarioNoTieneDeudasPorCobrar() {
		assertEquals(0, usuario.getDeudaPendiente() );
	}


	@Test
	void testUsuarioCancelaLaReservaRealizada() {
		SitioWeb sitio = new SitioWeb();
		Reserva reserva = mock(Reserva.class);
		
		Publicacion publi = new Publicacion();
		publi.encolarReserva(reserva);
		when(reserva.getPublicacion()).thenReturn(publi);
				
		usuario.cancelar(reserva);
		assertFalse(reserva.getPublicacion().registra(reserva));
	}
	@Test
	void testUsuarioEnviaMailDeCancelacion() {
		Usuario usuarioDestino = new Usuario();
		
		usuario.enviarMailDeCancelacionPara(usuarioDestino);
		assertEquals(1, usuarioDestino.getMailsDeConfirmacionRecibidos() );
	}
	
	
	@Test 
	void testUnUsuarioRecibePuntaje() {
		Puntaje puntaje  = mock(Puntaje.class);
		
		usuario.recibirPuntaje(puntaje);
		assertTrue(usuario.getPuntajes().contains(puntaje));
	}
	
	
	
	@Test
	void testUsuarioPuntuaNOPuede() throws Exception {
		Reserva reserva = new Reserva();
		CategoriaDePuntaje category = new CategoriaDePuntaje();
		
		try {
			usuario.puntuarPropietario(reserva, 5, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> 		usuario.puntuarPropietario(reserva, 5, category));
 

		}
		
	}
	
	
	
	@Test
	void testUsuarioPuntuaInmuebleNOPuede() throws Exception {
		Reserva reserva = new Reserva();
		CategoriaDePuntaje category = new CategoriaDePuntaje();
		usuario.puntuarPropietario(reserva, 5, category);
		
		try {
			usuario.puntuarInmueble(reserva, 5, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> 		usuario.puntuarInmueble(reserva, 5, category));
		}
	}
	@Test
	void testUnUsuarioRechazaUnaReserva() {
		Publicacion publicacion = new Publicacion(); publicacion.setPropietario(usuarioP);

		Reserva reserva = new Reserva(); reserva.setPublicacion(publicacion); reserva.setUsuarioInteresado(usuario);
		usuario.rechazarReserva(reserva);
		assertTrue(reserva.getEstado().estaCancelado());
	}
	
	
	
	
	

}


