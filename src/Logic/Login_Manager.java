
package Logic;

public class Login_Manager {
   
    //Statica para no declararse en cada metodo con capacidad para 50 jugadores
    private static final int MAX_PLAYERS=50;
    
    //Arreglos para la clase player para guarda jugadores
    private Player CurrentUser;
    private int totalPlayers;
    private Player [] players;
    
    
    public Login_Manager(){
        
        players = new Player[MAX_PLAYERS];
        totalPlayers=0;
        CurrentUser=null;
    }
    
    public boolean usuarioExiste(String username){
        for(Player p: players){
            if(p !=null && p.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    
   
    
    
}
