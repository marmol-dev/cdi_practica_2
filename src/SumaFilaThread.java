/**
 * Clase que se encarga de sumar una fila de dos matrices distintas.
 * Cada objeto de esta clase se ejecuta en un único hilo.
 * @author Martín y Víctor.
 */
public class SumaFilaThread implements Runnable{
    private int[][] filas1;
    private int[][] filas2;
    private int[][] resultado = null;
    int dimension;
    public SumaFilaThread(int[][] filas1, int[][] filas2, int dimension){
        this.filas1 = filas1;
        this.filas2 = filas2;
        this.dimension = dimension;
    }

    /**
     * Realiza la suma de una fila de dos matrices y guarda el resultado
     */
    public void run() {
        resultado = new int[filas1.length][dimension];
        for (int i = 0; i < filas1.length; i++) {
            for(int j = 0; j < filas1[i].length; j++){
                resultado[i][j] = filas1[i][j] + filas2[i][j];
            }
        }
    }

    /**
     * Devuelve el resultado de la suma realizada en el método run()
     * @return Las filas resultado
     */
    public int[][] getResultado() {
        if (resultado == null) this.run();
        return this.resultado;
    }
}
