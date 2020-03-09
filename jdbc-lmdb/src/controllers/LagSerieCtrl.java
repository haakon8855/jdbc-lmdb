package controllers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JDBC.Serie;
import JDBC.DBConn;

public class LagSerieCtrl extends DBConn {
	private Serie serie;

    public LagSerieCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagSerieCtrl="+e);
            return;
        }
    }
    
    public Serie initSerie(String tittel){
        this.serie = new Serie(tittel);
        this.serie.initialize(conn);
        return this.serie;
    }

    public Serie lagSerie(String tittel){
        this.serie = new Serie(tittel);
        return this.serie;
    }
    
    public void fullforSerie() {
        this.serie.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagSerieCtrl="+e);
            return;
        }
    }
    
    public List<Serie> hentAlleSerie() {
    	List<Serie> SerieListe = new ArrayList<>();
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from serie");
			while (rs.next()) {
				SerieListe.add(this.initSerie(rs.getString("tittel")));
			}
		} catch (Exception e) {
			System.out.println("db error during select of Serie= "+e);
		}
    	return SerieListe;
    }
    
    public void hentAlleSerieString() {
    	List<Serie> SerieListe = this.hentAlleSerie();
    	for (int i=0; i < SerieListe.size(); i++) {
    		System.out.println(i+1 + ": " + SerieListe.get(i));
    	}
    }
    
}











