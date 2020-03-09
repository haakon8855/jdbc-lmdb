package controllers;


import java.sql.*;

import JDBC.Sesong;
import JDBC.DBConn;
import JDBC.Serie;

public class LagSesongCtrl extends DBConn {
	private Sesong sesong;

    public LagSesongCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagSesongCtrl="+e);
            return;
        }
    }
    
    public Sesong initSesong(int sesongNR, Serie serie){
        this.sesong = new Sesong(sesongNR, serie);
        this.sesong.initialize(conn);
        return this.sesong;
    }
    
    public Sesong lagSesong(int sesongNR, Serie serie){
        this.sesong = new Sesong(sesongNR, serie);
        return this.sesong;
    }

    public void fullforSesong() {
        this.sesong.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagSesongCtrl="+e);
            return;
        }
    }
}











