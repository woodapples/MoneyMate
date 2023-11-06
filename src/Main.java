import dda.Geld;
import db.*;

public class Main {
    public static void main(String[] args) {

        String urlTuxi = "jdbc:sqlite:src/db/MoneySafe.db";
        String url = "jdbc:sqlite:src/db/";

        Geld g1 = new Geld();
        Geld g2 = new Geld(36, "ETFs", 0,1);
        Geld g3 = new Geld(15, "Mangos", 1, 5);
        Geld g4 = new Geld(-115, "B12 11er Karte", 1, 2);

        //System.out.println(g4.toString());
        //DatabaseController.createNewDatabase(url, "MoneySafe.db");

        // DBController.writeToDatabase(urlTuxi, g3);
        DatabaseController.getConnection(urlTuxi);

/*
        DatabaseController.writeToDatabase(g1);
        DatabaseController.writeToDatabase(g2);
        DatabaseController.writeToDatabase(g3);
        DatabaseController.writeToDatabase(g4);
*/
        DatabaseController.readFromDatabasePrintTerminal("2023-11-01", "2023-11-30");

        //DatabaseController.deleteDatabaseEntry("77fcd198-7bd1-476f-a16d-11e64d81c603");

        DatabaseController.closeConnection();
    }
}