import dda.Geld;
public class Main {
    public static void main(String[] args) {




        Geld g1 = new Geld();
        Geld g2 = new Geld();
        Geld g3 = new Geld(15, "Mangos", 1, 2);


        System.out.println(g1.toString());
        System.out.println(g2.toString());
        System.out.println(g3.toString());
    }
}