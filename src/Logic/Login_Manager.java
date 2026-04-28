
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
    
    
    
            //CLASE buscar jugador, aca se pide el username,
        //FOR EACH
         public Player buscarPlayer(String username){
             for (Player p: getPlayers()) {
                 if(p.getUsername().equals(username)){
                     return p;
                 }
             }
             return null;
         }

         
         
         public boolean crearPlayer (String username, String password){
             if(buscarPlayer(username)!=null){
                 return false;
             }
             if(totalPlayers>=MAX_PLAYERS){
              return false;   
             }
             
             Player nuevo = new Player(username,password);
             players[totalPlayers]=nuevo;
             totalPlayers++;
             
             CurrentUser=nuevo;
             return true;
             
         }
         
         
         
         
         
 
        //COPIAS PARA EVITAR LOS NULL Y SE CREA CON LA POCISION CABAL DE JUGADORES
        public Player [] getPlayers(){
            Player [] copia = new Player [totalPlayers];
            for (int i = 0; i < totalPlayers; i++) {
                copia[i]=players[i];
            }
            return copia;
        }

        public Player getCurrentUser(){
            return CurrentUser;
        }

        public int getTotalPlayer(){
            return totalPlayers;
        }
  
    
    
}
