/**
 * 
 */
package gestionCuenta;



/**
 * @author Jaime Herrerias
 * @version 1.0
 *
 */
public class CuentaCorriente {

	private double saldo;
	private Persona titularCuenta;
	private int cuentaId;
	private static int contador;

	/**
	 * @param contadorcuenta
	 * @throws DniInvalidoExceptions
	 * @throws SaldoInvalidoException
	 * 
	 */
	public CuentaCorriente(double saldoInicial, Persona titular) throws DniInvalidoExceptions, SaldoInvalidoException {
		setSaldo(saldoInicial);
		titularCuenta = titular;
		this.cuentaId = ++contador;

	}

	public CuentaCorriente(int id) {
		this.cuentaId = id;
	}

	@Override
	public String toString() {
		return "Cuenta : " + cuentaId + ", " + saldo + titularCuenta + ".\n";
	}

	/**
	 * Cambia la direccion
	 * 
	 * @param nuevaDireccion
	 */
	public void direccion(String nuevaDireccion) {
		titularCuenta.setDireccion(nuevaDireccion);
	}

	/**
	 * nos devuelve el saldo inicial
	 * 
	 * @return
	 */
	public double getSaldo() {
		return saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cuentaId;
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
		CuentaCorriente other = (CuentaCorriente) obj;
		if (cuentaId != other.cuentaId)
			return false;
		return true;
	}

	/**
	 * El usuario nos da el importe y se incrementa al saldo de la cuenta
	 * corriente
	 * 
	 * @param ingreso
	 *            cantidad que se desea ingresar
	 * @return true si se ha incrementado, false en el caso de ser negativo
	 */
	public void ingreso(double ingreso) throws SaldoInvalidoException {
		if (ingreso < 0)
			throw new SaldoInvalidoException("La cantidad no puede ser negativa");
		else 
			setSaldo(getSaldo()+ingreso);
		
	}

	/**
	 * Transferencia entre dos cuentas
	 * 
	 * @param cantidad
	 *            lo que se va a transferir
	 * @param cuenta
	 *            a donde vas a mandar el dinero
	 * @return true si se ha podido realizar
	 * @throws NumerosRojosException 
	 */
	public void transferencia(double cantidad, CuentaCorriente cuenta) throws SaldoInvalidoException, NumerosRojosException {
		if (cantidad < 0)
			throw new SaldoInvalidoException("La cantidad transferida no puede ser negativo");
		else if (cantidad >= getSaldo())
			throw new NumerosRojosException("No puedes quedar con numeros rojos");
		else{
			reintegro(cantidad);
			cuenta.ingreso(cantidad);
		}
			

	}

	/**
	 * Saca dinero de la cuenta, no puede estar nunca en rojos.
	 * 
	 * @param reintegro
	 * @return true si se ha podido realizar
	 * @throws SaldoInvalidoException 
	 */
	public void reintegro(double reintegro) throws NumerosRojosException, SaldoInvalidoException {
		if (reintegro < 0)
			throw new SaldoInvalidoException("La cantidad transferida no puede ser negativo");
		else if (reintegro > getSaldo())
			throw new NumerosRojosException("No puedes quedar en rojos");
		else
			setSaldo(getSaldo()-reintegro);;
		
	}

	/**
	 * nos devuelve el saldo de la cuenta actual
	 * 
	 * @param saldo
	 */
	public void setSaldo(double saldo) throws SaldoInvalidoException {
		
		if (saldo < 0)
			throw new SaldoInvalidoException("El saldo no puede ser negativo");
		else 
			this.saldo = saldo;
	}
}
