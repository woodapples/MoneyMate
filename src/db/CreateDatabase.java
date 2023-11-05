package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    public static void createNewDatabase(String path, String fileName) {

        String url = path + fileName;

        String tableSettings = "CREATE TABLE I NOT EXISTS finance(\n"
                + " id integer PRIMARY KEY, \n"
                + " erstellungsdatum instant NOT NULL, \n"
                + " betrag double NOT NULL, \n"
                + " kommentar string NOT NULL, \n "
                + " bezahlmethode integer, \n"
                + " kategorieausgabe integer \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

