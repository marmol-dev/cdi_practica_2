/**
 * Created by marmol on 1/03/16.
 */
public class SumaFilaThread implements Runnable{
    private int[] fila1;
    private int[] fila2;
    private int[] resultado = null;
    public SumaFilaThread(int[] fila1, int[] fila2){
        this.fila1 = fila1;
        this.fila2 = fila2;
    }

    public void run() {
        resultado = new int[fila1.length];
        for (int i = 0; i < fila1.length; i++) {
            resultado[i] = fila1[i] + fila2[i];
        }
    }

    public int[] getResultado() {
        if (resultado == null) this.run();
        return this.resultado;
    }
}
