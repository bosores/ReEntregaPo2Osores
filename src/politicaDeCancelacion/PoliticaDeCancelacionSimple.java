package politicaDeCancelacion;

import testUsuario.Reserva;

public class PoliticaDeCancelacionSimple implements PoliticaDeCancelacion {

	public PoliticaDeCancelacionSimple() {
	}

	@Override
	public void cobrar(Reserva reserva) {
		double deuda = reserva.getDiasDeReserva()*reserva.getPrecioDePublicacionPorDia();
		reserva.getUsuarioInteresado().incorporarDeuda(deuda);
		
	}

	

}
