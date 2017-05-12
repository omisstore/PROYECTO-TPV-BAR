package estructura.enumeraciones;

public enum Tamanio {
	ML200("200ml"),
	L05("0.5L"),
	L1("1L"),
	ML105("105ml");
	
	private String tamanio;
	
	private Tamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	
	/**
	 * @return valor del tamanio.
	 * */
	public String valor(){
		return this.tamanio;
	}
}
