import java.util.ArrayList;
import java.util.Random;

final class Bombo {

    private ArrayList<Integer> bolas;

    public Bombo() {
        this.bolas = new ArrayList<Integer>();
        int numMinimo=0;
        int numMaximo=91;
        for(int i=numMinimo; i<=numMaximo; i++)
            bolas.add(i);
    }

    public int sacarBola(){
        Random rand = new Random();
        int posicion = rand.nextInt(bolas.size());
        int bolaSacada = bolas.get(posicion);
        bolas.remove(posicion);
        return bolaSacada;
    }
}
