package testExamen;

import gestionCuenta.Banco;
import gestionCuenta.DniInvalidoExceptions;
import gestionCuenta.ListaVaciaException;
import gestionCuenta.NumerosRojosException;
import gestionCuenta.SaldoInvalidoException;
import utiles.Menu;
import utiles.Teclado;

/**
 * @author Jaime Herrerias
 * @version 1.0
 *
 */
public class TestCuenta {
	static Menu menuGestionCuentas = new Menu(new String[] { "Gestionar cuentas", "Operar con cuentas ya creadas",
			"Mostrar todas las cuentas", "Mostras todas las personas" }, "Menu de cuentas");
	static Menu menuOperaciones = new Menu(new String[] { "Reintegro", "Transferencia", "Ingreso" },
			"Que operacion desea realizar");
	static Menu menuGestion = new Menu(
			new String[] { "Crear titular", "Crear cuenta", "Modificar direccion titular", "Eliminar cuenta" },
			"¿Que desea hacer?");
	static Banco banco = new Banco();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int opcion;
		crearCuentasPersonas();
		do {
			switch (opcion = menuGestionCuentas.gestionaMenu()) {
			case 1:// getionar cuentas
				gestionarCuentas();
				break;

			case 2:// gestionarCuentasYaCreadas
				try {
					gestionarCuentasYaCreadas();
				} catch (ListaVaciaException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println(banco.mostrarCuentas());
				break;
			case 4:
				System.out.println(banco.mostrarPersonas());
				break;

			}

		} while (opcion != 5);

	}

	/**
	 * 
	 */
	private static void crearCuentasPersonas() {
		try {
			banco.addPersona("Jaime", "45944538Z", "Mi casa");
			banco.addPersona("Jose", "12345678Z", "Calle");
			banco.addPersona("Juan", "22445566G", "Avenida");
		} catch (DniInvalidoExceptions e) {
			System.out.println(e.getMessage());
		}
		try {
			banco.addCuentas(500, "45944538Z");
			banco.addCuentas(4000, "12345678Z");
			banco.addCuentas(850, "22445566G");
			banco.addCuentas(3000, "45944538Z");
		} catch (DniInvalidoExceptions | SaldoInvalidoException | ListaVaciaException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void gestionarCuentasYaCreadas() throws ListaVaciaException {
		int opcionMenuOperaciones;
		do {
			switch (opcionMenuOperaciones = menuOperaciones.gestionaMenu()) {
			case 1:
				reintegro();
				break;
			case 2:
				transferencia();
				break;
			case 3:
				ingreso();
				break;
			}
		} while (opcionMenuOperaciones != 4);
	}

	private static void gestionarCuentas() {
		int opcionMenuGestion;
		do {
			switch (opcionMenuGestion = menuGestion.gestionaMenu()) {
			case 1:
				addPersona();
				break;
			case 2:
				try {
					addCuenta();
				} catch (ListaVaciaException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 3:
				try {
					cambiarDireccion();
				} catch (ListaVaciaException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 4:
				try {
					eliminarCuenta();
				} catch (ListaVaciaException e) {
					System.out.println(e.getMessage());
				}
				break;
			}

		} while (opcionMenuGestion != 5);
	}

	/**
	 * @throws ListaVaciaException 
	 * 
	 */
	private static void eliminarCuenta() throws ListaVaciaException {
		try {
			System.out.println(banco.eliminarCuenta(Teclado.leerEntero("Dame el id de la cuenta que vas a eliminar")));
		} catch (SaldoInvalidoException | NumerosRojosException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * cambiar la direccion de la cuenta de un titular
	 * @throws ListaVaciaException 
	 */

	private static void cambiarDireccion() throws ListaVaciaException {
		try {
			if (banco.cambiarDireccion(Teclado.leerCadena("Dame la nueva direccion"),
					Teclado.leerCadena("Dame el dni de un titular existente")))
				System.out.println("Direccion cambiada con existo");
			else
				System.out.println("No existe ell titular");
		} catch (DniInvalidoExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ingresar dinero en la cuenta
	 * @throws ListaVaciaException 
	 */
	private static void ingreso() throws ListaVaciaException {
		try {
			System.out.println(banco.ingreso(Teclado.leerDecimal("Dame la cantidad que desea ingresar"),
					Teclado.leerEntero("Dame el id de la cuenta")));
		} catch (SaldoInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * transferencia entre cuentas
	 * @throws ListaVaciaException 
	 */

	private static void transferencia() throws ListaVaciaException {
		try {
			System.out.println(banco.transferencia(Teclado.leerEntero("Dame el id de la cuenta que vas a enviar"),
					Teclado.leerEntero("Dame el id de la cuenta que va a recibir"), Teclado.leerDecimal("Cantidad")));
		} catch (SaldoInvalidoException | NumerosRojosException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * reintegro entre cuentas
	 * @throws ListaVaciaException 
	 */

	private static void reintegro() throws ListaVaciaException {
		try {
			System.out.println(banco.reintegro(Teclado.leerDecimal("Dame la cantidad que desea retirar"),
					Teclado.leerEntero("Dame el id de la cuenta")));
		} catch (SaldoInvalidoException | NumerosRojosException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Añadims una cuenta
	 * @throws ListaVaciaException 
	 */

	private static void addCuenta() throws ListaVaciaException {

		try {
			if (banco.addCuentas(Teclado.leerDecimal("Dame el saldo inicial de la cuenta"),
					Teclado.leerCadena("Dame el dni de una persona ya creada")))
				System.out.println("Cuenta creada correctamente");
			else
				System.out.println("Titular no existes");
		} catch (DniInvalidoExceptions | SaldoInvalidoException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * añadimos una persona
	 */
	private static void addPersona() {
		try {
			if (banco.addPersona(Teclado.leerCadena("Dame el nombre del titular"),
					Teclado.leerCadena("Dame el dni del titular"), Teclado.leerCadena("Dame la direccion del titular")))
				System.out.println("Titular creado correctamente");
			else
				System.out.println("DNI ya registrado");
		} catch (DniInvalidoExceptions e) {
			System.out.println(e.getMessage());
		}
	}

}
