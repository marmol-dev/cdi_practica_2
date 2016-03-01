
public class Matriz {
	private int matriz[][];
	private int tamano;
	
	public Matriz(int tamano){
		this.tamano = tamano;
		this.matriz = new int[tamano][tamano];
	}
	
	public void autoGenerar(){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				this.matriz[i][j] = (int) (Math.random()*25+1);
			}
		}
	}
	public int getTamano(){
		return this.tamano;
	}
	
	public int getValor(int fila, int columna){
		return this.matriz[fila][columna];
	}

	public int[] getFila(int i){
		return this.matriz[i];
	}

	public void setFila(int i, int[] fila){
		this.matriz[i] = fila;
	}

	public void setValor(int fila, int columna,int valor){
		this.matriz[fila][columna] = valor;
	}
	
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
