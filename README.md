Renato Alba
jdk 27 / version

**1**
=== RESULTADOS (12) ===
Results [functionName=V1 Insercion, sample=10000, duration=42550700ns, 42,551ms]
Results [functionName=V2 MergeSort, sample=10000, duration=3788900ns, 3,789ms]
Results [functionName=V3 Arrays.sort, sample=10000, duration=1949700ns, 1,950ms]
Results [functionName=V4 Arrays.sort con Comparator, sample=10000, duration=12613400ns, 12,613ms]
Results [functionName=V1 Insercion, sample=50000, duration=836364400ns, 836,364ms]
Results [functionName=V2 MergeSort, sample=50000, duration=8010300ns, 8,010ms]
Results [functionName=V3 Arrays.sort, sample=50000, duration=5872000ns, 5,872ms]
Results [functionName=V4 Arrays.sort con Comparator, sample=50000, duration=24278300ns, 24,278ms]
Results [functionName=V1 Insercion, sample=100000, duration=3257118800ns, 3257,119ms]
Results [functionName=V2 MergeSort, sample=100000, duration=15798100ns, 15,798ms]
Results [functionName=V3 Arrays.sort, sample=100000, duration=8340000ns, 8,340ms]
Results [functionName=V4 Arrays.sort con Comparator, sample=100000, duration=115822800ns, 115,823ms]
PS C:\Users\renat\Downloads\icc-est-u1-comparativa\icc-est-u1-comparativa> 

**2**
CA01 n=10000 -> tamano correcto: true, valores entre 0 y n: true
CA01 n=50000 -> tamano correcto: true, valores entre 0 y n: true
CA01 n=100000 -> tamano correcto: true, valores entre 0 y n: true
CA02 n=0 -> []
CA02 n=-5 -> rechazado: size no puede ser negativo: -5
CA03 mismos valores: true, independientes: true
CA04-06 [7, 2, 7, 0, 4, 2] -> [0, 2, 2, 4, 7, 7] | correcto: true
CA04-06 [] -> [] | correcto: true
CA04-06 [5] -> [5] | correcto: true
CA04-06 [3, 3] -> [3, 3] | correcto: true
CA04-06 [0, 1, 2, 3] -> [0, 1, 2, 3] | correcto: true
CA04-06 [3, 2, 1, 0] -> [0, 1, 2, 3] | correcto: true
CA07 base intacta: true, salidas = referencia: true
CA08 Results [functionName=prueba, sample=3, duration=19200ns, 0,019ms] | salida: [1, 2, 3]
CA09 -> fallo registrado: Fallo en operacion que falla (sample 0): error simulado | causa original: java.lang.IllegalStateException: error simulado
CA10 resultados: 12
   Results [functionName=V1 Insercion, sample=10000, duration=32643400ns, 32,643ms]
   Results [functionName=V2 MergeSort, sample=10000, duration=1334900ns, 1,335ms]
   Results [functionName=V3 Arrays.sort, sample=10000, duration=1769800ns, 1,770ms]
   Results [functionName=V4 Arrays.sort con Comparator, sample=10000, duration=12854000ns, 12,854ms]
   Results [functionName=V1 Insercion, sample=50000, duration=840723200ns, 840,723ms]
   Results [functionName=V2 MergeSort, sample=50000, duration=7570400ns, 7,570ms]
   Results [functionName=V3 Arrays.sort, sample=50000, duration=4092500ns, 4,093ms]
   Results [functionName=V4 Arrays.sort con Comparator, sample=50000, duration=21996500ns, 21,997ms]
   Results [functionName=V1 Insercion, sample=100000, duration=1172298100ns, 1172,298ms]
   Results [functionName=V2 MergeSort, sample=100000, duration=18490600ns, 18,491ms]
   Results [functionName=V3 Arrays.sort, sample=100000, duration=11413600ns, 11,414ms]
   Results [functionName=V4 Arrays.sort con Comparator, sample=100000, duration=99389200ns, 99,389ms]
CA11 MergeSort [7,2,7,0,4,2] -> [0, 2, 2, 4, 7, 7]
PS C:\Users\renat\Downloads\icc-est-u1-comparativa\icc-est-u1-comparativa> 

CAPTURAS
![alt text](<Captura de pantalla 2026-11-21 123119.png>)
![alt text](<Captura de pantalla 2026-11-21 123438.png>)

EXPLICACION

Con el método V1 Inserción, al pasar de 10 000 a 100 000 elementos, el tiempo subió de 42,551 ms a 3257,119 ms es decir 76,5 veces más. Creo que pasó porque al ser O(n^2), las comparaciones y desplazamientos crecen de forma cuadrática.

Con 100 000 elementos, Inserción tardó 3257,119 ms, mientras que V3 Arrays.sort tardó 8,340 ms. Esto muestra que los algoritmos O(n log n) son muy superiores con grandes volúmenes porque dividen el arreglo en lugar de recorrerlo elemento por elemento.

Con 100 000 elementos, V4 Arrays.sort con Comparator tardó 115,823 ms y V3 Arrays.sort tardó 8,340 ms. Esto puede deberse a la sobrecarga de manejar objetos Integer y ejecutar el Comparator en lugar de ordenar tipos primitivos.

