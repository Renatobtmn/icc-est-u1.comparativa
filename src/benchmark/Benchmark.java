package benchmark;
import modelo.Results;


public class Benchmark {

    public static Results medir(Runnable accion, String nombre, int sample) {
        RuntimeException error = null;

        // tiempo actual
        long tA = System.nanoTime();
        // accion
        try {
            accion.run();
        } catch (RuntimeException e) {
            error = e; // solo guardamos el error, no lo tratamos dentro del tiempo
        }
        // tiempo final
        long tF = System.nanoTime();

        // el fallo se registra fuera del intervalo medido
        if (error != null) {
            throw new RuntimeException("Fallo en " + nombre + " (sample " + sample + "): "
                    + error.getMessage(), error); // error = excepcion original
        }

        // tiempo = final - actual, y retornamos el resultado
        return new Results(nombre, tF - tA, sample);
    }
}
