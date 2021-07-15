package estadosReserva;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Reserva.Reserva;
import Sitio.Usuario;
import publicacion.Publicacion;

class EstadoConcretadaTestaCase {
	EstadoConcretada estadoConcretado;

	@BeforeEach
	void setUp() throws Exception {
		estadoConcretado = new EstadoConcretada();
		
	}
	@Test
	void testUnEstadoConcretadoNoEstaCancelado() {
		assertTrue(!estadoConcretado.estaCancelado());
	}
	
	@Test
	void testUnEstadoConcretadoNoEstaEsperandoAprobacion() {
		assertTrue(!estadoConcretado.estaEsperandoAprobacion());
	}
	@Test
	void testUnEstadoConcretadoEstaConcretado() {
		assertTrue(estadoConcretado.estaConcretado());
	}
	
	@Test
	void testUnEstadoConcretadoPermiteEmitirPuntajes() {
		Reserva reserva =mock(Reserva.class);
		when(reserva.propietarioDePublicacion()).thenReturn(new Usuario());
		CategoriaDePuntaje category = mock(CategoriaDePuntaje.class);
		estadoConcretado.puntuarUsuario(reserva, 4, category);
		Puntaje puntajeEsperado= new Puntaje(reserva.propietarioDePublicacion(), 4, category);
		assertEquals(puntajeEsperado, reserva.propietarioDePublicacion().getPuntajes().get(0));
	}
	
	
	
}
