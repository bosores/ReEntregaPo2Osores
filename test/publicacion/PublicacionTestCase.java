package publicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.Reserva;
import Sitio.Usuario;
import politicaDeCancelacion.PoliticaDeCancelacion;
import politicaDeCancelacion.PoliticaDeCancelacionGratuita;

class PublicacionTestCase {
/**
 * @author bian 
 *  reconzco que la publicación tiene muchas otras responsabilidades con el fin de implementar   
 * reservas y cancelaciones  solo me interesa testear el correcto funcionamiento de los casos a continuación
 *    
 */
	Publicacion publicacion;
	Usuario propietario; 
	Inmueble inmueble; 
	FormaDePago efectivo;
	Comentario comentario;
	LocalDate checkIn ;
	LocalDate checkOut ;
	
	PoliticaDeCancelacion gratuita;
	@BeforeEach
	void setUp() throws Exception {
		
		propietario = mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		efectivo = mock(Efectivo.class);
		comentario = mock(Comentario.class);
		ArrayList <FormaDePago> formasDePago = new ArrayList<FormaDePago>(); formasDePago.add(efectivo);
		 checkIn =  LocalDate.of(2000, 01, 12);
		 checkOut = LocalDate.of(2000, 01, 20);
		gratuita = new PoliticaDeCancelacionGratuita();
		
		publicacion = new Publicacion(propietario, inmueble, formasDePago, checkIn, checkOut,200.0, gratuita);
		
	}

	@Test
	void testPublicacionGetterYSetters() {
		assertEquals(propietario, publicacion.getPropietario()) ;
		assertEquals(inmueble, publicacion.getInmueble()) ;
		assertTrue(publicacion.aceptaFormaDePago(efectivo));
		
		assertEquals(checkIn, publicacion.getCheckIn()) ;
		assertEquals(checkOut, publicacion.getCheckOut()) ;
		assertEquals(200.0, publicacion.getPrecioPorDia()) ;
		assertTrue(publicacion.aceptaFormaDePago(efectivo) );
	}
	
	@Test
	void testsPublicacionGuardaLaReserva() {
		Reserva reserva = mock(Reserva.class);
		
		publicacion.encolarReserva(reserva);
		assertTrue(publicacion.getReservasEncoladas().contains(reserva));
		
	}
	
	@Test
	void testPublicacionCancelaReservaEncolada() {
	
		Reserva reserva = mock(Reserva.class);
		
		publicacion.encolarReserva(reserva);
		publicacion.cancelar(reserva);
		
		assertTrue(publicacion.getReservasEncoladas().isEmpty() );
		
	}
	
	

	
	
	
	
	
	
}
