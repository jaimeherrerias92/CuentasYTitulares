/**
 * 
 */
package gestionCuenta;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author Jaime Herrerias
 * @version 1.0
 *
 */
public class Banco {
	private ArrayList<Persona> listaPersona = new ArrayList<Persona>();
	private ArrayList<CuentaCorriente> cuentas = new ArrayList<CuentaCorriente>();

	/**
	 * Añade personas
	 * 
	 * @param nombre
	 * @param dni
	 * @param direccion
	 * @return
	 * @throws DniInvalidoExceptions
	 */
	public boolean addPersona(String nombre, String dni, String direccion) throws DniInvalidoExceptions {
		if (!listaPersona.contains(new Persona(dni)))
			return listaPersona.add(new Persona(nombre, dni, direccion));
		return false;
	}

	/**
	 * Añade las cuentas
	 * 
	 * @param saldo
	 * @param dni
	 * @return
	 * @throws DniInvalidoExceptions
	 * @throws SaldoInvalidoException
	 * @throws ListaVaciaException
	 */
	public boolean addCuentas(double saldo, String dni)
			throws DniInvalidoExceptions, SaldoInvalidoException, ListaVaciaException {
		Persona titularCuenta = obtenerPersona(dni);
		if (titularCuenta == null)
			return false;
		return cuentas.add(new CuentaCorriente(saldo, titularCuenta));
	}

	/**
	 * @param dni
	 * @return
	 * @throws DniInvalidoExceptions
	 * @throws ListaVaciaException
	 */
	private Persona obtenerPersona(String dni) throws DniInvalidoExceptions, ListaVaciaException {
		// comprobarListadoLleno();
		if (listaPersona.isEmpty())
			throw new ListaVaciaException("La lista esta vacia");
		if (listaPersona.contains(new Persona(dni)))
			return listaPersona.get(listaPersona.indexOf(new Persona(dni)));
		return null;

	}

	public String eliminarCuenta(int id) throws SaldoInvalidoException, ListaVaciaException, NumerosRojosException {
		String cadena = "";
		CuentaCorriente cuenta = buscarCuentaId(id);
		if (cuenta == null)
			return "El id " + id + " no es valido";
		cadena+= reintegro(cuenta.getSaldo(), id)+"\n";
		cuentas.remove(new CuentaCorriente(id));
		cadena+="cuenta eliminada con exito";
		return cadena;
	}

	/**
	 * Muestra las cuentas sin iterator
	 * 
	 * @return
	 */
	public String mostrarCuentas() {
		if (cuentas.isEmpty())
			return "No hay cuentas para mostrar";
		// /*Con ListIterator*/
		// String cadena ="";
		// ListIterator it = cuentas.listIterator();
		// if (cuentas.isEmpty())
		// cadena += "No hay personas";
		//
		// while (it.hasNext())
		// cadena += it.next() + "\n";
		// return cadena;
		return cuentas.toString();
	}

	/**
	 * Muestra las cuentas con iterator
	 * 
	 * @return
	 */

	public String mostrarPersonas() {
		String cadena = "";
		ListIterator<Persona> it = listaPersona.listIterator();
		if (listaPersona.isEmpty())
			cadena += "No hay personas";
		else {
			while (it.hasNext())
				cadena += it.next() + "\n";
		}
		return cadena;

	}

	/**
	 * Realiza la transferencia entre dos cuentas
	 * 
	 * @param idCuentaEnvia
	 * @param idCuentaRecibe
	 * @param cantidad
	 * @return cadena si ha sido exitosa o con el error
	 * @throws SaldoInvalidoException
	 * @throws ListaVaciaException 
	 * @throws NumerosRojosException 
	 */
	public String transferencia(int idCuentaEnvia, int idCuentaRecibe, double cantidad) throws SaldoInvalidoException, ListaVaciaException, NumerosRojosException {
		String cadena = "";
		if (idCuentaEnvia == idCuentaRecibe)
			return "Son la misma cuenta";
		CuentaCorriente cuentaEnvia = buscarCuentaId(idCuentaEnvia);
		CuentaCorriente cuentaRecibe = buscarCuentaId(idCuentaRecibe);
		if (cuentaRecibe == null)
			cadena += "El id " + idCuentaRecibe + " de la cuenta no es valido \n";
		if (cuentaEnvia == null)
			cadena += "El id " + idCuentaEnvia + " de la cuenta no es valido";

		if(cadena.length() == 0 ) {
			cuentaEnvia.transferencia(cantidad, cuentaRecibe);
			cadena+="Transferencia realizada con exito";
		}
			
				
		
		return cadena;
	}

	/**
	 * @return nos devuelve la cuentea buscada por id, si no existe devuelve
	 *         null
	 * @throws ListaVaciaException 
	 */
	private CuentaCorriente buscarCuentaId(int id) throws ListaVaciaException {
		if(cuentas.isEmpty())
			throw new ListaVaciaException("Lista vacia");
		if (cuentas.contains(new CuentaCorriente(id)))
			return cuentas.get(cuentas.indexOf(new CuentaCorriente(id)));
		return null;
	}

	/**
	 * reintegro de la cuenta
	 * 
	 * @param cantidad
	 * @param id
	 * @return
	 * @throws SaldoInvalidoException
	 * @throws ListaVaciaException 
	 * @throws NumerosRojosException 
	 */
	public String reintegro(double cantidad, int id) throws SaldoInvalidoException, ListaVaciaException, NumerosRojosException {
		CuentaCorriente cuenta = buscarCuentaId(id);
		if (cuenta == null)
			return "El id " + id + " de la cuenta no es valido \n";
		else{
			cuenta.reintegro(cantidad);
			return "Reintegro de "+  cantidad +" realizado con existo";
		}
			
	

	}

	/**
	 * Cambia la direccion de una persona ya registrada
	 * 
	 * @param direccion
	 * @param dni
	 * @return
	 * @throws DniInvalidoExceptions
	 * @throws ListaVaciaException
	 */
	public boolean cambiarDireccion(String direccion, String dni) throws DniInvalidoExceptions, ListaVaciaException {

		Persona persona = obtenerPersona(dni);
		if (persona == null)
			return false;
		persona.setDireccion(direccion);
		return true;
	}

	/**
	 * @param leerDecimal
	 * @param leerEntero
	 * @return
	 * @throws SaldoInvalidoException
	 * @throws ListaVaciaException 
	 */
	public String ingreso(double cantidad, int id) throws SaldoInvalidoException, ListaVaciaException {
		CuentaCorriente cuenta = buscarCuentaId(id);
		if (cuenta == null)
			return "El id " + id + " de la cuenta no es valido \n";
		else{
			cuenta.ingreso(cantidad);
			return "Ingreso realizado correctamente";
		}
		
	}

}
