import java.util.Arrays;
import java.util.stream.Collectors;

final class Carton {
    private int id;
    private int[][] numeros;

    public Carton(int id) {
        this.id = id;
        int dimensionFilas = 3;
        int dimensionColumnas = 5;
        int numMinimo=0;
        int numMaximo=91;
        this.numeros = new int[dimensionFilas][dimensionColumnas];
        for (int i=0; i<this.numeros.length;i++)
        {
            for(int j=0; j<this.numeros[i].length;j++)
            {
                int numAleatorio = (int)Math.floor(Math.random()*(numMinimo-numMaximo)+numMaximo);

                while(Arrays.stream(numeros).collect(Collectors.toList()).contains(numAleatorio))
                    numAleatorio = (int)Math.floor(Math.random()*(numMinimo-numMaximo)+numMaximo);

                this.numeros[i][j] = numAleatorio;
            }
        }
    }

    public int getId() {
        return id;
    }

    public int[][] getNumeros() {
        return numeros;
    }
}
