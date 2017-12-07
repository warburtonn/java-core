package com.diplom;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/dbtest";

    public static void main (String[] args)
    {

        Connection connection;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest", "root", "root");
            System.out.println("Соединение успешно");
            System.out.println("Закрываю соединение");
            connection.close();
        } catch (SQLException e) {
            System.out.print("Не удалось подключиться");
        }


        /*Connection connection;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
            if(!connection.isClosed())
            {
                System.out.println("Connection to BD..OK");
            }
            connection.close();
            if (connection.isClosed())
            {
                System.out.println("Connection is close");
            }
        } catch (SQLException e) {
            System.err.print("не удалось подключить драйвер");
        }*/
    }
}
