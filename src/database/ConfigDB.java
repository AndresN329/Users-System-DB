package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/User_Management";
            String user = "root";
            String password = "yourpassword";
            //establishes the connection
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Successful connection");

        } catch (ClassNotFoundException error) {
            System.out.println("driver is not installed" + error.getMessage());
        } catch (SQLException error) {
            System.out.println("error connecting to the database" + error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection(){
        try{
            if (objConnection != null){
                objConnection.close();
                System.out.println("Connection close succesfully");
            }
        } catch (SQLException error) {
            System.out.println("Error" + error.getMessage());
        }
    }
}
