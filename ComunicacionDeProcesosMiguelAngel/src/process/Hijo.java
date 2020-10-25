
package process;

import java.util.Scanner;

public class Hijo {
	private static boolean salir = false;
	private static Scanner tec = new Scanner(System.in);

	public static void main(String[] args) {
		// Al comenzar la ejecuciÃ³n muestro por pantalla un mensaje de bienvenida
		System.out.println("Bienvenido al sistemas de reservas del Restaurante. ¿Que desea?");
		System.out.println(
				"Puede realizar las siguientes opciones:\n Reservar Mesa\n Mostrar la carta de hoy\n Consultar Horario del resturante\n Salir");
		while (!salir) {
			System.out.println("Introduzca una opcion:");
			String entrada = tec.nextLine();
			switch (entrada) {
			case "reservar una mesa":
				System.out.println("De acuerdo, se le ha reservado una mesa para las  22:00");
				break;
			case "mostrar la carta de hoy":
				System.out.println("Primer plato: Ensalada rusa.\n Segundo plato: Berberechos.\n Postre: Helado.");
				break;
			case "consultar horario del restaurante":
				System.out.println("El local abre a las 10:00 AM y cierra a las 22:00 PM");
				break;
			case "salir":
				System.out.println("Saliendo del sistema...");
				Hijo.salir = true;
				break;
			default:
				System.out.println("Debe introducir una opcion valida");
				break;
			}

		}

	}
}
