package sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import Sitio.Usuario;
import politicaDeCancelacion.PoliticaDeCancelacion;
import politicaDeCancelacion.PoliticaDeCancelacionGratuita;
import publicacion.Efectivo;
import publicacion.FormaDePago;
import publicacion.Publicacion;

class UsuarioTestCase {

		Usuario usuario;
		SitioWeb sitio; //DOT
		
	@BeforeEach
	void setUp() throws Exception {
		
		usuario = new Usuario();
		usuario.setNombre("Teresa");
		usuario.setEmail("teresa@gmail.com");
		usuario.setTelefono(123456);
		
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
	void testUsuarioCancelaReserva() {
	
		
		
	}
	
	
	

}


