package tui.utiles;
/**
*Crea la clase Menu. Metelo en el paquete utiles. Implementa al menos los siguientes
*metodos y atributos:
*<ul>
*	<li>titulo</li>
*	<li>opciones</li>
*	<li>numOpciones</li>
*	<li>gestionar()</li>
*	<li>recogerOpcion()</li>
*</ul>
*/

public class Menu {
	/**
	 * Es el campo que almacenara el titulo que indique el usuario
	 * */
	private String titulo;
	/**
	 * Es el vector que almacenara las opciones posibles que ha indicado el usuario
	 * */
	private String[]opciones;
	/**
	 * Es el campo que almacenara la opcion de salida que en este caso sera la longitud del vector
	 * */
	private int opcionSalida;
	
	/**
	 * Es el constructor de la clase 
	 * @param titulo, cotiene el titulo que ha indicado el usuario
	 * @param []opciones,contiene todas las opciones posibles elegidas por el usuario
	 * */
	public Menu(String titulo,String[] opciones){
		setTitulo(titulo);
		setOpciones(opciones);
		setSALIR(opciones.length);
	}
	
	public Menu(String titulo, Object[] array) {
		this.titulo = titulo;
		this.opciones = getOpciones(array);
		setSALIR(opciones.length);
	}
	
	public static String[] getOpciones(Object[] array) {
		String[] vector = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			vector[i] = array[i].toString();
		}
		
		return vector;
	}
	
	/**
	 * asignara el valor que introduzcamos por el parametro al campo de la clase
	 * @param opcionSalida,contiene la longitud del vector con todas las opciones
	 * */
	private void setSALIR(int opcionSalida){
		this.opcionSalida = opcionSalida;
	}
	
	/**
	 * @return la opcion salida que sera la longitud del vector con todas las opciones 
	 * */
	public int getSALIR(){
		return opcionSalida;
	}
	
	/**
	 * @param []opciones, contiene el vector con todas las opciones
	 * */
	private void setOpciones(String[] opciones){
		this.opciones = opciones;
	}
	
	/**
	 *  asignamos la cadena pasada por parametro al campo de la clase
	 * @param titulo, contiene la cadena pasada por parametro
	 * */
	private void setTitulo(String titulo) {
		this.titulo  = titulo;
	}
	
	/**
	 * @return la cadena que contiene el titulo
	 * */
	public String getTitulo() {
		return titulo+"\n";
	}

	
	public int recogerOpcion(){
		int opcion;
		do{
			opcion = Teclado.leerEntero("Indica la opcion entre 1 y "+getSALIR());
		}while(!esValida(opcion));
		return opcion;
	}
	
	public int gestionar() {
		System.out.print(mostrar());
		return recogerOpcion();
	}
	
	private int getPRIMERAOPCION(){
		return 1;
	}
	
	private boolean esValida(int opcion){
		if(opcion<getPRIMERAOPCION() || opcion > getSALIR())
			return false;
		return true;
		
	}
	
	public String mostrar(){
		String opcion="";
		for(int i=0;i<opciones.length;i++){
			opcion += (i+1)+"-"+opciones[i]+"\n";
		}
		return getTitulo()+opcion;
	}
	
	
	
}