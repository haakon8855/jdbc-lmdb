package controllers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JDBC.Sjanger;
import JDBC.DBConn;

public class LagSjangerCtrl extends DBConn {
	
	private Sjanger sjanger;

    public LagSjangerCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagSjangerCtrl="+e);
            return;
        }
    }
    
    public Sjanger initSjanger(String sjanger_navn){
        this.sjanger = new Sjanger(sjanger_navn);
        this.sjanger.initialize(conn);
        return this.sjanger;
    }
    
    public Sjanger lagSjanger(String sjanger_navn){
        this.sjanger = new Sjanger(sjanger_navn);
        return this.sjanger;
    }

    public void fullforSjanger() {
        this.sjanger.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagSjangerCtrl="+e);
            return;
        }
    }
    
    public List<Sjanger> hentAlleSjanger() {
    	List<Sjanger> SjangerListe = new ArrayList<>();
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sjanger");
			while (rs.next()) {
				SjangerListe.add(this.initSjanger(rs.getString("sjanger_navn")));
			}
		} catch (Exception e) {
			System.out.println("db error during select of sjanger= "+e);
		}
    	return SjangerListe;
    }
    
    public void hentAlleSjangerString() {
    	List<Sjanger> SjangerListe = this.hentAlleSjanger();
    	for (int i=0; i < SjangerListe.size(); i++) {
    		System.out.println(i+1 + ": " + SjangerListe.get(i));
    	}
    }
    
}











