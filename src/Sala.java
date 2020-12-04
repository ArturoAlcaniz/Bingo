import java.util.ArrayList;
import java.util.List;

final class Sala {
    private final Bombo bombo;
    private final CartonRepository cartonRepository;
    private final UserRepository userRepository;
    private boolean lineaCantada;
    private boolean bingoCantado;
    private final ArrayList<String> ganadoresLinea;
    private final ArrayList<String> ganadoresBingo;
    private final ArrayList<int[][]> cartonesLinea;
    private final ArrayList<int[][]> cartonesBingo;

    public Sala() {
        this.bombo = new Bombo();
        this.cartonRepository = new CartonRepository();
        this.userRepository = new UserRepository();
        this.lineaCantada = false;
        this.bingoCantado = false;
        this.ganadoresLinea = new ArrayList<>();
        this.ganadoresBingo = new ArrayList<>();
        this.cartonesLinea = new ArrayList<>();
        this.cartonesBingo = new ArrayList<>();
    }

    public void nuevoParticipante(String nombre, int cartones) {
        User user = new User(userRepository.getId(), nombre, crearCartones(cartones));
        userRepository.addUser(user);
    }

    public List<Integer> crearCartones(int cartones) {
        List<Integer> cartonesCreados = new ArrayList<>();
        for (int i = 0; i < cartones; i++) {
            int idCarton = cartonRepository.getId();
            Carton carton = new Carton(idCarton);
            cartonRepository.addCarton(carton);
            cartonesCreados.add(idCarton);
        }
        return cartonesCreados;
    }

    public void comenzarBingo() {
        if(userRepository.getId()>0){
            userRepository.comienzaPartida();
            cartonRepository.comienzaPartida();
            while (!lineaCantada || !bingoCantado){
                int bola = bombo.sacarBola();
                cartonRepository.bolaSacada(bola);
                if(!cartonRepository.comprobarLinea().isEmpty() && !lineaCantada) {
                    lineaCantada = !cartonRepository.comprobarLinea().isEmpty();
                    List<Integer> ganadores = cartonRepository.comprobarLinea();
                    guardarGanadores(ganadores, cartonesLinea, ganadoresLinea);

                }
                if (!cartonRepository.comprobarBingo().isEmpty() && !bingoCantado) {
                    bingoCantado = !cartonRepository.comprobarBingo().isEmpty();
                    List<Integer> ganadores = cartonRepository.comprobarBingo();
                    guardarGanadores(ganadores, cartonesBingo, ganadoresBingo);
                }
            }
        }
    }

    private void guardarGanadores(List<Integer> ganadores, ArrayList<int[][]> cartonesLinea, ArrayList<String> ganadoresLinea) {
        for (Integer i : ganadores) {
            int[][] n = new int[cartonRepository.getNumerosPartida(i).length][cartonRepository.getNumerosPartida(i)[0].length];
            copiarCarton(n, cartonRepository.getNumerosPartida(i));
            cartonesLinea.add(n);
            for (User user : userRepository.getUsersPartida().values()) {
                if (user.getCartones().contains(i))
                    ganadoresLinea.add(user.getNombre());
            }
        }
    }

    public void copiarCarton(int[][] destino, int[][] origen) {
        System.arraycopy(origen, 0, destino, 0, origen.length);
    }

    public String obtenerGanadores() {
        StringBuilder ganadoresL = new StringBuilder();
        StringBuilder ganadoresB = new StringBuilder();
        for (String ganador : ganadoresLinea) {
            ganadoresL.append(ganador).append(" ");
        }
        for (String ganador : ganadoresBingo) {
            ganadoresB.append(ganador).append(" ");
        }
        return "Ganadores de linea:\n" + ganadoresL.toString() + "\n" +
                "Ganadores de bingo:\n" + ganadoresB.toString();
    }

    public List<int[][]> obtenerCartonesLinea() {
        return cartonesLinea;
    }

    public List<int[][]> obtenerCartonesBingo() {
        return cartonesBingo;
    }
}
