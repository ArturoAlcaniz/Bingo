import java.util.logging.Logger;

public class Prueba {

    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Prueba.class.getName());
        Sala sala = new Sala();
        sala.nuevoParticipante("Pepe", 2);
        sala.nuevoParticipante("Artur", 2);
        sala.comenzarBingo();
        String ganadores = sala.obtenerGanadores();
        logger.info(ganadores);
        logger.info("Cartones con linea:");
        StringPrinterCarton printer = new StringPrinterCarton();
        StringBuilder cartonesConLinea = new StringBuilder();
        for (int[][] numeros : sala.obtenerCartonesLinea()) {
            cartonesConLinea.append("\n").append(printer.printCarton(numeros));
        }
        String cartonesLinea = cartonesConLinea.toString();
        logger.info(cartonesLinea);
        logger.info("\n");
        logger.info("Cartones con bingo:");
        StringBuilder cartonesConBingo = new StringBuilder();
        for (int[][] numeros : sala.obtenerCartonesBingo()) {
            cartonesConBingo.append("\n").append(printer.printCarton(numeros));
        }
        String cartonesBingo = cartonesConBingo.toString();
        logger.info(cartonesBingo);
    }
}
