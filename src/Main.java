import dda.Geld;
import db.*;

public class Main {
    public static void main(String[] args) {

        String urlTuxi = "jdbc:sqlite:src/db/MoneySafe.db";
        String url = "jdbc:sqlite:src/db/";

        Geld g1 = new Geld();
        Geld g2 = new Geld();
        Geld g3 = new Geld(15, "Mangos", 1, 5);
        Geld g4 = new Geld(-115, "B12 11er Karte", 1, 2);

        // DBController.createNewDatabase(url, "MoneySafe.db");

        // DBController.writeToDatabase(urlTuxi, g3);
        DBController.getConnection(urlTuxi);

        DBController.writeToDatabase(g1);
        DBController.writeToDatabase(g3);
        DBController.writeToDatabase(g4);

        DBController.closeConnection();
    }
}