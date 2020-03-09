package controllers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JDBC.Musikk;
import JDBC.Artist;
import JDBC.DBConn;

public class LagMusikkCtrl extends DBConn {
	
    private Musikk musikk;

    public LagMusikkCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagMusikkCtrl="+e);
            return;
        }
    }
    
    public Musikk initMusikk(String tittel) {
    	this.musikk = new Musikk(tittel);
    	this.musikk.initialize(conn);
    	return this.musikk;
    }

    public Musikk lagMusikk(String tittel, int utgivelsesaar, Artist artist){
        this.musikk = new Musikk(tittel, utgivelsesaar, artist);
        return this.musikk;
    }
    
    public void fullforMusikk() {
        this.musikk.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagMusikkCtrl="+e);
            return;
        }
    }
    
    public List<Musikk> hentAlleMusikk() {
    	List<Musikk> MusikkListe = new ArrayList<>();
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from musikk");
			while (rs.next()) {
				MusikkListe.add(this.initMusikk(rs.getString("tittel")));
			}
		} catch (Exception e) {
			System.out.println("db error during select of musikk= "+e);
		}
    	return MusikkListe;
    }
    
    public void hentAlleMusikkString() {
    	List<Musikk> MusikkListe = this.hentAlleMusikk();
    	for (int i=0; i < MusikkListe.size(); i++) {
    		System.out.println(i+1 + ": " + MusikkListe.get(i));
    	}
    }
}











