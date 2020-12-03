final class StandardOutputPrinterCarton implements PrinterCarton{
    public void printCarton(int[][] numeros) {
        for(int i=0; i<numeros.length; i++) {

            for (int j = 0; j < numeros[i].length; j++) {
                if(numeros[i][j] == -1)
                    System.out.print("X  ");
                else if(numeros[i][j] < 10)
                    System.out.print("0"+numeros[i][j]+" ");
                else
                    System.out.print(numeros[i][j]+" ");
            }
            System.out.println();
        }
    }
}
