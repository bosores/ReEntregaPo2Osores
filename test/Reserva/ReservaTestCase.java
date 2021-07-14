package Reserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Reserva.Reserva;
import Sitio.Usuario;
import estadosReserva.Estado;
import publicacion.*;



class ReservaTestCase {
	Reserva reserva;
	Usuario usuarioInteresado;
	Publicacion publicacion;
	FormaDePago formaDePago;
	LocalDate checkIn;
	LocalDate checkOut;
	Estado estado;
	
	
	@BeforeEach
	void setUp() throws Exception {
		usuarioInteresado = mock(Usuario.class);
		publicacion = mock(Publicacion.class);
		formaDePago = mock(FormaDePago.class);
		
				
		reserva = new Reserva(usuarioInteresado, publicacion, formaDePago, checkIn, checkOut);
		
	}

	@Test
	void testReserva() {
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
