package sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.Reserva;
import Sitio.SitioWeb;
import Sitio.Usuario;
import estadosReserva.Estado;
import publicacion.FormaDePago;
import publicacion.Inmueble;
import publicacion.OcupacionDeInmueble;
import publicacion.Publicacion;

class SitioTestCase {

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
		sitio =  new SitioWeb();
		 propietario = new Usuario(); propietario.registrarse(sitio);
		 interesado = new Usuario();  interesado.registrarse(sitio);
		
		publicacion = mock(Publicacion.class);
		formaDePago = mock(FormaDePago.class);
		
				
		reserva = new Reserva(usuarioInteresado, publicacion, formaDePago, checkIn, checkOut);
		
	}


	@Test
	void testSitioWebRegistraUnaOcupacion() {
		OcupacionDeInmueble ocupacion = mock(OcupacionDeInmueble.class);
		when(ocupacion.pertenece(reserva)).thenReturn(true);
		sitio.getOcupaciones().add(ocupacion);
		
		assertTrue(sitio.registraOcupacionDe(reserva) );	
		}
	
	
	@Test
	void testGeneraOcupacionDeReserva() {
		Inmueble inmueble = mock(Inmueble.class);
		Publicacion  publi = new Publicacion();
		publi.setInmueble(inmueble);
		reserva.setPublicacion(publi);
		OcupacionDeInmueble ocupacionEsperada = new OcupacionDeInmueble(reserva);
		sitio.generarOcupacionDeReserva(reserva);
		assertFalse(sitio.getOcupaciones().isEmpty());
		assertTrue(sitio.registraOcupacionDe(reserva));
	}
	
	
	@Test
	void registrarUaReserva() {

		Publicacion  publi = new Publicacion();
		sitio.registrarReserva(usuarioInteresado, publi, formaDePago, checkIn, checkOut);

		assertFalse(publi.getReservasEncoladas().isEmpty() );
	}
	
	@Test
	void cancelarReserva() {
		
	}
	
}
