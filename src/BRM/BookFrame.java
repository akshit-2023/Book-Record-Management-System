package BRM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        //Save Button Event Handling
        saveButton.addActionListener(new InsertBookRecord());

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

        ArrayList<Book> bookList=fetchBookRecords();
        setDataOnTable(bookList);
        updateButton=new JButton("Update Book");
        updateButton.addActionListener(new UpdateBookRecord());
        deleteButton=new JButton("Delete Book");
        deleteButton.addActionListener(new DeleteBookRecord());

        viewPanel=new JPanel();
        viewPanel.add(updateButton);
        viewPanel.add(deleteButton);
        scrollPane=new JScrollPane(table);//table added on scroll pane
        viewPanel.add(scrollPane);

        tabbedPane.add(insertPanel);
        tabbedPane.add(viewPanel);
        //tab event handling
        tabbedPane.addChangeListener(new TabChangeHandler());


        frame.add(tabbedPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);





    }

    ArrayList<Book> fetchBookRecords(){
        ArrayList<Book> bookList=new ArrayList<Book>();
        String q="Select * from book";
        try{
            ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Book b=new Book();
                b.setBookId(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setPrice(rs.getDouble(3));
                b.setAuthor(rs.getString(4));
                b.setPublisher(rs.getString(5));
                bookList.add(b);
            }
        }
        catch (SQLException e){
            System.out.println("Exception: "+e.getMessage());
        }
        return bookList;
    }
    void setDataOnTable(ArrayList<Book> bookList){
        Object [][] obj=new Object[bookList.size()][5];
        for(int i=0;i<bookList.size();i++){
            obj[i][0]=bookList.get(i).getBookId();
            obj[i][1]=bookList.get(i).getTitle();
            obj[i][2]=bookList.get(i).getPrice();
            obj[i][3]=bookList.get(i).getAuthor();
            obj[i][4]=bookList.get(i).getPublisher();
        }
        table=new JTable();
        tm=new DefaultTableModel();
        tm.setColumnCount(5);
        tm.setRowCount(bookList.size());
        tm.setColumnIdentifiers(columnNames);
        for(int i=0;i<bookList.size();i++){
            tm.setValueAt(obj[i][0],i,0);
            tm.setValueAt(obj[i][1],i,1);
            tm.setValueAt(obj[i][2],i,2);
            tm.setValueAt(obj[i][3],i,3);
            tm.setValueAt(obj[i][4],i,4);
        }
        table.setModel(tm);
    }
    void updateTable(ArrayList<Book> bookList){
        Object [][] obj=new Object[bookList.size()][5];
        for(int i=0;i<bookList.size();i++){
            obj[i][0]=bookList.get(i).getBookId();
            obj[i][1]=bookList.get(i).getTitle();
            obj[i][2]=bookList.get(i).getPrice();
            obj[i][3]=bookList.get(i).getAuthor();
            obj[i][4]=bookList.get(i).getPublisher();
        }
        tm.setRowCount(bookList.size());
        for(int i=0;i<bookList.size();i++){
            tm.setValueAt(obj[i][0],i,0);
            tm.setValueAt(obj[i][1],i,1);
            tm.setValueAt(obj[i][2],i,2);
            tm.setValueAt(obj[i][3],i,3);
            tm.setValueAt(obj[i][4],i,4);
        }
        table.setModel(tm);
    }
    public BookFrame(){
        getConnection();
        initComponents();
    }
    class InsertBookRecord implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Book b1=readFormData();
            String q="insert into book(bookid,title,price,author,publisher) values(?,?,?,?,?)";
            try{
                ps=con.prepareStatement(q);
                ps.setInt(1,b1.getBookId());
                ps.setString(2,b1.getTitle());
                ps.setDouble(3,b1.getPrice());
                ps.setString(4,b1.getAuthor());
                ps.setString(5,b1.getPublisher());
                ps.execute();

                //Setting text fields to empty when saved so user knows that record has been inserted
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
            }
            catch (SQLException excep){
                System.out.println("Exception: "+ excep.getMessage());
            }
        }
        Book readFormData(){
            Book b1=new Book();
            b1.setBookId(Integer.parseInt(t1.getText()));
            b1.setTitle(t2.getText());
            b1.setPrice(Double.parseDouble(t3.getText()));
            b1.setAuthor(t4.getText());
            b1.setPublisher(t5.getText());
            return b1;
        }
    }

    class TabChangeHandler implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            int index=tabbedPane.getSelectedIndex();//index of tab means 0 is first tab then 1 for second and so on.
            if(index==0){
                System.out.println("Insert Form");
            }
            else if(index==1){
                ArrayList<Book> bookList=fetchBookRecords();
                updateTable(bookList);
            }

        }
    }

    class UpdateBookRecord implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Book>updatedBookList=readTableData();
            String q="update book set title=?,price=?,author=?,publisher=? where bookid=?";
            try{
                ps=con.prepareStatement(q);
                for(int i=0;i<updatedBookList.size();i++){
                    ps.setString(1,updatedBookList.get(i).getTitle());
                    ps.setDouble(2,updatedBookList.get(i).getPrice());
                    ps.setString(3,updatedBookList.get(i).getAuthor());
                    ps.setString(4,updatedBookList.get(i).getPublisher());
                    ps.setInt(5,updatedBookList.get(i).getBookId());
                    ps.executeUpdate();
                }
            }
            catch (SQLException ex){
                System.out.println("Exception: "+ex.getMessage());
            }
        }
        ArrayList<Book> readTableData(){
            ArrayList<Book> updatedBookList=new ArrayList<Book>();
            for(int i=0;i<table.getRowCount();i++){
                Book b=new Book();
                b.setBookId(Integer.parseInt(table.getValueAt(i,0).toString()));
                b.setTitle(table.getValueAt(i,1).toString());
                b.setPrice(Double.parseDouble(table.getValueAt(i,2).toString()));
                b.setAuthor(table.getValueAt(i,3).toString());
                b.setPublisher(table.getValueAt(i,4).toString());
                updatedBookList.add(b);
            }
            return updatedBookList;
        }
    }

    class DeleteBookRecord implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int rowNo=table.getSelectedRow();
            if(rowNo!=-1){
                int id=(int)table.getValueAt(rowNo,0);
                String q="delete from book where bookid=?";
                try{
                    ps=con.prepareStatement(q);
                    ps.setInt(1,id);
                    ps.execute();
                }
                catch (SQLException ex){
                    System.out.println("Exception: "+ex.getMessage());
                }
                finally {
                    ArrayList<Book> bookList=fetchBookRecords();
                    updateTable(bookList);
                }
            }
        }
    }
}