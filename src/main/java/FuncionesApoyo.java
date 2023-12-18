import java.util.Scanner;

public class FuncionesApoyo {
    public static int rand(int min, int max){return (int)(Math.random()*(max-min+1)+min);}

    public static String getString(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextLine();
    }

    public static int getInteger(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        while (!sc.hasNextInt()) {
            System.out.println("Debes darme un numero.");
            sc.next();
        }
        return sc.nextInt();
    }


}
