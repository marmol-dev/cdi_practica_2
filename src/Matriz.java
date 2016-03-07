/**
 * Clase que implementa los métodos necesarios para trabajar con una matriz cuadrada.
 * @author Víctor y Martín
 */
public class Matriz {
	private int matriz[][];
	private int tamano;
	
	public Matriz(int tamano){
		this.tamano = tamano;
		this.matriz = new int[tamano][tamano];
	}

	/**
	 * Auto-genera una matriz con números aleatorios entre 0 y 26
	 */
	public void autoGenerar(){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				this.matriz[i][j] = (int) (Math.random()*25+1);
			}
		}
	}

	/**
	 * Devuelve el tamaño de una matriz cuadrada (dimensión)
	 * @return El tamaño
     */
	public int getTamano(){
		return this.tamano;
	}

	/**
	 * Devuelve el valor de una celda de la matriz
	 * @param fila La fila
	 * @param columna La columna
     * @return El valor contenido en la posición (fila, columna) de la matriz
     */
	public int getValor(int fila, int columna){
		return this.matriz[fila][columna];
	}

	/**
	 * Devuelve una fila
	 * @param i La fila
	 * @return Un vector de enteros que representa una fila
     */
	public int[] getFila(int i){
		return this.matriz[i];
	}

	/**
	 * Actualiza el valor de una fila
	 * @param i La fila
	 * @param fila Un vector de enteros que representa una fila
     */
	public void setFila(int i, int[] fila){
		this.matriz[i] = fila;
	}

	/**
	 * Actualiza el valor de una celda
	 * @param fila La fila
	 * @param columna La columna
	 * @param valor El valor nuevo de la celda
     */
	public void setValor(int fila, int columna,int valor){
		this.matriz[fila][columna] = valor;
	}

	/**
	 * Devuelve un string con los valores de la matriz formateados multilínea
	 * @return El string que corresponde a la matriz
     */
	public String toString(){
		String toret = "";
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				toret += getValor(i, j) + "    ";
			}
			toret += "\n";
		}
		return toret;
	}
}
