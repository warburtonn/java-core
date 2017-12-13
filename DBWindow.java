package com.diplom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.sql.*;

import net.proteanit.sql.DbUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by Сергей on 08.12.2017.
 */

public class DBWindow {

    static final String NAME = " ";
    static final String PASS = " ";
    static final String URL = " ";
    static final String DRIVER = " ";

    Connection conn = null;
    Statement stm = null;
    private JPanel DBMain;
    private JButton LoadTabble;
    private JTable table1;
    private JButton button1;
    private JButton buttonGidrology;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DBWindow frame = new DBWindow();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(URL,NAME,PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM "; //select coloumn your table FROM .tablename
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        //Creating Main Window
        JFrame panel = new JFrame("Метеорологические данные: "); //Name of JFrame
        panel.setSize(1400, 400); //resolution Main Window
        panel.setContentPane(new DBWindow().DBMain);
        panel.setLocationRelativeTo(null); //Window position in center
        panel.setVisible(true); //Is this window visible ? (true - 'yes' / false - 'no')
        panel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Close window and close program when user press 'x'
    }//end main

    public DBWindow() {

        final Connection connection = null;
        LoadTabble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection connection = DriverManager.getConnection(URL, NAME, PASS);
                    String query = "SELECT * FROM /*enter your tablename*/";
                    PreparedStatement pst =connection.prepareStatement(query);
                    ResultSet res= pst.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(res));

                }catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        });
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("root-name", "root-password", "url");
                    Statement stmt = con.createStatement();
                    ResultSet resultSet = stmt.executeQuery("select * from ");
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet spreadsheet = workbook.createSheet("Export data");
                    XSSFRow row = spreadsheet.createRow(1);
                    XSSFCell cell;
                    //Binding a column and name to an Excel file
                    cell = row.createCell(0);
                    cell.setCellValue("№");

                    cell = row.createCell(1);
                    cell.setCellValue("Дата");

                    cell = row.createCell(2);
                    cell.setCellValue("Воздух максимальное");

                    cell = row.createCell(3);
                    cell.setCellValue("Воздух минимальное");

                    cell = row.createCell(4);
                    cell.setCellValue("Воздух среднее");

                    cell = row.createCell(5);
                    cell.setCellValue("Температура на поверх (макс)");

                    cell = row.createCell(6);
                    cell.setCellValue("Температура на поверх (мин)");

                    cell = row.createCell(7);
                    cell.setCellValue("Почва 5см");

                    cell = row.createCell(8);
                    cell.setCellValue("Почва 10см");

                    cell = row.createCell(9);
                    cell.setCellValue("Почва 20см");

                    cell = row.createCell(10);
                    cell.setCellValue("Влажность воздуха мин");

                    cell = row.createCell(11);
                    cell.setCellValue("Влажность воздуха макс");

                    cell = row.createCell(12);
                    cell.setCellValue("Дифицит влажности среднее");


                    cell = row.createCell(13);
                    cell.setCellValue("Атмосферные явления");

                    cell = row.createCell(14);
                    cell.setCellValue("Скорость ветра макс");

                    cell = row.createCell(15);
                    cell.setCellValue("Облачность общее");

                    cell = row.createCell(16);
                    cell.setCellValue("Облачность нижнее");

                    cell = row.createCell(17);
                    cell.setCellValue("Осадки ночь");

                    cell = row.createCell(18);
                    cell.setCellValue("Осадки день");

                    cell = row.createCell(19);
                    cell.setCellValue("Осадки сутки");

                    int i = 3; //1-3 indent three cells from the beginning
                    while (resultSet.next()) {
                        row = spreadsheet.createRow(i);

                        cell = row.createCell(0);
                        cell.setCellValue(resultSet.getInt("id"));

                        cell = row.createCell(1);
                        cell.setCellValue(resultSet.getString("date"));

                        cell = row.createCell(2);
                        cell.setCellValue(resultSet.getFloat("vozdux_max"));

                        cell = row.createCell(3);
                        cell.setCellValue(resultSet.getFloat("vozdux_min"));

                        cell = row.createCell(4);
                        cell.setCellValue(resultSet.getFloat("vozdux_avarage"));

                        cell = row.createCell(5);
                        cell.setCellValue(resultSet.getFloat("surface_max"));

                        cell = row.createCell(6);
                        cell.setCellValue(resultSet.getFloat("surface_min"));

                        cell = row.createCell(7);
                        cell.setCellValue(resultSet.getFloat("depth_5cm"));

                        cell = row.createCell(8);
                        cell.setCellValue(resultSet.getFloat("depth_10cm"));

                        cell = row.createCell(9);
                        cell.setCellValue(resultSet.getFloat("depth_20cm"));

                        cell = row.createCell(10);
                        cell.setCellValue(resultSet.getFloat("air_rel_min"));

                        cell = row.createCell(11);
                        cell.setCellValue(resultSet.getFloat("air_rel_max"));

                        cell = row.createCell(12);
                        cell.setCellValue(resultSet.getFloat("air_def_avarage"));

                        cell = row.createCell(13);
                        cell.setCellValue(resultSet.getString("atmos_phenome"));

                        cell = row.createCell(14);
                        cell.setCellValue(resultSet.getFloat("wind_speed_max"));

                        cell = row.createCell(15);
                        cell.setCellValue(resultSet.getFloat("nebula_total"));

                        cell = row.createCell(16);
                        cell.setCellValue(resultSet.getFloat("nebula_bot"));

                        cell = row.createCell(17);
                        cell.setCellValue(resultSet.getFloat("opadi_night"));

                        cell = row.createCell(18);
                        cell.setCellValue(resultSet.getFloat("opadi_day"));

                        cell = row.createCell(19);
                        cell.setCellValue(resultSet.getFloat("opadi_24h"));

                        i++;
                    }
                    FileOutputStream out = new FileOutputStream(new File("C://Exel/database_new.xlsx")); //Path to your .xls file
                    workbook.write(out);
                    out.close();
                    System.out.println("File Successfully created"); //Successful export message
                    con.close();
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}//end
