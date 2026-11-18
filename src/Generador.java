import java.util.ArrayList;
import java.util.List;

public class Generador {
    //genera un listado tamaño size
    //con numeros alteatorios de 0-size
    public static List<Integer> generate(int size) {
        List<Integer> list= new ArrayList<>();
        for(int i =  0 ; i <size ; i++){
            list.add((int) (Math.random()*size));
        }
        return list;


        
    }
    
}
