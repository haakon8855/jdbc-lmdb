package controllers;


import java.sql.*;

import JDBC.AnmeldelseAvProduksjon;
import JDBC.Bruker;
import JDBC.DBConn;
import JDBC.Produksjon;

public class LagAnmeldelseAvProduksjonCtrl extends DBConn {
	private AnmeldelseAvProduksjon anmeldelseAvProduksjon;

    public LagAnmeldelseAvProduksjonCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagAnmeldelseAvProduksjonCtrl="+e);
            return;
        }
    }
    
    public AnmeldelseAvProduksjon initAnmeldelseAvProduksjon(Bruker bruker, Produksjon produksjon){
        this.anmeldelseAvProduksjon = new AnmeldelseAvProduksjon(bruker, produksjon);
        this.anmeldelseAvProduksjon.initialize(conn);
        return this.anmeldelseAvProduksjon;
    }

    public AnmeldelseAvProduksjon lagAnmeldelseAvProduksjon(Bruker bruker, Produksjon produksjon, String tekst, int rating){
        this.anmeldelseAvProduksjon = new AnmeldelseAvProduksjon(bruker, produksjon, tekst, rating);
        return this.anmeldelseAvProduksjon;
    }
    
    public void fullforAnmeldelseAvProduksjon() {
        this.anmeldelseAvProduksjon.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagAnmeldelseAvProduksjonCtrl="+e);
            return;
        }
    }
}











