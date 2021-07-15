package politicaDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.Reserva;
import Sitio.*;

class PoliticaDeCancelacionTestCase {
	/**
	 * @author bian
	 * Aclaro que tengo varios sut porque elegí testear todas los 
	 * ConcretStrategy del InterfaceStrategy PoliticaDeCancelacion testeados en un mismo TestCase y los considero SUT
	 */
	PoliticaDeCancelacion gratuita; //SUT
	PoliticaDeCancelacion simple;   //SUT
	PoliticaDeCancelacion intermedia; //SUT
	Reserva reserva; //DOT 
	Usuario usuario; //DOT
	@BeforeEach
	void setUp() throws Exception {
		gratuita = new PoliticaDeCancelacionGratuita();
		simple = new PoliticaDeCancelacionSimple();
		intermedia = new PoliticaDeCancelacionIntermedia();
		usuario = new Usuario(); 
		reserva  = mock(Reserva.class);
		
	}
	

	@Test
	void testCostoPoliticaDeCancelacionGratuitaDiezDiasAntes() {
		when(reserva.getPrecioDePublicacionPorDia()).thenReturn(1.0);
		when(reserva.getUsuarioInteresado()).thenReturn(usuario);
		when(reserva.fueCreadaConDiasDeAnterioridad(10)).thenReturn(false);
		gratuita.cobrar(reserva);
		assertEquals( 0, usuario.getDeudaPendiente()  );
		
	}
	
	@Test
	void testCostoPoliticaDeCancelacionGratuitaParaPubli() { 
		when(reserva.getPrecioDePublicacionPorDia()).thenReturn(1.0);
		when(reserva.getUsuarioInteresado()).thenReturn(usuario);
		when(reserva.fueCreadaConDiasDeAnterioridad(10)).thenReturn(true);
		gratuita.cobrar(reserva);
		assertEquals( 0,0 , usuario.getDeudaPendiente()  ); //una reserva gratuita no cobra deudas al usuario
		
	}

	@Test
	void testCostoPoliticaDeCancelacionSimpleParaPubli() {
		when(reserva.getPrecioDePublicacionPorDia()).thenReturn(1.0);
		when(reserva.getDiasDeReserva()).thenReturn((long) 2);
		when(reserva.getUsuarioInteresado()).thenReturn(usuario);
		
		simple.cobrar(reserva);
		assertEquals( 2,0 , usuario.getDeudaPendiente()  );
	}
	
	@Test 	
	void testCostoPoliticaDeCancelacionIntermediaParaPubli20DiasAntes() {
		when(reserva.getPrecioDePublicacionPorDia()).thenReturn(1.0);
		when(reserva.getDiasDeReserva()).thenReturn((long) 2);
		when(reserva.fueCreadaConDiasDeAnterioridad(20)).thenReturn(true);

		when(reserva.getUsuarioInteresado()).thenReturn(usuario);
		
		intermedia.cobrar(reserva);
		assertEquals( 0.0 , usuario.getDeudaPendiente()  );
	}
	
	@Test 	
	void testCostoPoliticaDeCancelacionIntermediaParaPubli50Porciento() {
		when(reserva.getPrecioDePublicacionPorDia()).thenReturn(1.0);
		when(reserva.getDiasDeReserva()).thenReturn((long) 2);
		when(reserva.fueCreadaConDiasDeAnterioridad(19)).thenReturn(true);

		when(reserva.getUsuarioInteresado()).thenReturn(usuario);
		
		intermedia.cobrar(reserva);
		assertEquals(1.0, usuario.getDeudaPendiente());
	}
	
	@Test 	
	void testCostoPoliticaDeCancelacionIntermediaParaPubliTotalidad() {
		when(reserva.getPrecioDePublicacionPorDia()).thenReturn(1.0);
		when(reserva.getDiasDeReserva()).thenReturn((long) 2);
		when(reserva.fueCreadaConDiasDeAnterioridad(9)).thenReturn(true);

		when(reserva.getUsuarioInteresado()).thenReturn(usuario);
		
		intermedia.cobrar(reserva);
		assertEquals(2.0, usuario.getDeudaPendiente());
	}	
	
	
	
	
}
