package algoritmos;

// V2 (metodo investigado): Merge Sort
// Nota: el arreglo auxiliar se crea DENTRO del metodo ordenar,
// por eso el tiempo de crearlo si se mide.

public class MergeSort {
    public static void ordenar(int[] a) {
        int[] aux = new int[a.length];
        ordenar(a, aux, 0, a.length - 1);
    }

    private static void ordenar(int[] a, int[] aux, int inicio, int fin) {
        if (inicio >= fin) {
            return; // caso base: 0 o 1 elemento
        }
        int medio = (inicio + fin) / 2;
        ordenar(a, aux, inicio, medio);
        ordenar(a, aux, medio + 1, fin);
        mezclar(a, aux, inicio, medio, fin);
    }

    private static void mezclar(int[] a, int[] aux, int inicio, int medio, int fin) {
        // copiamos la parte a mezclar al arreglo auxiliar
        for (int k = inicio; k <= fin; k++) {
            aux[k] = a[k];
        }
        int i = inicio;     // recorre la mitad izquierda
        int j = medio + 1;  // recorre la mitad derecha
        for (int k = inicio; k <= fin; k++) {
            if (i > medio) {
                a[k] = aux[j];
                j++;
            } else if (j > fin) {
                a[k] = aux[i];
                i++;
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j];
                j++;
            } else {
                // si son iguales sale primero el de la izquierda (por eso es estable)
                a[k] = aux[i];
                i++;
            }
        }
    }
    
}
