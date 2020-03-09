package controllers;


import java.sql.*;

import JDBC.Artist;
import JDBC.DBConn;
import JDBC.Produksjon;
import JDBC.SkrevetAv;

public class LagSkrevetAvCtrl extends DBConn {
	
	private SkrevetAv skrevetAv;

    public LagSkrevetAvCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagSkuespillerRolleCtrl="+e);
            return;
        }
    }
    
    public SkrevetAv initSkrevetAv(Artist artist, Produksjon produksjon){
        this.skrevetAv = new SkrevetAv(artist, produksjon);
        this.skrevetAv.initialize(conn);
        return this.skrevetAv;
    }
    
    public SkrevetAv lagSkrevetAv(Artist artist, Produksjon produksjon){
        this.skrevetAv = new SkrevetAv(artist, produksjon);
        return this.skrevetAv;
    }

    public void fullforSkrevetAv() {
        this.skrevetAv.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagSkuespillerRolleCtrl="+e);
            return;
        }
    }
}











