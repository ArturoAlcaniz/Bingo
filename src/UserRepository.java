import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserRepository {
    private ArrayList<User> usuariosGenerados;
    private final ConcurrentHashMap<Integer, User> usuariosEnPartida;

    public UserRepository() {
        this.usuariosGenerados = new ArrayList<>();
        this.usuariosEnPartida = new ConcurrentHashMap<>();
    }

    public final int getId(){
        return usuariosGenerados.size()+1;
    }

    public final void addUser(User user) {
        usuariosGenerados.add(user);
    }

    public final ConcurrentMap<Integer, User> getUsersPartida() {
        return usuariosEnPartida;
    }

    public void comienzaPartida() {
        for (User user : usuariosGenerados) {
            usuariosEnPartida.put(user.getId(), user);
        }
        this.usuariosGenerados = new ArrayList<>();
    }
}
