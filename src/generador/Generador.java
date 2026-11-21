package generador;

public class Generador {
    // Tamano maximo soportado: 100 000 000 (por memoria).
    // Asi size + 1 nunca se desborda al calcular el limite superior.
    public static final int MAX_N = 100_000_000;

    // genera un arreglo de tamano size
    // con numeros aleatorios de 0 a size (ambos inclusive)
    public int[] generate(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size no puede ser negativo: " + size);
        }
        if (size > MAX_N) {
            throw new IllegalArgumentException("size es mayor al maximo soportado: " + MAX_N);
        }
        int[] arreglo = new int[size]; // si size = 0 queda un arreglo vacio
        for (int i = 0; i < size; i++) {
            // Math.random() da valores de 0 hasta menos de 1,
            // por eso multiplicamos por (size + 1) para incluir el size
            arreglo[i] = (int) (Math.random() * (size + 1));
        }
        return arreglo;
    }
    
}
