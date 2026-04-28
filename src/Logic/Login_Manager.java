
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
         
         
         public boolean login(String username, String password){
             Player p = buscarPlayer(username);
             if(p != null && p.getPassword().equals(password)){
                 CurrentUser=p;
                 return true;
             }
             return false;
         }
         
         
         public void logout(){
            CurrentUser=null;
         }
         
         
         
         public String verDatos(){
            if (CurrentUser==null){
                return "Error, no hay usuario";
            }
            return "Tus Datos "+CurrentUser.toString();
        } 
         
            public String modificarDatos(String newUsername, String newPassword){
            if(CurrentUser==null){
                return "Error, no hay usuario";
            }

            if(newUsername.isEmpty()||newPassword.isEmpty()){
                return "ERROR!, USUARIO O CONTRASENA NO PUEDEN ESTAR VACIOS!!";
            }


            String userNameNow=CurrentUser.getUsername();

            if(!userNameNow.equals(newUsername)){
                Player existente = buscarPlayer(newUsername);
            if(existente!=null){
                return "Error el usuario "+newUsername+" Ya existe";
            }
            }

             CurrentUser.setUsername(newUsername);
             CurrentUser.setPassword(newPassword);
            return "Datos modificados exitosamente "+"Nuevo Usuario: "+newUsername+"\n"+"Nueva Contrasena: "+newPassword+"\n";
       }


         
         
            
            public Player [] getRankingJugadores(){
           Player[] ranking = new Player[totalPlayers];
            for (int i = 0; i < totalPlayers; i++) {
                ranking[i] = players[i];
            }

            //burbuja
            for (int i = 0; i < totalPlayers - 1; i++) {
                for (int j = 0; j < totalPlayers - 1 - i; j++) {
                    if (ranking[j].getPuntos() < ranking[j + 1].getPuntos()) {
                        Player temp = ranking[j];
                        ranking[j] = ranking[j + 1];
                        ranking[j + 1] = temp;
                    }
                }
            }
            return ranking;
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
