package controllers;


import java.sql.*;

import JDBC.SkuespillerRolle;
import JDBC.Artist;
import JDBC.DBConn;
import JDBC.Produksjon;

public class LagSkuespillerRolleCtrl extends DBConn {
	
	private SkuespillerRolle skuespillerRolle;

    public LagSkuespillerRolleCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagSkuespillerRolleCtrl="+e);
            return;
        }
    }
    
    public SkuespillerRolle initSkuespillerRolle(Artist artist, Produksjon produksjon, String rolle){
        this.skuespillerRolle = new SkuespillerRolle(artist, produksjon, rolle);
        this.skuespillerRolle.initialize(conn);
        return this.skuespillerRolle;
    }
    
    public void getSkuespillerRolleFilm(String artistNavn){
		try {
			int i = 0;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * \n" + 
					"from skuespillerRolle as SR\n" + 
					"where artistNavn='" + artistNavn + "' and SR.produksjonsTittel not in (\n" + 
					"	select SR.produksjonsTittel\n" + 
					"    from episodeISesong as E\n" + 
					"    inner join produksjon as P on (P.tittel = E.produksjonsTittel)\n" + 
					"    inner join skuespillerRolle as SR on (SR.produksjonsTittel = P.tittel))");
			while (rs.next()) {
				i++;
				System.out.println(i + ": " + rs.getString("produksjonsTittel") + ", Rolle - " + rs.getString("rolle"));
			}
		} catch (Exception e) {
			System.out.println("db error during select of SkuespillerRolle: "+e);
		}
    }
    
    public void getSkuespillerRolle(String artistNavn){
		try {
			int i = 0;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select P.tittel, SR.artistNavn, SR.Rolle\n" + 
					"from produksjon as P\n" + 
					"inner join skuespillerRolle as SR on (P.tittel = SR.produksjonsTittel)" + " where artistNavn='" + artistNavn + "'");
			while (rs.next()) {
				i++;
				System.out.println(i + ": " + rs.getString("tittel") + ", Rolle - " + rs.getString("rolle"));
			}
		} catch (Exception e) {
			System.out.println("db error during select of SkuespillerRolle: "+e);
		}
    }
    
    public SkuespillerRolle lagSkuespillerRolle(Artist artist, Produksjon produksjon, String rolle){
        this.skuespillerRolle = new SkuespillerRolle(artist, produksjon, rolle);
        return this.skuespillerRolle;
    }

    public void fullforSkuespillerRolle() {
        this.skuespillerRolle.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagSkuespillerRolleCtrl="+e);
            return;
        }
    }
}











