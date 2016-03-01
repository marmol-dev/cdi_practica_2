import java.util.Vector;

public class Actividad2 {
	public static void main(String[] args) {
		for (int i = 2; i <= 4096; i = i * 2) {
			Matriz m1 = new	Matriz(i);
			m1.autoGenerar();
			Matriz m2 = new Matriz(i);
			m2.autoGenerar();
			SumaThread sumaThread;
			Matriz resultado;

			long inicio, fin;

			//Con threads
			inicio = System.currentTimeMillis();
			try {
				resultado = SumaThread.getInstance().getSuma(m1, m2);
			} catch(Exception e){}
			fin = System.currentTimeMillis();


			System.out.println("Tiempo ejecucion con threads (Tamano " + i + "): " + (fin - inicio));

		}



		//Sin threads
		/*inicio = System.currentTimeMillis();
		resultado = new Matriz(tam);
		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam; j++) {
				resultado.setValor(i, j, m1.getValor(i, j) + m2.getValor(i, j));
			}
		}
		fin = System.currentTimeMillis();
		System.out.println("Tiempo ejecucion sin threads: " + (fin - inicio));*/


		//Mostrar matrices
		//System.out.println("M1\n" + m1.toString());
		//System.out.println("M2\n" + m2.toString());
		//System.out.println("MR\n" + resultado.toString());


	}
}
