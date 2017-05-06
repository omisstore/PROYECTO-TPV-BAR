package tui.utiles;

public class DeseaContinuar {
	
	public static boolean deseaContinuar(String msg){
		switch (Teclado.leerCaracter(msg)) {
		case 'n':
		case 'N':
			return false;
		default:
			return true;
		}
	}

}
