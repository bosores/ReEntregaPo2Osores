package politicaDeCancelacion;

import Reserva.Reserva;

public class PoliticaDeCancelacionIntermedia implements PoliticaDeCancelacion {

	public PoliticaDeCancelacionIntermedia() {
	}

	@Override
	public void cobrar(Reserva reserva) {
		reserva.getUsuarioInteresado().incorporarDeuda(this.total(reserva));

	}
	
	private double total (Reserva reserva) {
		return (this.montoSinCobro(reserva) 
				+montoPorcentualSiCorresponde(reserva)
				+montoTotalSiCorresponde(reserva));
	}


	private int montoSinCobro(Reserva reserva) {
		return 0;
	}
	

	private double montoPorcentualSiCorresponde(Reserva reserva) {
		if(reserva.fueCreadaConDiasDeAnterioridad(19)||
				reserva.fueCreadaConDiasDeAnterioridad(10) ) {
			return( this.calcular50Porciento(reserva));
		}
		return 0;
			 
	}
	
	
	private double montoTotalSiCorresponde(Reserva reserva ) {
		if(reserva.fueCreadaConDiasDeAnterioridad(9) ) {
			return( this.calcularTotal(reserva));
		}
		return( 0);
				
			
	}

	private double calcularTotal(Reserva reserva) {
		return (reserva.getDiasDeReserva()* reserva.getPrecioDePublicacionPorDia() );
	}
	
	private double calcular50Porciento(Reserva reserva) {
		return( reserva.getDiasDeReserva()* reserva.getPrecioDePublicacionPorDia() /2 ) ;
	}
	
	
	}
	
	