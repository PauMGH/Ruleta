public class Game {
    public static void main(String[] args) {
        Ruleta ruleta = new Ruleta();

        System.out.println("Bieeeeeeeeeenbenidoooooooos a la ruuuuuuuuuuuletaaaaaaaaaaaaa.");

        int nJugadores = FuncionesApoyo.getInteger("Cuantos jugadores van a jugar?");
        while (nJugadores > 10){
            nJugadores = FuncionesApoyo.getInteger("No se permite la participación de mas de 10 jugadores.");
        }

        if (nJugadores <= 0){
            System.out.println("Si no juega nadie gana la banca! Buenas noches a todo el mundo!");
        }else{
            Jugador[] jugadores = new Jugador[nJugadores];
            Jugador banca = new Jugador("Banca", 10000);
            Casilla casilla;
            boolean quedanJugadores = true;

            iniciaJugadores(jugadores);

            do {

                apuestas(nJugadores, jugadores, banca);
                System.out.println();
                System.out.println("Y la ruleta cae en...");
                ruleta.cambiarOrden();
                System.out.println(ruleta);
                System.out.println("...");
                casilla = ruleta.tiroDeBola();
                System.out.println(casilla);
                System.out.println("Por tanto gana...");
                compruebaGanadores(casilla, jugadores, banca);


                System.out.println();
            }while (banca.getSaldo() > 0 && quedanJugadores);
            System.out.println("Buena partida, buenas noches a todos!");
        }
    }

    private static void iniciaJugadores(Jugador[] jugadores) {
        for (int i = 0;i < jugadores.length; i++){
            String nombre = FuncionesApoyo.getString("Como te llamas jugador?");
            int saldo = FuncionesApoyo.getInteger("Cuanto dinero te vas a jugar?");
            while (saldo <= 0){
                saldo = FuncionesApoyo.getInteger("Aquí nadie viene y no se juega nada.");
            }
            jugadores[i] = new Jugador(nombre, saldo);
        }
    }

    private static void apuestas(int nJugadores, Jugador[] jugadores, Jugador banca) {
        for (int i = 0; i < nJugadores; i++){
            System.out.println("Bien jugador " + (i+1) + ", como quiere apostar?");
            jugadores[i].setApuesta(apuestasJugador());
            jugadores[i].setCantidad(decidirApuesta());
        }
    }

    public static String apuestasJugador(){
        String apuesta;

        do {
            System.out.println("N: apuesta a un número.");
            System.out.println("P: apuesta a que saldrá par o impar.");
            System.out.println("C: apuesta al color que saldrá.");

            apuesta = FuncionesApoyo.getString("");

            if (!apuesta.equalsIgnoreCase("N") &&
                    !apuesta.equalsIgnoreCase("P") &&
                    !apuesta.equalsIgnoreCase("C")){
                System.out.println("Debes darme una opción válida!");
            }
            System.out.println();
        }while (!apuesta.equalsIgnoreCase("N") &&
                !apuesta.equalsIgnoreCase("P") &&
                !apuesta.equalsIgnoreCase("C"));

        apuesta = jugadorApuesta(apuesta);

        return apuesta;
    }

    private static String jugadorApuesta(String apuesta) {
        if (apuesta.equalsIgnoreCase("N")){
            apuesta = apuestaNum();
        } else if (apuesta.equalsIgnoreCase("P")) {
            apuesta = apuestaPar();
        }else{
            apuesta = apuestaColor();
        }

        return apuesta;
    }

    private static String apuestaNum() {
        String rand = FuncionesApoyo.getString("Quieres apostar por un número al azar? (si/no)");
        while (!rand.equalsIgnoreCase("Si") && !rand.equalsIgnoreCase("No")){
            rand = FuncionesApoyo.getString("Solo responde si o no.");
        }
        System.out.println();
        String apuesta;

        if (rand.equalsIgnoreCase("Si")){
            System.out.println("Tenemoooos un valienteeee!");
            apuesta = String.valueOf(FuncionesApoyo.rand(1, 36));
        }else{
            int casilla = FuncionesApoyo.getInteger("En fin, dime en que casilla apostarás? (entre el 0 y 36)");
            while (casilla < 1 || casilla > 36){
                casilla = FuncionesApoyo.getInteger("Ese no es un número entre el 0 y el 36.");
            }
            apuesta = String.valueOf(casilla);
        }
        return apuesta;
    }

    private static String apuestaPar() {
        String apuesta = FuncionesApoyo.getString("Apuestas a que saldrá un número par (P) o uno impar (I)?");
        while (!apuesta.equalsIgnoreCase("P") && !apuesta.equalsIgnoreCase("I")){
            apuesta = FuncionesApoyo.getString("Esa respuesta no me vale, solo dime si crees que saldrá par (P) o impar (I).");
        }
        return apuesta;
    }

    private static String apuestaColor() {
        String apuesta = FuncionesApoyo.getString("Apuestas a que caerá en una casilla roja (R) o caerá en una casilla negra (N)?");
        while (!apuesta.equalsIgnoreCase("R") && !apuesta.equalsIgnoreCase("N")){
            apuesta = FuncionesApoyo.getString("Esa respuesta no me vale, solo dime si crees que saldrá roja (R) o negra (N).");
        }
        return apuesta;
    }

    private static int decidirApuesta() {
        int cantidad = FuncionesApoyo.getInteger("Y cuanto va a apostar?");
        while (cantidad < 0){
            cantidad = FuncionesApoyo.getInteger("Eh, eh, eh. Hay que apostar mas que 0 va campeón.");
        }
        return cantidad;
    }

    private static void compruebaGanadores(Casilla casilla, Jugador[] jugadores, Jugador banca) {
        if (casilla.getValor() != 0) {
            boolean gana = false;
            for (Jugador jugador : jugadores) {
                gana = jugadorGanaApuesta(jugador.getApuesta(), casilla);
                if (gana){
                    System.out.println("Enhorabuena " + jugador.getNombre() + ", has ganado tu apuesta!");
                    jugador.ganaApuesta(jugador.getCantidad());
                    banca.pierdeApuesta(jugador.getCantidad());
                }else{
                    System.out.println("Oooh, " + jugador.getNombre() + " ha perdido su apuesta!");
                    jugador.pierdeApuesta(jugador.getCantidad());
                    banca.ganaApuesta(jugador.getCantidad());
                }
            }
        }else{
            System.out.println("Miren eso! La banca siempre gana!");
            for (Jugador jugador : jugadores) {
                jugador.pierdeApuesta(jugador.getCantidad());
                banca.ganaApuesta(jugador.getCantidad());
            }
        }
    }

    private static boolean jugadorGanaApuesta(String apuesta, Casilla casilla) {
        boolean gana = false;
        if (apuesta.equalsIgnoreCase("P") || apuesta.equalsIgnoreCase("I")){
            gana = compruebaParidad(apuesta, casilla);
        } else if (apuesta.equalsIgnoreCase("R") || apuesta.equalsIgnoreCase("N")) {
            gana = compruebaColor(apuesta, casilla);
        }else{
            gana = Integer.parseInt(apuesta) == casilla.getValor();
        }

        return gana;
    }

    private static boolean compruebaParidad(String apuesta, Casilla casilla) {
        boolean gana = false;
        if (apuesta.equalsIgnoreCase("P")){
            gana = casilla.getValor()%2 == 0;
        } else{
            gana = casilla.getValor()%2 != 0;
        }
        return gana;
    }

    private static boolean compruebaColor(String apuesta, Casilla casilla) {
        boolean gana = false;
        if (apuesta.equalsIgnoreCase("R")){
            gana = casilla.getColor() == Casilla.Color.RED;
        } else{
            gana = casilla.getColor() == Casilla.Color.BLACK;
        }
        return gana;
    }
}
