package db;

import dda.Geld;
import java.sql.*;

public class DBController {

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
                String insertQuery = "INSERT INTO finance (id, erstellungsdatum, betrag,"
                    + " kommentar, bezahlmethode, kategorieausgabe)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, geld.getID().toString());
            preparedStatement.setString(2, geld.getErstellungsdatum().toString());
            preparedStatement.setDouble(3, geld.getBetrag());
            preparedStatement.setString(4, geld.getKommentar());
            preparedStatement.setInt(5, geld.getBezahlmethode());
            preparedStatement.setInt(6, geld.getKategorieAusgabe());

            preparedStatement.executeUpdate();

            System.out.println("Stuff as has been written");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
