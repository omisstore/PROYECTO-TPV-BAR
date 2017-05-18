package estructura.enumeraciones;

public enum TipoBebida {
	NORMAL(0.0F),
	ALCHOLICA(0.10F);
	
	private float impuesto;
	private TipoBebida(float impuesto) {
		this.impuesto =  impuesto;
	}
	
	/**
	 * @return valor del impuesto.
	 * */
	public float impuesto(){
		return this.impuesto;
	}
}
