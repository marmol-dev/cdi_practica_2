/**
 * Clase principal que muestra el resultado de la actividad 2
 * @author Víctor, Martín
 */
public class Actividad2 {
	/**
	 * Método principal.
	 * @param args Los argumentos que recibe el programa del termina: nThreads y maxDimension
     */
	public static void main(String[] args) {

		try {
			if (args.length < 2) {
				throw new Exception("Usage: /path/to/executable nThreads maxDimension");
			}

			int nThreads = Integer.parseInt(args[0]);
			int maxDimension = Integer.parseInt(args[1]);

			if (maxDimension < 2) {
				throw new Exception("maxDimension shout be greater than 2");
			}

			Matriz m1, m2, resultado;
			SumaThreads sumaThreads;

			try {
				sumaThreads = new SumaThreads(nThreads);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			for (int i = 2; i <= maxDimension; i = i * 2) {
				m1 = new Matriz(i);
				m1.autoGenerar();
				m2 = new Matriz(i);
				m2.autoGenerar();

				long inicio, fin;

				//Con threads
				inicio = System.currentTimeMillis();
				try {
					resultado = sumaThreads.getSuma(m1, m2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				fin = System.currentTimeMillis();

				System.out.println("Tamaño: " + i + ", Tiempo: " + (fin - inicio));

			}

			System.out.println("Program actividad 2 finished successfully!");

		} catch (Exception e){
			System.out.println(e.toString());
		}

	}
}
