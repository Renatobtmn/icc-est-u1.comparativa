package app;

import java.util.Arrays;
import java.util.List;

import algoritmos.Insercion;
import algoritmos.MergeSort;
import benchmark.Benchmark;
import control.ControladorExperimentos;
import generador.Generador;
import modelo.Results;

public class PruebasAceptacion {

    public static void main(String[] args) {
        Generador gen = new Generador();

        // CA01: generar n = 10 000, 50 000 y 100 000
        for (int n : new int[]{10_000, 50_000, 100_000}) {
            int[] m = gen.generate(n);
            boolean enRango = true;
            for (int v : m) {
                if (v < 0 || v > n) enRango = false;
            }
            System.out.println("CA01 n=" + n + " -> tamano correcto: " + (m.length == n)
                    + ", valores entre 0 y n: " + enRango);
        }

        // CA02: n = 0 y n negativo
        System.out.println("CA02 n=0 -> " + Arrays.toString(gen.generate(0)));
        try {
            gen.generate(-5);
            System.out.println("CA02 n=-5 -> NO dio error (mal)");
        } catch (IllegalArgumentException e) {
            System.out.println("CA02 n=-5 -> rechazado: " + e.getMessage());
        }

        // CA03: mismas entradas e independientes
        int[] base = gen.generate(1000);
        int[] c1 = base.clone();
        int[] c2 = base.clone();
        boolean iguales = Arrays.equals(base, c1) && Arrays.equals(base, c2);
        c1[0] = -1; // cambiamos solo una copia
        boolean independientes = base[0] != -1 && c2[0] != -1;
        System.out.println("CA03 mismos valores: " + iguales + ", independientes: " + independientes);

        // CA04, CA05 y CA06 (V1, V2 y V3)
        int[][] casos = {
                {7, 2, 7, 0, 4, 2},          // CA04
                {}, {5}, {3, 3},              // CA05
                {0, 1, 2, 3}, {3, 2, 1, 0}    // CA06
        };
        for (int[] caso : casos) {
            int[] esperado = caso.clone();
            Arrays.sort(esperado);
            int[] a = caso.clone();
            int[] b = caso.clone();
            int[] c = caso.clone();
            Insercion.ordenar(a);
            MergeSort.ordenar(b);
            Arrays.sort(c);
            boolean ok = Arrays.equals(a, esperado) && Arrays.equals(b, esperado)
                    && Arrays.equals(c, esperado);
            System.out.println("CA04-06 " + Arrays.toString(caso) + " -> " + Arrays.toString(b)
                    + " | correcto: " + ok);
        }

        // CA07: la base no cambia y todas las salidas coinciden con la referencia
        int[] base7 = gen.generate(2000);
        int[] copia7 = base7.clone();
        int[] ref7 = base7.clone();
        Arrays.sort(ref7);
        int[] x = base7.clone();
        int[] y = base7.clone();
        int[] z = base7.clone();
        Insercion.ordenar(x);
        MergeSort.ordenar(y);
        Arrays.sort(z);
        System.out.println("CA07 base intacta: " + Arrays.equals(base7, copia7)
                + ", salidas = referencia: "
                + (Arrays.equals(x, ref7) && Arrays.equals(y, ref7) && Arrays.equals(z, ref7)));

        // CA08: medir una operacion con lambda
        int[] datos = {3, 1, 2};
        Results r = Benchmark.medir(() -> Arrays.sort(datos), "prueba", datos.length);
        System.out.println("CA08 " + r + " | salida: " + Arrays.toString(datos));

        // CA09: operacion que lanza una excepcion
        try {
            Benchmark.medir(() -> { throw new IllegalStateException("error simulado"); },
                    "operacion que falla", 0);
            System.out.println("CA09 -> NO dio error (mal)");
        } catch (RuntimeException e) {
            System.out.println("CA09 -> fallo registrado: " + e.getMessage()
                    + " | causa original: " + e.getCause());
        }

        // CA10: 12 resultados (tarda unos segundos)
        List<Results> lista = new ControladorExperimentos().ejecutar();
        System.out.println("CA10 resultados: " + lista.size());
        for (Results res : lista) {
            System.out.println("   " + res);
        }

        // CA11: salida del Merge Sort para [7, 2, 7, 0, 4, 2]
        int[] t = {7, 2, 7, 0, 4, 2};
        MergeSort.ordenar(t);
        System.out.println("CA11 MergeSort [7,2,7,0,4,2] -> " + Arrays.toString(t));
    }
    
}
