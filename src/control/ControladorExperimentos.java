package control;

import algoritmos.Insercion;
import algoritmos.MergeSort;
import benchmark.Benchmark;
import generador.Generador;
import modelo.Results;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ControladorExperimentos {
     private int[] tamanos = {10_000, 50_000, 100_000};
    private Generador generador = new Generador();
    private List<Results> resultados = new ArrayList<>();

    public List<Results> ejecutar() {
        for (int n : tamanos) {
            // una sola muestra base por tamano (nunca se modifica)
            int[] base = generador.generate(n);
            int[] copiaBase = base.clone();

            // arreglo de referencia ya ordenado (fuera del cronometro)
            int[] referencia = base.clone();
            Arrays.sort(referencia);

            medirV1(base, referencia);
            medirV2(base, referencia);
            medirV3(base, referencia);
            medirV4(base, referencia);

            // comprobamos que la base no cambio
            if (!Arrays.equals(base, copiaBase)) {
                throw new IllegalStateException("La muestra base fue modificada");
            }
        }
        return resultados;
    }

    private void medirV1(int[] base, int[] referencia) {
        int[] clon = base.clone(); // clon nuevo, fuera del cronometro
        Results r = Benchmark.medir(() -> Insercion.ordenar(clon), "V1 Insercion", base.length);
        validar("V1", clon, referencia);
        resultados.add(r);
    }

    private void medirV2(int[] base, int[] referencia) {
        int[] clon = base.clone();
        Results r = Benchmark.medir(() -> MergeSort.ordenar(clon), "V2 MergeSort", base.length);
        validar("V2", clon, referencia);
        resultados.add(r);
    }

    private void medirV3(int[] base, int[] referencia) {
        int[] clon = base.clone();
        Results r = Benchmark.medir(() -> Arrays.sort(clon), "V3 Arrays.sort", base.length);
        validar("V3", clon, referencia);
        resultados.add(r);
    }

    private void medirV4(int[] base, int[] referencia) {
        // convertimos int[] a Integer[] fuera del cronometro
        Integer[] clon = new Integer[base.length];
        for (int i = 0; i < base.length; i++) {
            clon[i] = base[i];
        }
        // comparador con lambda, usando compare (sin restar valores)
        Comparator<Integer> comparador = (a, b) -> Integer.compare(a, b);

        Results r = Benchmark.medir(() -> Arrays.sort(clon, comparador),
                "V4 Arrays.sort con Comparator", base.length);

        // validamos fuera del cronometro
        int[] comoInt = new int[clon.length];
        for (int i = 0; i < clon.length; i++) {
            comoInt[i] = clon[i];
        }
        validar("V4", comoInt, referencia);
        resultados.add(r);
    }

    // Si el arreglo ordenado no es igual al de referencia, esta ordenado mal
    // o se perdieron elementos.
    private void validar(String variante, int[] ordenado, int[] referencia) {
        if (!Arrays.equals(ordenado, referencia)) {
            throw new IllegalStateException("Salida incorrecta en " + variante);
        }
    }
    
}
