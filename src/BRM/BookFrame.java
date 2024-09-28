package BRM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookFrame {
    Connection con;
    PreparedStatement ps;
    JFrame frame=new JFrame("Book Record Management");
    JPanel insertPanel,viewPanel;
    JTabbedPane tabbedPane=new JTabbedPane();
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4,t5;
    JButton saveButton,updateButton,deleteButton;
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tm;
    String [] columnNames={"BookID","Title","Price(in Rs.)","Author","Publisher"};

    void getConnection(){
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_first","root","csislove");
            System.out.println("Connected successfully!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void initComponents(){
        //Components for insert form
        l1=new JLabel("Book Id:");
        l2=new JLabel("Title:");
        l3=new JLabel("Price:");
        l4=new JLabel("Author:");
        l5=new JLabel("Publisher:");

        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        t5=new JTextField();

        saveButton=new JButton("Save");

        //Setting bounds
        l1.setBounds(100,100,100,20);
        l2.setBounds(100,150,100,20);
        l3.setBounds(100,200,100,20);
        l4.setBounds(100,250,100,20);
        l5.setBounds(100,300,100,20);
        t1.setBounds(250,100,100,20);
        t2.setBounds(250,150,100,20);
        t3.setBounds(250,200,100,20);
        t4.setBounds(250,250,100,20);
        t5.setBounds(250,300,100,20);
        saveButton.setBounds(175,350,100,30);

        //Components Added on Panel
        insertPanel=new JPanel();
        insertPanel.setLayout(null);
        insertPanel.add(l1);
        insertPanel.add(l2);
        insertPanel.add(l3);
        insertPanel.add(l4);
        insertPanel.add(l5);
        insertPanel.add(t1);
        insertPanel.add(t2);
        insertPanel.add(t3);
        insertPanel.add(t4);
        insertPanel.add(t5);
        insertPanel.add(saveButton);

        tabbedPane.add(insertPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);





    }
    public BookFrame(){
        getConnection();
        initComponents();
    }
}
