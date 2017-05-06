package estructura.enumeraciones;

public enum TipoPescado {
	BLANCO(0.10F),
	AZUL(0.05F);
	
	private float impuesto;
	private TipoPescado(float impuesto) {
		this.impuesto = impuesto;
	}
	
	/**
	 * @return impuesto del pescado
	 * */
	public float impuesto(){
		return this.impuesto;
	}
}
