/**
 * Interfaz que define los métodos de las clases que implementen la suma de dos matrices
 * @author Martín y Víctor
 */
public interface SumaMatrices {
    /**
     * Obtiene la matriz que es suma de dos matrices
     * @param m1 La primera matriz
     * @param m2 La segunda matriz
     * @return La matriz resultante
     * @throws Exception que puede lanzar si las matrices son de distinto tamaño
     */
    public Matriz getSuma(Matriz m1, Matriz m2) throws Exception;
}
