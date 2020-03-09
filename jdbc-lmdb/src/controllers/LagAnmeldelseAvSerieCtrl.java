package controllers;


import java.sql.*;

import JDBC.AnmeldelseAvSerie;
import JDBC.Bruker;
import JDBC.DBConn;
import JDBC.Serie;

public class LagAnmeldelseAvSerieCtrl extends DBConn {
	private AnmeldelseAvSerie anmeldelseAvSerie;

    public LagAnmeldelseAvSerieCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagAnmeldelseAvSerieCtrl="+e);
            return;
        }
    }
    
    public AnmeldelseAvSerie initAnmeldelseAvSerie(Bruker bruker, Serie serie){
        this.anmeldelseAvSerie = new AnmeldelseAvSerie(bruker, serie);
        this.anmeldelseAvSerie.initialize(conn);
        return this.anmeldelseAvSerie;
    }

    public AnmeldelseAvSerie lagAnmeldelseAvSerie(Bruker bruker, Serie serie, String tekst, int rating){
        this.anmeldelseAvSerie = new AnmeldelseAvSerie(bruker, serie, tekst, rating);
        return this.anmeldelseAvSerie;
    }
    
    public void fullforAnmeldelseAvSerie() {
        this.anmeldelseAvSerie.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagAnmeldelseAvSerieCtrl="+e);
            return;
        }
    }
}











