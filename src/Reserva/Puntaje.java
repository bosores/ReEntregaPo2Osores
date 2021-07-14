package Reserva;

import Sitio.Usuario;

public class Puntaje {

	private Usuario usuario;
	private CategoriaDePuntaje categoria;
	

	public Puntaje(Usuario propietario, int valor, CategoriaDePuntaje category) {
		
	}

	public CategoriaDePuntaje getCategoria() {
		return this.categoria;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

}
