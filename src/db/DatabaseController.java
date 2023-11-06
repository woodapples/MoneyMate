package db;

import dda.Geld;
import java.sql.*;

public class DatabaseController {

    private static Connection connection;

    public static Connection getConnection(String path){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(path);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(connection);
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection to SQLite has been finished");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void createNewDatabase(String path, String fileName) {
        Connection conn = getConnection(path + fileName);
        try(Statement statement = conn.createStatement()) {
            String tableSettings = "CREATE TABLE IF NOT EXISTS finance(\n"
                    + " id UUID PRIMARY KEY, \n"
                    + " erstellungsdatum instant NOT NULL, \n"
                    + " erstellungsuhrzeit instant NOT NULL, \n"
                    + " betrag double NOT NULL, \n"
                    + " kommentar string NOT NULL, \n "
                    + " bezahlmethode integer, \n"
                    + " kategorieausgabe integer \n"
                    + ");";
            statement.execute(tableSettings);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void writeToDatabase(Geld geld) {

        Connection conn = connection;
        try {
                String insertQuery = "INSERT INTO finance (id, erstellungsdatum, erstellungsuhrzeit, betrag,"
                    + " kommentar, bezahlmethode, kategorieausgabe)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, geld.getID().toString());
            preparedStatement.setString(2, geld.getErstellungsDatum().toString());
            preparedStatement.setString(3, geld.getErstellungsZeit().toString());
            preparedStatement.setDouble(4, geld.getBetrag());
            preparedStatement.setString(5, geld.getKommentar());
            preparedStatement.setInt(6, geld.getBezahlmethode());
            preparedStatement.setInt(7, geld.getKategorieAusgabe());

            preparedStatement.executeUpdate();

            System.out.println("Stuff as has been written");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readFromDatabasePrintTerminal(String beginDatum, String endDatum) {
        Connection conn = connection;

        try (Statement statement = conn.createStatement()) {

            String query = "SELECT * FROM finance WHERE erstellungsdatum >= '" + beginDatum + "' AND erstellungsdatum <= '" + endDatum + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String UUID = resultSet.getString("ID");
                String erstellungsdatum = resultSet.getString("erstellungsdatum");
                String erstellungszeit = resultSet.getString("erstellungsuhrzeit");
                double betrag = resultSet.getDouble("Betrag");
                String kommentar = resultSet.getString("Kommentar");
                int bezahlmethode = resultSet.getInt("Bezahlmethode");
                int kategorieAusgabe = resultSet.getInt("Kategorieausgabe");

                System.out.println("UUID: " + UUID);
                System.out.println("Erstellungsdatum: " + erstellungsdatum);
                System.out.println("Erstellungszeit " + erstellungszeit);
                System.out.println("Betrag: " + betrag);
                System.out.println("Kommentar: " + kommentar);
                System.out.println("Bezahlmethode: " + bezahlmethode);
                System.out.println("Kategorie Ausgabe: " + kategorieAusgabe);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDatabaseEntry(String UUID) {

        Connection conn = connection;
        try (Statement statement = conn.createStatement()) {

            String query = "DELETE FROM finance WHERE id = '" + UUID + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);


            int geloeschteZeilen = preparedStatement.executeUpdate();

            if (geloeschteZeilen > 0) {
                System.out.println("Eintrag wurde gelöscht.");
            } else {
                System.out.println("Eintrag mit ID " + UUID + " wurde nicht gefunden.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
