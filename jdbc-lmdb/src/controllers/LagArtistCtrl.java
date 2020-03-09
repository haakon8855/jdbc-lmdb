package controllers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JDBC.Artist;
import JDBC.DBConn;

public class LagArtistCtrl extends DBConn {
	private Artist artist;

    public LagArtistCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagArtistCtrl="+e);
            return;
        }
    }
    
    public Artist initArtist(String navn){
        this.artist = new Artist(navn);
        this.artist.initialize(conn);
        return this.artist;
    }

    public Artist lagArtist(String navn, int fodselsaar, String nasjonalitet){
        this.artist = new Artist(navn, fodselsaar, nasjonalitet);
        return this.artist;
    }
    
    public void fullforArtist() {
        this.artist.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagArtistCtrl="+e);
            return;
        }
    }
    
    public List<Artist> hentAlleArtist() {
    	List<Artist> ArtistListe = new ArrayList<>();
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from artist");
			while (rs.next()) {
				ArtistListe.add(this.initArtist(rs.getString("navn")));
			}
		} catch (Exception e) {
			System.out.println("db error during select of artist= "+e);
		}
    	return ArtistListe;
    }
    
    public void hentAlleArtistString() {
    	List<Artist> ArtistListe = this.hentAlleArtist();
    	for (int i=0; i < ArtistListe.size(); i++) {
    		System.out.println(i+1 + ": " + ArtistListe.get(i));
    	}
    }
    
}











