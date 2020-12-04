import java.util.Random;

final class Carton {
    private final int id;
    private final int[][] numeros;

    public Carton(int id) {
        this.id = id;
        Random rand = new Random();
        int dimensionFilas = 3;
        int dimensionColumnas = 5;
        int numMaximo = 91;
        this.numeros = new int[dimensionFilas][dimensionColumnas];
        for (int i = 0; i < this.numeros.length; i++) {
            for(int j=0; j<this.numeros[i].length;j++) {
                int numAleatorio = rand.nextInt(numMaximo + 1);

                while (contains(numeros, numAleatorio))
                    numAleatorio = rand.nextInt(numMaximo + 1);

                this.numeros[i][j] = numAleatorio;
            }
        }
    }

    private boolean contains(int[][] numeros, int numero) {
        boolean contain = false;

        for (int[] ints : numeros) {
            for (int anInt : ints) {
                if (anInt == numero) {
                    contain = true;
                    break;
                }
            }
        }

        return contain;
    }

    public int getId() {
        return id;
    }

    public int[][] getNumeros() {
        return numeros;
    }
}
