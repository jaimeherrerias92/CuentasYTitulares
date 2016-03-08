
package utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ExpresionesRegulares {
	private static final String DNI="^\\d{8}[- ]?[a-zA-Z&&[^IOUiou]]$";
	private	static final String CUENTA= "^ES[0-9]{2}/[0-9]{4}/[0-9]{4}/[0-9]{4}/[0-9]{4}$";
	private static final String IP="^(((((1[0-9]{2})|2[0-4][0-9]|25[0-5])|([1-9][0-9])|[0-9]))\\.){3}((((1[0-9]{2})|2[0-4][0-9]|25[0-5])|([1-9][0-9])|[0-9]))$";
	private static final String CODIGOPOSTAL="^([0-4][1-9]\\d{3})|(5[0-2]\\d{3})$";
	private static final String CORREO=	"^[a-zA-Z0-9_]+@([a-zA-Z]+\\.)+(com|es|org)$";
	private static final String MAC ="((([0-9]|[A-Z]){2}):){4}(([0-9]|[A-Z]){2})";
	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobrarCuentaCorriente(String string) {
		Pattern p = Pattern.compile(CUENTA);
		Matcher m = p.matcher(string);
		return m.matches();
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobrarDNI(String string) {
		Pattern p = Pattern.compile(DNI );
		Matcher m = p.matcher(string);
		return m.matches();
		
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobarCodigoPostal(String string) {
		// "^([0-4][1-9]\\d{3})|(5[0-2]\\d{3})$"
		Pattern p = Pattern.compile(CODIGOPOSTAL);
		Matcher m = p.matcher(string);
		return m.matches();
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static  boolean comprobarCorreo(String string) {
		Pattern p = Pattern.compile(CORREO);
		Matcher m = p.matcher(string);
		return m.matches();
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobarIp(String string) {
		
		Pattern p = Pattern.compile(IP);
		Matcher m = p.matcher(string);
		return m.matches();
	}

	public static boolean comprobarMac(String string) {
		Pattern p = Pattern.compile(MAC);
		Matcher m = p.matcher(string);
		return m.matches();
	}
}
