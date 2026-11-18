
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {

        Generador g = new Generador();
        Benchmark b = new Benchmark();
         List<Integer>list = g.generate(100);
         System.out.println(list);


         Results benGenList = b.medir(
            ()-> g.generate(100),
            "generacion de listado ");
    

            System.out.println(benGenList);




      
      
    
      
        
    }
}
