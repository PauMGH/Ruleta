public class Jugador {
    private final String nombre;
    private int saldo;
    private String apuesta;
    private int cantidad;
    private boolean seQueda;


    public Jugador(String nombre, int saldo){
        this.nombre = nombre;
        this.saldo = saldo;
        this.apuesta = "";
        this.cantidad = 0;
        this.seQueda = true;
    }

    public void ganaApuesta(int ganancias){
        this.saldo += ganancias;
    }

    public void pierdeApuesta(int perdidas){
        this.saldo -= perdidas;
    }

    public String getNombre() {
        return nombre;
    }
    public int getSaldo() {
        return saldo;
    }
    public String getApuesta() {
        return apuesta;
    }
    public int getCantidad() {
        return cantidad;
    }
    public boolean getSeQueda() {
        return seQueda;
    }

    public void setApuesta(String apuesta) {
        this.apuesta = apuesta;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setSeQueda(boolean seQueda) {
        this.seQueda = seQueda;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", saldo=" + saldo +
                ", apuesta='" + apuesta + '\'' +
                '}';
    }
}
