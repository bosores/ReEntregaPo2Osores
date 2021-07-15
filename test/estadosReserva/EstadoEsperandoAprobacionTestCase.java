package estadosReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.CategoriaDePuntaje;
import Reserva.Reserva;
import Sitio.SitioWeb;
import Sitio.Usuario;
import publicacion.Publicacion;

class EstadoEsperandoAprobacionTestCase {
	EstadoEsperandoAprobacion estadoEsperandoAprobacion;
	Reserva reserva;
	CategoriaDePuntaje category;
	@BeforeEach
	void setUp() throws Exception {
		estadoEsperandoAprobacion = new EstadoEsperandoAprobacion();
		reserva = mock(Reserva.class); 
		category = mock(CategoriaDePuntaje.class);
	}

	@Test
	void testUnEstadoEsperandoAprobacionNoEstaCancelado() {
		assertTrue(!estadoEsperandoAprobacion.estaCancelado());
	}

	@Test
	void testUnEstadoEsperandoAprobacionNoEstaConcretada() {
		assertTrue(!estadoEsperandoAprobacion.estaConcretado());
	}

	@Test
	void testEstadoEsperandoAprobacionEstaEsperandoPorAceptacion() {
		assertTrue(estadoEsperandoAprobacion.estaEsperandoAprobacion());
		
		
	}

	@Test
	void testEstadoEsperandoAprobacionNoPuedeGenerarPuntajes() {
		try {
			estadoEsperandoAprobacion.puntuarInmueble(reserva, 4, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> estadoEsperandoAprobacion.puntuarInmueble(reserva, 4, category) );

		}
		try {
			estadoEsperandoAprobacion.puntuarUsuario(reserva, 4, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> estadoEsperandoAprobacion.puntuarUsuario(reserva, 4, category) );

		}		
		
	}

	@Test
	void testEstadoEsperandoAprobacionPuedeSerConsolidadaEnElSitio() {
		Publicacion publi = mock(Publicacion.class);
		Usuario usuario = mock(Usuario.class);
		when(usuario.getSitio()).thenReturn(new SitioWeb());
		when(publi.getPropietario()).thenReturn(usuario);
		
		Reserva reserva = new Reserva();
		reserva.setPublicacion(publi);
		reserva.getEstado().consolidarEnElSitio(reserva);// el estado se cambia automaticamente a concretado
		estadoEsperandoAprobacion.consolidarEnElSitio(reserva);
		assertTrue(reserva.getEstado().estaConcretado());

	}
	
	@Test
	void testEstadoEsperandoAprobacionPuedeSerRechazado() {
		Publicacion publi = mock(Publicacion.class); 
		Usuario usuario = mock(Usuario.class); Usuario usuarioInteresado = mock(Usuario.class); 
		when(usuario.getSitio()).thenReturn(new SitioWeb());
		when(publi.getPropietario()).thenReturn(usuario);
		
		Reserva reserva = new Reserva();
		reserva.setUsuarioInteresado(usuarioInteresado);
		reserva.setPublicacion(publi);
		estadoEsperandoAprobacion.propietarioRechaza(reserva);
		assertTrue(reserva.getEstado().estaCancelado() );
	}
	
	
	


}
