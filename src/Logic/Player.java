
package Logic;


public class Player {
    //Atributos
    
    String username;
    String password;
    private int puntos;
    private String [] logs;
    private int CuentaLog;
    
    
    public Player(String username, String password){
        this.username=username;
        this.password=password;
        this.puntos=0;
        this.logs= new String [10];
        this.CuentaLog=0;
    }
    
    
}
