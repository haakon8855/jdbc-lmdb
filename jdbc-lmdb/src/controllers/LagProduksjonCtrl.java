package controllers;


import java.sql.*;

import JDBC.DBConn;
import JDBC.Filmselskap;
import JDBC.Produksjon;

public class LagProduksjonCtrl extends DBConn {
	private Produksjon produksjon;

    public LagProduksjonCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagProduksjonCtrl="+e);
            return;
        }
    }
    
    public Produksjon initProduksjon(String tittel){
        this.produksjon = new Produksjon(tittel);
        this.produksjon.initialize(conn);
        return this.produksjon;
    }

    public Produksjon lagProduksjon(String tittel, int lengde, int utgivelsesaar, String lanseringsdato, String sammendrag, Filmselskap filmselskap){
        this.produksjon = new Produksjon(tittel, lengde, utgivelsesaar, lanseringsdato, sammendrag, filmselskap);
        return this.produksjon;
    }
    
    public void fullforProduksjon() {
        this.produksjon.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagProduksjonCtrl="+e);
            return;
        }
    }
}











