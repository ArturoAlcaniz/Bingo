import java.util.ArrayList;

final class Sala {
    private int id;
    private Bombo bombo;
    private CartonRepository cartonRepository;
    private UserRepository userRepository;
    private boolean lineaCantada;
    private boolean bingoCantado;
    private ArrayList<String> ganadoresLinea;
    private ArrayList<String> ganadoresBingo;
    private ArrayList<int[][]> cartonesLinea;
    private ArrayList<int[][]> cartonesBingo;

    public Sala(int id) {
        this.id = id;
        this.bombo = new Bombo();
        this.cartonRepository = new CartonRepository();
        this.userRepository = new UserRepository();
        this.lineaCantada = false;
        this.bingoCantado = false;
        this.ganadoresLinea = new ArrayList<String>();
        this.ganadoresBingo = new ArrayList<String>();
        this.cartonesLinea = new ArrayList<int[][]>();
        this.cartonesBingo = new ArrayList<int[][]>();
    }

    public void nuevoParticipante(String nombre, int cartones) {
        User user = new User(userRepository.getId(), nombre, crearCartones(cartones));
        userRepository.addUser(user);
    }

    public ArrayList<Integer> crearCartones(int cartones){
        ArrayList<Integer> cartonesCreados = new ArrayList<Integer>();
        for(int i=0; i<cartones; i++){
            int idCarton = cartonRepository.getId();
            Carton carton = new Carton(idCarton);
            cartonRepository.addCarton(carton);
            cartonesCreados.add(idCarton);
        }
        return cartonesCreados;
    }

    public void comenzarBingo(){
        if(userRepository.getId()>0){
            userRepository.comienzaPartida();
            cartonRepository.comienzaPartida();
            while (!lineaCantada || !bingoCantado){
                int bola = bombo.sacarBola();
                cartonRepository.bolaSacada(bola);
                if(!cartonRepository.comprobarLinea().isEmpty() && !lineaCantada) {
                    lineaCantada = !cartonRepository.comprobarLinea().isEmpty();
                    ArrayList<Integer> ganadores = cartonRepository.comprobarLinea();
                    for(Integer i : ganadores){
                        int[][] n = new int[cartonRepository.getNumerosPartida(i).length][cartonRepository.getNumerosPartida(i)[0].length];
                        copiarCarton(n, cartonRepository.getNumerosPartida(i));
                        cartonesLinea.add(n);
                        for(User user : userRepository.getUsersPartida().values()){
                            if(user.getCartones().contains(i))
                                ganadoresLinea.add(user.getNombre());
                        }
                    }

                }
                if(!cartonRepository.comprobarBingo().isEmpty() && !bingoCantado) {
                    bingoCantado = !cartonRepository.comprobarBingo().isEmpty();
                    ArrayList<Integer> ganadores = cartonRepository.comprobarBingo();
                    for(Integer i : ganadores){
                        int[][] n = new int[cartonRepository.getNumerosPartida(i).length][cartonRepository.getNumerosPartida(i)[0].length];
                        copiarCarton(n, cartonRepository.getNumerosPartida(i));
                        cartonesBingo.add(n);
                        for(User user : userRepository.getUsersPartida().values()){
                            if(user.getCartones().contains(i))
                                ganadoresBingo.add(user.getNombre());
                        }
                    }
                }
            }
        }
    }

    public void copiarCarton(int[][] destino, int[][] origen){
        for(int i=0; i<destino.length; i++){
            for(int j=0; j<destino[0].length; j++){
                destino[i][j] = origen[i][j];
            }
        }
    }

    public String obtenerGanadores(){
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

    public ArrayList<int[][]> obtenerCartonesLinea(){
        return cartonesLinea;
    }

    public ArrayList<int[][]> obtenerCartonesBingo(){
        return cartonesBingo;
    }
}
