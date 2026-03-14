package com.project.config

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBConn {

    private final String url = "jdbc:postgresql://localhost:5432/linketinder"
    private final String user = "postgres"
    private final String password = System.getenv("DB_PASSWORD")

    Connection connect() {
        try{
            Connection conn = DriverManager.getConnection(url, user, password)
            return conn
        } catch (SQLException e){
            print("Falha ao abrir conexão")
            e.printStackTrace()
            return null
        }
    }

    void closeConn(Connection conn){
        try {
            if (conn != null && !conn.isClosed())
            conn.close()
        } catch (SQLException e){
            println("Falha ao fechar conexão")
            e.printStackTrace()
        }
    }

}
