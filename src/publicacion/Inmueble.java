package publicacion;

import java.util.List;

import Reserva.CategoriaDePuntaje;
import Reserva.Puntaje;
import Sitio.Usuario;

public class Inmueble {

	private List<Puntaje> puntajes;

	public void registrarPuntajeDe(Usuario usuarioInteresado, Integer puntos, CategoriaDePuntaje categoria) {
		Puntaje puntajeRecibido =  new Puntaje(usuarioInteresado, puntos, categoria);
		this.getPuntajes().add(puntajeRecibido);
	}

	private List<Puntaje> getPuntajes() {
		return (this.puntajes);
	}

}
