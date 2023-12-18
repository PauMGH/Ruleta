import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Casilla {

    int valor;
    Color color;

    public Casilla(int valor, Color color){
        this.valor = valor;
        this.color = color;
    }

    public int getValor() {
        return valor;
    }
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return  colorize(valor + "", color.getColor());
    }

    public enum Color {
        RED(Attribute.RED_TEXT()),
        BLACK(Attribute.BLACK_TEXT()),
        GREEN(Attribute.GREEN_TEXT());

        private final Attribute color;

        Color(Attribute color){
            this.color = color;
        }

        public Attribute getColor() {
            return color;
        }
    }
}
