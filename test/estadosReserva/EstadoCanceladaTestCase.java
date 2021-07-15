package estadosReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.CategoriaDePuntaje;
import Reserva.Reserva;
import publicacion.Publicacion;

class EstadoCanceladaTestCase {
	EstadoCancelada estadoCancelada;
	Reserva reserva;
	CategoriaDePuntaje category;
	
	@BeforeEach
	void setUp() throws Exception {
	
		estadoCancelada = new EstadoCancelada();
		reserva = mock(Reserva.class);
		category = mock(CategoriaDePuntaje.class);
	}

	@Test
	void testUnEstadoCanceladoEstaCancelado() {
		assertTrue(estadoCancelada.estaCancelado());
	}
	
	@Test
	void testUnEstadoCanceladoNoEstaConcretado() {
		assertTrue(!estadoCancelada.estaConcretado());
	}

	
	@Test
	void testUnEstadoCanceladoNoEstaEsperandoAprobacion() {
		assertTrue(!estadoCancelada.estaEsperandoAprobacion());
	}

	@Test
	void testUnEstadoCanceladoNOPuedeGenerarPuntajes() {
		try {
			estadoCancelada.puntuarInmueble(reserva, 4, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> estadoCancelada.puntuarInmueble(reserva, 4, category) );

		}
		try {
			estadoCancelada.puntuarUsuario(reserva, 4, category);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> estadoCancelada.puntuarUsuario(reserva, 4, category) );

		}
	}
	@Test
	void testUnEstadoCanceladoNOPuedeConsolidarseEnElSitio() {
		try {
			estadoCancelada.consolidarEnElSitio(reserva);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> estadoCancelada.consolidarEnElSitio(reserva));

		}
	}

	@Test
	void testUnEstadoCanceladoCancelaLaReserva() {
	    Publicacion publi = mock(Publicacion.class);
		when(publi.getReservasEncoladas()).thenReturn(new ArrayList<Reserva>() );
	    publi.encolarReserva(reserva); 
			
		when(reserva.getPublicacion()).thenReturn(publi);
		
		estadoCancelada.cancelar(reserva);
		
		assertFalse(publi.registra(reserva));
	}
	
	@Test
	void testUnEstadoCanceladoNoPuedeSerRechazadoPorqueYAEstáEliminado() {
		try {
			estadoCancelada.rechazada(reserva);
		} catch (Exception errorNoPuedeConcretarse) {
			assertThrows(Exception.class,() -> estadoCancelada.rechazada(reserva));

		}
	}
	
	
	
	
}
