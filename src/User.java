import java.util.List;

final class User {
    private final int id;
    private final String nombre;
    private final List<Integer> cartones;

    public User(int id, String nombre, List<Integer> cartones) {
        this.id = id;
        this.nombre = nombre;
        this.cartones = cartones;
    }

    public int getId() {
        return id;
    }
    public String getNombre() { return nombre; }

    public List<Integer> getCartones() {
        return cartones;
    }
}
