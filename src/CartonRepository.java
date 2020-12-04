import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class CartonRepository {
    private ArrayList<Carton> cartonesGenerados;
    private final ConcurrentHashMap<Integer, Carton> cartonesEnPartida;

    public CartonRepository() {
        this.cartonesGenerados = new ArrayList<>();
        this.cartonesEnPartida = new ConcurrentHashMap<>();
    }

    public final void addCarton(Carton carton){
        cartonesGenerados.add(carton);
    }
    public final int getId(){
        return cartonesGenerados.size()+1;
    }
    public final int[][] getNumerosPartida(int i) { return cartonesEnPartida.get(i).getNumeros(); }
    public final void bolaSacada(int bola){
        for(Carton carton : cartonesEnPartida.values()){
            for(int i=0; i<carton.getNumeros().length; i++) {
                for (int j=0; j < carton.getNumeros()[0].length; j++) {
                    if(carton.getNumeros()[i][j] == bola)
                        carton.getNumeros()[i][j] = -1;
                }
            }
        }
    }
    public final ArrayList<Integer> comprobarLinea(){
        ArrayList<Integer> ganadoresLinea = new ArrayList<>();
        for(Carton carton : cartonesEnPartida.values()){
            for(int i=0; i<carton.getNumeros().length; i++) {
                int numTachados = 0;
                for (int j=0; j < carton.getNumeros()[0].length; j++) {
                    if(carton.getNumeros()[i][j] == -1)
                        numTachados++;
                }
                if(numTachados == carton.getNumeros()[i].length)
                    ganadoresLinea.add(carton.getId());
            }
        }
        return ganadoresLinea;
    }
    public final ArrayList<Integer> comprobarBingo(){
        ArrayList<Integer> ganadoresBingo = new ArrayList<Integer>();
        for(Carton carton : cartonesEnPartida.values()){
            int numTachados = 0;
            for(int i=0; i<carton.getNumeros().length; i++) {
                for (int j=0; j < carton.getNumeros()[0].length; j++) {
                    if(carton.getNumeros()[i][j] == -1)
                        numTachados++;
                }
                if(numTachados == (carton.getNumeros().length*carton.getNumeros()[0].length))
                    ganadoresBingo.add(carton.getId());
            }
        }
        return ganadoresBingo;
    }

    public void comienzaPartida() {
        for(Carton carton : cartonesGenerados){
            cartonesEnPartida.put(carton.getId(), carton);
        }
        this.cartonesGenerados = new ArrayList<Carton>();
    }
}
