import java.util.ArrayList;
import java.util.Random;

final class Bombo {

    private final ArrayList<Integer> bolas;
    private final Random rand;

    public Bombo() {
        this.bolas = new ArrayList<>();
        this.rand = new Random();
        int numMinimo = 0;
        int numMaximo = 91;
        for (int i = numMinimo; i <= numMaximo; i++)
            bolas.add(i);
    }

    public int sacarBola(){
        int posicion = rand.nextInt(bolas.size());
        int bolaSacada = bolas.get(posicion);
        bolas.remove(posicion);
        return bolaSacada;
    }
}
