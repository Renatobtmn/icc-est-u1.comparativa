Investigacion Estructura de Datos

# Investigación: Merge Sort (V2)

## 1. Identificación del código

Algoritmo y variante: Merge Sort recursivo "de arriba hacia abajo" (top-down), con un arreglo auxiliar.

Clase y archivo: [src/algoritmos/MergeSort.java](src/algoritmos/MergeSort.java)

Métodos:

- [`ordenar(int[] a)`](src/algoritmos/MergeSort.java#L8): es el método que se llama primero. Crea el arreglo auxiliar `aux` una sola vez y empieza el proceso.
- [`ordenar(int[] a, int[] aux, int inicio, int fin)`](src/algoritmos/MergeSort.java#L13): se llama a sí mismo. Parte el rango en dos mitades hasta que queda un solo elemento.
- [`mezclar(int[] a, int[] aux, int inicio, int medio, int fin)`](src/algoritmos/MergeSort.java#L23): junta dos mitades que ya están ordenadas y deja el resultado ordenado en `a`.

Cómo se usa como V2: el método [`medirV2`](src/control/ControladorExperimentos.java#L49) de [ControladorExperimentos](src/control/ControladorExperimentos.java) lo manda a `Benchmark.medir` con una lambda y después comprueba que la salida esté bien.

## 2. Proceso paso a paso

Qué recibe cada método:

- `ordenar(int[] a)` recibe el arreglo que hay que ordenar.
- `ordenar(a, aux, inicio, fin)` recibe el arreglo `a`, el arreglo auxiliar `aux` y los números `inicio` y `fin`, que indican qué parte del arreglo se está ordenando.
- `mezclar(a, aux, inicio, medio, fin)` recibe lo mismo más `medio`, que marca dónde termina la mitad izquierda. La izquierda va de `inicio` a `medio` y la derecha de `medio + 1` a `fin`.

Caso base: el método deja de dividir cuando `inicio >= fin`, es decir, cuando la parte tiene 0 o 1 elemento. Un solo elemento ya está ordenado, así que no hace falta hacer nada.

Por qué siempre termina: en cada llamada calcula el `medio` y ordena por separado la mitad izquierda y la derecha. Cada vez el pedazo es más pequeño, así que tarde o temprano llega a un solo elemento.

Qué se mantiene cierto durante todo el proceso: cuando termina una llamada, esa parte del arreglo ya quedó ordenada. Por eso, cuando `mezclar` trabaja, siempre recibe dos mitades ya ordenadas.

## 3. División, mezcla, arreglo auxiliar y empates

División: el `medio` se calcula como `(inicio + fin) / 2`. El arreglo no se copia en pedazos nuevos. Siempre se trabaja sobre el mismo arreglo usando solo los números `inicio` y `fin`.

Mezcla: uso dos "dedos". El dedo `i` recorre la mitad izquierda y el dedo `j` recorre la mitad derecha. En cada posición `k` de `a` escribo un valor, según estos cuatro casos:

- si ya no quedan elementos en la izquierda, tomo el de la derecha;
- si ya no quedan en la derecha, tomo el de la izquierda;
- si el de la derecha es menor, tomo el de la derecha;
- en cualquier otro caso (el de la izquierda es menor o son iguales), tomo el de la izquierda.

Arreglo auxiliar: sirve para guardar una copia de la parte que se está mezclando, porque mientras escribo en `a` voy borrando los valores originales. Se crea una sola vez dentro de `ordenar(int[] a)`. Como eso ocurre dentro del método que se cronometra, el tiempo de crearlo sí cuenta en el tiempo medido de V2.

Empates: cuando dos números son iguales, la condición `aux[j] < aux[i]` es falsa porque es una comparación estricta. Entonces se toma el de la izquierda, y así los números iguales quedan en el mismo orden en que estaban al inicio.

## 4. Traza manual con [7, 2, 7, 0, 4, 2]

Para ver el orden de los números repetidos, los marco como `7a, 2a, 7b, 0, 4, 2b`. Al final, la salida es `[0, 2, 2, 4, 7, 7]`, igual que en el caso CA11 de `evidencias/pruebas.md`.

| Paso | Llamada o mezcla | Resultado del rango | Arreglo completo |
| :--- | :--- | :--- | :--- |
| 1 | `ordenar(0, 5)`, `medio = (0 + 5) / 2 = 2` | divide en `[0..2]` y `[3..5]` | `[7a, 2a, 7b, 0, 4, 2b]` |
| 2 | `ordenar(0, 2)`, `medio = (0 + 2) / 2 = 1` | divide en `[0..1]` y `[2..2]` | `[7a, 2a, 7b, 0, 4, 2b]` |
| 3 | `ordenar(0, 1)`, `medio = (0 + 1) / 2 = 0` | divide en `[0..0]` y `[1..1]` | `[7a, 2a, 7b, 0, 4, 2b]` |
| 4 | `ordenar(0, 0)` y `ordenar(1, 1)` | casos base `[7a]` y `[2a]` | `[7a, 2a, 7b, 0, 4, 2b]` |
| 5 | `mezclar(0, 0, 1)` (`[7a]` y `[2a]`) | `[2a, 7a]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 6 | `ordenar(2, 2)` | caso base `[7b]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 7 | `mezclar(0, 1, 2)` (`[2a, 7a]` y `[7b]`). Empate entre `7a` y `7b`: sale primero `7a` | `[2a, 7a, 7b]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 8 | `ordenar(3, 5)`, `medio = (3 + 5) / 2 = 4` | divide en `[3..4]` y `[5..5]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 9 | `ordenar(3, 4)`, `medio = (3 + 4) / 2 = 3` | divide en `[3..3]` y `[4..4]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 10 | `ordenar(3, 3)` y `ordenar(4, 4)` | casos base `[0]` y `[4]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 11 | `mezclar(3, 3, 4)` (`[0]` y `[4]`) | `[0, 4]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 12 | `ordenar(5, 5)` | caso base `[2b]` | `[2a, 7a, 7b, 0, 4, 2b]` |
| 13 | `mezclar(3, 4, 5)` (`[0, 4]` y `[2b]`) | `[0, 2b, 4]` | `[2a, 7a, 7b, 0, 2b, 4]` |
| 14 | `mezclar(0, 2, 5)` (`[2a, 7a, 7b]` y `[0, 2b, 4]`). Empate entre `2a` y `2b`: sale primero `2a` | `[0, 2a, 2b, 4, 7a, 7b]` | `[0, 2, 2, 4, 7, 7]` |

## 5. Tiempo, memoria y estabilidad

Tiempo (mejor, promedio y peor caso): en los tres casos crece como n log n. El arreglo se parte por la mitad unas log2(n) veces (con 100 000 elementos son unos 17 niveles) y en cada nivel hay que recorrer los n elementos para mezclar. Mi mezcla siempre recorre todo el rango, aunque el arreglo ya esté ordenado, por eso los tres casos se parecen.

Memoria extra: usa un arreglo auxiliar del mismo tamaño que el original, así que necesita memoria extra proporcional a n.

Pila de llamadas: como cada llamada divide el rango a la mitad, solo hay unas log2(n) llamadas abiertas al mismo tiempo, es decir, una cantidad proporcional a log n.

Estabilidad: sí es estable. Cuando dos elementos son iguales, la comparación estricta hace que se tome primero el de la izquierda, y así se respeta su orden original.

## 6. Caso desfavorable y prueba real

Caso elegido, el costo de memoria: el Merge Sort necesita un arreglo auxiliar del mismo tamaño que la entrada. En mi V2 ese arreglo se crea dentro del método que se cronometra, así que su costo se suma al tiempo. Esto se relaciona con la prueba CA10 en [PruebasAceptacion](src/app/PruebasAceptacion.java#L99) y con los 100 000 elementos de la tabla de benchmarking (V2 tardó 15,798 ms y V3 tardó 8,340 ms).

Problemas durante las pruebas: no hubo fallos. No aparecieron errores de índices ni de recursión demasiado profunda (con 100 000 elementos hay unos 17 niveles) y los casos de 0 o 1 elemento de CA05 terminan de inmediato por el caso base.

## 7. Observaciones del benchmarking y fuentes

### 7.1 Tabla de benchmarking y observaciones

| Método | 10 000 (ms) | 50 000 (ms) | 100 000 (ms) |
| :--- | :---: | :---: | :---: |
| V1 Inserción | 42,551 | 836,364 | 3257,119 |
| V2 Merge Sort | 3,789 | 8,010 | 15,798 |
| V3 Arrays.sort | 1,950 | 5,872 | 8,340 |
| V4 Arrays.sort con Comparator | 12,613 | 24,278 | 115,823 |

Observación 1 (V2 contra V1): al pasar de 10 000 a 100 000 elementos, V2 subió de 3,789 ms a 15,798 ms (unas 4,2 veces), mientras que V1 subió de 42,551 ms a 3257,119 ms (unas 76,5 veces). Esto concuerda con el apartado 5: un algoritmo de tipo n log n crece mucho más despacio que uno que crece como n al cuadrado. V2 creció menos de las 12,5 veces que se esperarían con n log n. Creo que pudo deberse al calentamiento de la JVM, pero es solo una suposición mía, y además cada dato viene de una sola medición en mi equipo.

Observación 2 (V2 contra V3): con 100 000 elementos, V2 tardó 15,798 ms y V3 tardó 8,340 ms, o sea V2 tardó unas 1,9 veces lo de V3. Aunque ambos son de tipo n log n, creo que V2 tarda más en parte porque crea y usa un arreglo extra dentro del tiempo medido, como se explica en el apartado 5. Esto es una suposición, porque no comprobé cómo funciona `Arrays.sort` por dentro.

### 7.2 Fuentes técnicas

| # | Datos bibliográficos o enlace | Fecha de consulta | Qué parte consulté |
| :--- | :--- | :---: | :--- |
| 1 | Sedgewick, R. y Wayne, K. Algorithms, 4.ª ed. Sección 2.2 Mergesort. https://algs4.cs.princeton.edu/22mergesort | 21/11/2026 | Cómo el Merge Sort divide el arreglo, usa un arreglo auxiliar y cuántas comparaciones hace. |
| 2 | Sedgewick, R. y Wayne, K. Merge.java. https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Merge.java.html | 21/11/2026 | El código y la documentación de la mezcla con arreglo auxiliar, su tiempo, su memoria extra y que es estable. |

### 7.3 Ideas, fragmentos y modificaciones

Ideas tomadas de las fuentes: partir el rango en mitades, usar un arreglo auxiliar para no perder datos mientras se mezcla, y dar prioridad al elemento de la izquierda cuando hay empates.

Código o fragmentos consultados: revisé el código de `Merge.java` para entender la lógica de ordenar y mezclar con arreglo auxiliar, y la tomé como guía para mi `ordenar` y mi `mezclar`.

Modificaciones realizadas: adapté el algoritmo para trabajar con arreglos de enteros `int[]`, dejé la creación de `aux` dentro de `ordenar(int[] a)` para que el cronómetro también mida ese costo, y usé nombres en español (`ordenar`, `mezclar`, `inicio`, `medio`, `fin`) para integrarlo como V2.