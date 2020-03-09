package controllers;


import java.sql.*;

import JDBC.RegissertAv;
import JDBC.Artist;
import JDBC.DBConn;
import JDBC.Produksjon;

public class LagRegissertAvCtrl extends DBConn {
	
	private RegissertAv ressigertAv;

    public LagRegissertAvCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagRessigertAvCtrl="+e);
            return;
        }
    }
    
    public RegissertAv initRessigertAv(Artist artist, Produksjon produksjon){
        this.ressigertAv = new RegissertAv(artist, produksjon);
        this.ressigertAv.initialize(conn);
        return this.ressigertAv;
    }
    
    public RegissertAv lagRessigertAv(Artist artist, Produksjon produksjon){
        this.ressigertAv = new RegissertAv(artist, produksjon);
        return this.ressigertAv;
    }

    public void fullforRessigertAv() {
        this.ressigertAv.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagRessigertAvCtrl="+e);
            return;
        }
    }
}











