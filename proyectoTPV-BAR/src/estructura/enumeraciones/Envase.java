package estructura.enumeraciones;

public enum Envase {
	LATA(Tamanio.ML200),
	BOTELLAmL(Tamanio.L05),
	BOTELLALt(Tamanio.L1),
	COPA(Tamanio.ML105);
	
	private String tamanio;
	
	private Envase(Tamanio tamanio) {
		this.tamanio =  tamanio.valor();
	}
	/**
	 * @return tamanio del envase
	 * */
	public String tamanio(){
		return this.tamanio;
	}
	
}	
