package estructura.enumeraciones;

public enum TipoCarne {
	TERNERA(0.15F),
	POLLO(0.13F),
	GANSO(0.14F),
	CERDO(0.12F);
	
	private float impuesto;
	private TipoCarne(float impuesto) {
		this.impuesto =  impuesto;
	}
	
	/**
	 * @return impuesto del tipo de carne
	 * */
	public float impuesto(){
		return this.impuesto;
	}
}
