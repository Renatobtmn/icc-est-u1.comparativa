package app;
import control.ControladorExperimentos;
import modelo.Results;

import java.util.List;


public class App {
    public static void main(String[] args) {

        ControladorExperimentos controlador = new ControladorExperimentos();
        List<Results> resultados = controlador.ejecutar();

        // se imprime la lista completa solo al terminar todas las mediciones
        System.out.println("=== RESULTADOS (" + resultados.size() + ") ===");
        for (Results r : resultados) {
            System.out.println(r);
        }

        
    }

        
          




      
      
    
      
        
    }

