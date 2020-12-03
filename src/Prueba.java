public class Prueba {
    public static void main(String[] args)
    {
        Sala sala = new Sala(1);
        sala.nuevoParticipante("Pepe", 2);
        sala.nuevoParticipante("Artur", 2);
        sala.comenzarBingo();
        Printer printerString = new StandardOutputPrinter();
        printerString.print(sala.obtenerGanadores());
        printerString.print("Cartones con linea:");
        PrinterCarton printer = new StandardOutputPrinterCarton();
        for(int[][] numeros : sala.obtenerCartonesLinea()){
            printer.printCarton(numeros);
        }
        printerString.print("\n");
        printerString.print("Cartones con bingo:");
        for(int[][] numeros : sala.obtenerCartonesBingo()){
            printer.printCarton(numeros);
        }
    }
}
