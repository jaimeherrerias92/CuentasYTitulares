/**
 * 
 */
package gestionCuenta;

import utiles.ExpresionesRegulares;

/**
 * @author Jaime Herrerias
 * @version 1.0
 *
 */
public class Persona {
	private ExpresionesRegulares expresionDni = new ExpresionesRegulares();
	private static final char[] LETRASDNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
			'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' };
	private String nombre;
	private String dni;
	private String direccion;

	public Persona(String nombre, String dni, String direccion) throws DniInvalidoExceptions {
		this.nombre = nombre;
		setDni(dni);
		setDireccion(direccion);

	}

	public Persona(String dni) throws DniInvalidoExceptions {
		setDni(dni);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equalsIgnoreCase(other.dni))
			return false;
		return true;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws DniInvalidoExceptions {
		dni=dni.trim().toUpperCase();
		if (!ExpresionesRegulares.comprobrarDNI(dni))
			throw new DniInvalidoExceptions("Formato DNI no valido. Debe de ser 00000000A");
		else if (!dniLetraValida(dni.toUpperCase()))
			throw new DniInvalidoExceptions("Letra invalida");
		else
			this.dni = dni.toUpperCase();

	}

	/**
	 * @param dni2
	 * @return
	 */
	private boolean dniLetraValida(String dni) {
		System.out.println();
		return (dni.charAt((dni.length()-1)) == LETRASDNI[(Integer.parseInt(dni.substring(0, 8))) % 23]);

	}

	@Override
	public String toString() {
		return  dni + ", " + nombre + ", " + direccion;
	}
}
