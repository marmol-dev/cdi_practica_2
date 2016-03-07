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
	 * Constructor que recibe el número hilos
	 * @param nThreads Número de hilos para resolver la suma
	 * @throws Exception que se produce cuando el número hilos es negativo o 0
     */
	public SumaThreads(int nThreads) throws Exception {
		if (nThreads < 1){
			throw new Exception("Invalid number of threads");
		}
		this.nThreads = nThreads;
	}

	/**
	 * Obtiene el número de filas que tiene que procesar cada thread
	 * @param dimension La dimensión de la matriz
	 * @return El número de filas por thread
     */
	private int getFilasPorThread(int dimension){
		float filasPorThread = (float) (dimension / nThreads);

		if (filasPorThread < 1) filasPorThread = 1;
		else filasPorThread = (float) Math.floor(filasPorThread);

		return (int) filasPorThread;
	}

	/**
	 * Obtiene la fila de inicio que tiene que procesar un thread
	 * Si la fila de inicio es -1 significa que ese thread no tiene que procesar ninguna fila
	 * @param nThread El thread al que le quiere calcular la fila que tiene que procesar
	 * @param dimension La dimensión de la matriz
     * @return La fila de inicio
     */
	private int getFilaInicio(int nThread, int dimension){
		int toret = nThread* getFilasPorThread(dimension);
		if (toret >= dimension) toret = -1;
		return toret;
	}

	/**
	 * Obtiene la fila de fin que tiene que procesar un thread (la fila de fin es la siguiente a la última)
	 * @param nThread El thread al que se le quiere calcular la fila de fin
	 * @param dimension La dimensión de la matriz
     * @return La fila de fin
     */
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
