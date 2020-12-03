import java.util.ArrayList;

final class User {
    private int id;
    private String nombre;
    private ArrayList<Integer> cartones;

    public User(int id, String nombre, ArrayList<Integer> cartones) {
        this.id = id;
        this.nombre = nombre;
        this.cartones = cartones;
    }

    public int getId() {
        return id;
    }
    public String getNombre() { return nombre; }

    public ArrayList<Integer> getCartones() {
        return cartones;
    }
}
