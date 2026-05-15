
package Logic;


public class Login_Manager implements DataManager {
    
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
                 if(p != null && p.getUsername().equals(username)){
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
             if(!validarPassword(password)){
                return false;
            }
             Player nuevo = new Player(username,password);
             players[totalPlayers]=nuevo;
             totalPlayers++;
             
             CurrentUser=nuevo;
             return true;
             
         }
         
         
         public  boolean login(String username, String password){
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
            if(!validarPassword(newPassword)){
                return "Error: la contraseña debe tener 5 caracteres, incluir una mayúscula, una minuscula y un numero";
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

            
            private boolean validarPassword(String password) {
            if (password.length() != 5) {
                return false;
            }

            boolean hasMayuscula = false;
            boolean hasMinuscula = false;
            boolean hasNumero = false;

            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasMayuscula = true;
                } else if (Character.isLowerCase(c)) {
                    hasMinuscula = true;
                } else if (Character.isDigit(c)) {
                    hasNumero = true;
                }
            }

            return hasMayuscula && hasMinuscula && hasNumero;
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

         
         
         
          public String[] getMisUltimosJuegos() {
            if (CurrentUser == null) {
                return new String[]{"No hay usuario logueado"};
            }
            return CurrentUser.getLogs();
        }
   
          
          
          
          
         public String eliminarCuenta(){
            if(CurrentUser==null){
                return "Error, no hay usuario logueado";
            }
            //guardar username
            String usernameEliminar=CurrentUser.getUsername();
            //bandera
             int pocision =-1;
             for (int i = 0; i < totalPlayers; i++) {
                if(players[i].getUsername().equals(usernameEliminar)){
                    //cambio bandear
                    pocision=i;
                    break;
                }
            }


             if(pocision==-1){
                 return "Error, usuario No encontrado";    
                 }

                    for (int i = pocision; i < totalPlayers-1; i++) {
                        //cambio de pocision a siguiente pocision
                     players[i]=players[i+1];
                    }

                 players[totalPlayers-1]=null;
                 totalPlayers--;

                 CurrentUser=null;
                 return("Cuenta elinada exitosamente"+" El usuario"+usernameEliminar+"Ya no existe");
        }
 
            
      
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
  
        
        @Override
        public boolean guardarPartida(String log){
          try {
        if (CurrentUser == null) return false;
        CurrentUser.agregarLog(log);
        return true;
       } catch (Exception e) {
        return false;
    }
        }
        
        @Override
        public String [] cargarPartidas(){
             try {
        if (CurrentUser == null) return new String[]{"No hay usuario logueado"};
        return CurrentUser.getLogs();
        } catch (Exception e) {
            return new String[]{"Error al cargar partidas"};
        }
        }
        
        
      
        
    
    
}
