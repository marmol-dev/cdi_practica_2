/**
 * Clase principal que muestra el resultado de la actividad 2
 * @author Víctor, Martín
 */
public class Actividad2 {
	/**
	 * Método principal.
	 * @param args Los argumentos que recibe el programa del terminal
     */
	public static void main(String[] args) {
		int nThreads = 4;
		Matriz m1, m2, resultado;
		SumaThreads sumaThreads;

		try {
			 sumaThreads = new SumaThreads(nThreads);
		} catch (Exception e){
			e.printStackTrace();
			return;
		}

		for (int i = 2; i <= 2048; i = i * 2) {
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

	}
}
