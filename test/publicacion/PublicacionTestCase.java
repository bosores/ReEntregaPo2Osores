package publicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testUsuario.FormaDePago;
import testUsuario.Reserva;
import testUsuario.Usuario;

class PublicacionTestCase {
/**
 * @author bian 
 *  reconzco que la publicación tiene muchas otras responsabilidades pero para testear la parte de reservar y cancelar 
 *  reservas solo me interesa testear el correcto funcionamiento de los casos a continuación
 *    
 */
	Publicacion publicacion;
	Usuario propietario; 
	Inmueble inmueble; 
	FormaDePago efectivo;
	Comentario comentario;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
