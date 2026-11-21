package algoritmos;

public class Insercion {
    public static void ordenar(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int actual = a[i];
            int j = i - 1;
            // movemos a la derecha los elementos mayores que "actual"
            while (j >= 0 && a[j] > actual) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = actual;
        }
    }
    
}
