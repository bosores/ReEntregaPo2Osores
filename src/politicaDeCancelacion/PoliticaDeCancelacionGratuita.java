package politicaDeCancelacion;

import testUsuario.Reserva;

public class PoliticaDeCancelacionGratuita implements PoliticaDeCancelacion {

	public PoliticaDeCancelacionGratuita() {
	}

	@Override
	public void cobrar(Reserva reserva) {
		reserva.getUsuarioInteresado().incorporarDeuda(0);
	}


}
