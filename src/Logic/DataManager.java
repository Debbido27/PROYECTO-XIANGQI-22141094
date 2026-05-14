
package Logic;


public interface DataManager {
    boolean crearPlayer(String user, String pass);
    boolean login(String user, String pass);
    String eliminarCuenta();
    Player[] getPlayers();
    Player buscarPlayer(String username);
    boolean guardarPartida(String log);
    String [] cargarPartidas();
   
}
