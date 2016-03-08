
package utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ExpresionesRegulares {
	static final private Pattern patternMatricula = Pattern
			.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");
	static final private Pattern dniPattern = Pattern
			.compile("^\\d{8}[- ]?[a-zA-Z&&[^IOUiou]]$");
	static final private Pattern ipPattern = Pattern
			.compile("^(((((1[0-9]{2})|2[0-4][0-9]|25[0-5])|([1-9][0-9])|[0-9]))\\.){3}((((1[0-9]{2})|2[0-4][0-9]|25[0-5])|([1-9][0-9])|[0-9]))$");
	static final private Pattern cuentaCorrientePattern = Pattern
			.compile("^ES[0-9]{2}/[0-9]{4}/[0-9]{4}/[0-9]{4}/[0-9]{4}$");
	static final private Pattern codigoPostalPattern = Pattern
			.compile("^([0-4][1-9]\\d{3})|(5[0-2]\\d{3})$");
	static final private Pattern correoElectronicoPattern = Pattern
			.compile("^[a-zA-Z0-9_]+@([a-zA-Z]+\\.)+(com|es|org)$");
	static final private Pattern macPattern = Pattern
			.compile("((([0-9]|[A-Z]){2}):){4}(([0-9]|[A-Z]){2})");
	

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobrarCuentaCorriente(String string) {
		return cuentaCorrientePattern.matcher(string).matches();
	}
	
	
	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobrarMatricula(String string) {
		return patternMatricula.matcher(string).matches();
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobrarDNI(String string) {
		return ipPattern.matcher(string).matches();
		
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobarCodigoPostal(String string) {
		return codigoPostalPattern.matcher(string).matches();
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static  boolean comprobarCorreo(String string) {
		return correoElectronicoPattern.matcher(string).matches();
	}

	/**
	 * @param leerCadena
	 * @return
	 */
	public static boolean comprobarIp(String string) {
		return dniPattern.matcher(string).matches();
	}

	public static boolean comprobarMac(String string) {
		return macPattern.matcher(string).matches();
	}
}
