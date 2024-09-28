package BRM;
import java.sql.*;
import javax.swing.*;

public class BookFrame {
    Connection con;
    void getConnection(){
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_first","root","csislove");
            System.out.println("Connected successfully!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public BookFrame(){
        getConnection();
    }
}
