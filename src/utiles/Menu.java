package utiles;

public class Menu {
	private String[] opciones;
	private String titulo;

	public Menu(String[] opciones, String titulo) {
		setOpciones(opciones);
		this.titulo = titulo;

	}

	private void setOpciones(String[] opciones) {
		this.opciones = new String[opciones.length + 1];
		int i = 0;
		for (String string : opciones) {
			this.opciones[i++] = "-."+string;
		}
		this.opciones[i] = "-.Salir";
	}

	private void mostrarMenu() {

		
		System.out.println("**" + titulo);
		for (int i = 0; i < opciones.length; i++) {
			System.out.println((i + 1) + " " + opciones[i]);
		}

	}

	public int gestionaMenu() {

		mostrarMenu();
		return opcionValida();

		

	}

	private int opcionValida() {

		int opcionValida = Teclado.leerEntero("Dame una opcion valida entre 1 y " + (opciones.length));
		while (opcionValida < 1 || opcionValida > (opciones.length)) {
			opcionValida = Teclado.leerEntero("La opcion no esta entre  1 y " + (opciones.length));
		}
		return opcionValida;

	}

}
