import java.util.Vector;

/**
 * Clase que implementa la suma de dos matrices utilizando hilos
 * @author Víctor y Martín
 */
public class SumaThreads implements SumaMatrices {

	private static SumaThreads instance = null;

	private Matriz m1;
	private Matriz m2;
	private Matriz resultado = null;
	private int nThreads;

	/**
	 * Devuelve la instancia para implementar el patrón "Singleton"
	 * @param nThreads el número de threads a usar
	 * @return La única instancia permitida de la SumaThreads
     */
	public static SumaThreads getInstance(int nThreads) throws Exception {
		if(instance == null) instance = new SumaThreads(nThreads);
		return instance;
	}

	public SumaThreads(int nThreads) throws Exception {
		if (nThreads < 1){
			throw new Exception("Invalid number of threads");
		}
		this.nThreads = nThreads;
	}

	private int getFilasPorThread(int dimension){
		float filasPorThread = (float) (dimension / nThreads);

		if (filasPorThread < 1) filasPorThread = 1;
		else filasPorThread = (float) Math.floor(filasPorThread);

		return (int) filasPorThread;
	}

	private int getFilaInicio(int nThread, int dimension){
		int toret = nThread* getFilasPorThread(dimension);
		if (toret >= dimension) toret = -1;
		return toret;
	}

	private int getFilaFin(int nThread, int dimension){
		int filaFin, filaInicio = getFilaInicio(nThread, dimension);

		if (filaInicio == -1){
			return -1;
		}

		filaFin = filaInicio + getFilasPorThread(dimension);
		if (filaFin > dimension){
			filaFin = dimension;
		}

		return filaFin;
	}

	/**
	 * Obtiene la matriz que es suma de dos matrices
	 * @param m1 La primera matriz
	 * @param m2 La segunda matriz
	 * @return La matriz resultante
	 * @throws Exception que puede lanzar si las matrices son de distinto tamaño
	 */
	public Matriz getSuma(Matriz m1, Matriz m2) throws Exception {

		if(m1.getTamano() != m2.getTamano()){
			throw new Exception("Size of matrix must be equal");
		}

		Matriz resultado = new Matriz(m1.getTamano());

		Vector<SumaFilaThread> r = new Vector<SumaFilaThread>();
		Vector<Thread> t = new Vector<Thread>();
		int dimension = m1.getTamano();
		int filaInicio, filaFin;


		//Crear hilos e iniciarlos
		for (int i = 0; i < nThreads; i++) {
			filaInicio = getFilaInicio(i, dimension);
			if (filaInicio > -1) {
				filaFin = getFilaFin(i, dimension);
				r.add(new SumaFilaThread(m1.getFilas(filaInicio, filaFin), m2.getFilas(filaInicio, filaFin), m1.getTamano()));
				t.add(new Thread(r.get(i)));
				t.get(i).start();
			}
		}

		//Sincronizarlos
		for(int i = 0; i < nThreads; i++){
			filaInicio = getFilaInicio(i, dimension);
			if (filaInicio > -1) {
				filaFin = getFilaFin(i, dimension);
				try {
					t.get(i).join();
				} catch (InterruptedException e) {
				}
				resultado.setFilas(filaInicio, filaFin, r.get(i).getResultado());
			}
		}

		return resultado;

	}



}
