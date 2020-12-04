import java.util.logging.Logger;

public class Prueba {

    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Prueba.class.getName());
        Sala sala = new Sala(1);
        sala.nuevoParticipante("Pepe", 2);
        sala.nuevoParticipante("Artur", 2);
        sala.comenzarBingo();
        logger.info(sala.obtenerGanadores());
        logger.info("Cartones con linea:");
        StringPrinterCarton printer = new StringPrinterCarton();
        for (int[][] numeros : sala.obtenerCartonesLinea()) {
            logger.info("\n" + printer.printCarton(numeros));
        }
        logger.info("\n");
        logger.info("Cartones con bingo:");
        for (int[][] numeros : sala.obtenerCartonesBingo()) {
            logger.info("\n" + printer.printCarton(numeros));
        }
    }
}
