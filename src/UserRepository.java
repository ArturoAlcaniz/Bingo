import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private ArrayList<User> usuariosGenerados;
    private ConcurrentHashMap<Integer, User> usuariosEnPartida;

    public UserRepository() {
        this.usuariosGenerados = new ArrayList<User>();
        this.usuariosEnPartida = new ConcurrentHashMap<Integer, User>();
    }

    public final int getId(){
        return usuariosGenerados.size()+1;
    }
    public final void addUser(User user){
        usuariosGenerados.add(user);
    }
    public final String getNombreEnPartida(int id) { return usuariosEnPartida.get(id).getNombre(); }
    public final ConcurrentHashMap<Integer, User> getUsersPartida() { return usuariosEnPartida; }

    public void comienzaPartida() {
        Iterator<User> usuariosEnSala = usuariosGenerados.iterator();
        while(usuariosEnSala.hasNext()){
            User user = usuariosEnSala.next();
            usuariosEnPartida.put(user.getId(), user);
        }
        this.usuariosGenerados = new ArrayList<User>();
    }
}
