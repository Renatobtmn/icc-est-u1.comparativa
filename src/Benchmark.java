public class Benchmark {

    public static Results medir(Runnable accion,String nombre) {
        
        long tA = System.nanoTime();
        accion.run();
        long tF= System.nanoTime();

        Results results= new Results(nombre, tF-tA, "100000");
        return results;

        

        //tiempo actual
        //accion
        //tiempo final
        //tiempo
        //retornar el tiempo de duracion

        
    }
    
}
