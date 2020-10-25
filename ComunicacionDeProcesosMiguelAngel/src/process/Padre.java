
package process;

import java.io.InputStream;

import java.io.OutputStream;

public class Padre {
//Metodo para comprobar que el proceso hijo sigue ejecutandose
	public static boolean isAlive(Process p) {
		// si el proceso hijo ha terminado devuelve falso, si no ha terminado lanza una
		// excepci�n y devuelve true
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

	public static void main(String[] args) throws Exception {

		ProcessBuilder builder = new ProcessBuilder("java", "-jar", "hijo.jar"); // Ejecuto un archivo .jar ejecutable que contiene la clase hijo.java
																				
		builder.redirectErrorStream(true); // redirige el buffer de error a la salida est�ndar
		Process process = builder.start();

		InputStream out = process.getInputStream(); // creo un objeto InputStream que corresponde a la salida del proceso hijo
												
		OutputStream in = process.getOutputStream(); // creo un objeto OutputStream que corresponde a la entrada del proceso hijo
														

		byte[] buffer = new byte[4000]; // Creo el buffer con el que se comunican los dos procesos

		// Empiezo el bucle de comunicacion entre procesos
		while (isAlive(process)) {
			// se comprueba que el OutputStream tenga informacion de entrada del proceso hijo
		
			int no = out.available();
			if (no > 0) {
				// si el stream de salida del proceso hijo tiene informaci�n se muestra por
				// pantalla
				int n = out.read(buffer, 0, Math.min(no, buffer.length));
				System.out.println(new String(buffer, 0, n));
			}
			// Compruebo si hay que enviarle informacion al proceso hijo
			int ni = System.in.available();
			if (ni > 0) {
				// si hay informacion que enviarle, se le envia mediante la entrada estandar
				int n = System.in.read(buffer, 0, Math.min(ni, buffer.length));
				in.write(buffer, 0, n);
				in.flush();
			}
		}
		// Al finalizar la ejecucion del proceso hijo, se muestra el valor de salida del
		// mismo.
		System.out.println("Valor de salida del proceso hijo: " + process.exitValue());
	}

}
