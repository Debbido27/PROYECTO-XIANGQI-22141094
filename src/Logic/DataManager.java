
package Logic;


public interface DataManager {
    boolean crearPlayer(String user, String pass);
    boolean login(String user, String pass);
    String eliminarCuenta();
}
