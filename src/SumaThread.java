import java.util.Vector;

public class SumaThread {

	private static SumaThread instance = null;

	private Matriz m1;
	private Matriz m2;
	private Matriz resultado = null;

	public static SumaThread getInstance() {
		if(instance == null) instance = new SumaThread();
		return instance;
	}

	public Matriz getSuma(Matriz m1, Matriz m2) throws Exception {

		if(m1.getTamano() != m2.getTamano()){
			throw new Exception("Size of matrix must be equal");
		}

		Matriz resultado = new Matriz(m1.getTamano());

		Vector<SumaFilaThread> r = new Vector<SumaFilaThread>();
		Vector<Thread> t = new Vector<Thread>();


		//Crear hilos e iniciarlos
		for (int i = 0; i < m1.getTamano(); i++) {
			r.add(new SumaFilaThread(m1.getFila(i), m2.getFila(i)));
			t.add(new Thread(r.get(i)));
			t.get(i).start();
		}

		//Sincronizarlos
		for(int i = 0; i < m1.getTamano(); i++){
			try {
				t.get(i).join();
				resultado.setFila(i, r.get(i).getResultado());
			} catch(InterruptedException e){}
		}

		return resultado;

	}



}
