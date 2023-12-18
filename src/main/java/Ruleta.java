import java.util.Arrays;
import java.util.Collections;

public class Ruleta {
    Casilla[] casillas;

    public Ruleta(){
        this.casillas = new Casilla[37];
        boolean par;
        for (int i = 0; i < casillas.length; i++){
            if (i == 0) {
                casillas[i] = new Casilla(0, Casilla.Color.GREEN);
            } else if (i == 10) {
                casillas[i] = new Casilla(10, Casilla.Color.BLACK);
            }else if(i == 28){
                casillas[i] = new Casilla(28, Casilla.Color.BLACK);
            }else{
                if (reducePar(i)){
                    casillas[i] = new Casilla(i, Casilla.Color.BLACK);
                }else {
                    casillas[i] = new Casilla(i, Casilla.Color.RED);
                }
            }
        }
    }

    public boolean reducePar(int num){
        if (num > 10){
         num = num/10 + num%10;
        }
        if (num > 10){
            num = num/10 + num%10;
        }

        return num % 2 == 0;
    }

    public void cambiarOrden(){
        Collections.shuffle(Arrays.asList(casillas));
    }

    public Casilla tiroDeBola(){
        return casillas[FuncionesApoyo.rand(0, 36)];
    }

    public Casilla[] getCasillas() {
        return casillas;
    }

    @Override
    public String toString() {
        String aux = "";
        for (Casilla casilla: casillas){
            aux += casilla + " ";
        }
        return aux;
    }
}
