/**
 * Clase que se encarga de sumar una fila de dos matrices distintas.
 * Cada objeto de esta clase se ejecuta en un único hilo.
 * @author Martín y Víctor.
 */
public class SumaFilaThread implements Runnable{
    private int[] fila1;
    private int[] fila2;
    private int[] resultado = null;
    public SumaFilaThread(int[] fila1, int[] fila2){
        this.fila1 = fila1;
        this.fila2 = fila2;
    }

    /**
     * Realiza la suma de una fila de dos matrices y guarda el resultado
     */
    public void run() {
        resultado = new int[fila1.length];
        for (int i = 0; i < fila1.length; i++) {
            resultado[i] = fila1[i] + fila2[i];
        }
    }

    /**
     * Devuelve el resultado de la suma realizada en el método run()
     * @return La fila resultado
     */
    public int[] getResultado() {
        if (resultado == null) this.run();
        return this.resultado;
    }
}
