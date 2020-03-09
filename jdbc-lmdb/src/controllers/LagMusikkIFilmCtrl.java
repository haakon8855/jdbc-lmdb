package controllers;


import java.sql.*;


import JDBC.DBConn;
import JDBC.Musikk;
import JDBC.MusikkIFilm;
import JDBC.Produksjon;

public class LagMusikkIFilmCtrl extends DBConn {
	
	private MusikkIFilm musikkIFilm;

    public LagMusikkIFilmCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagMusikkIFilmCtrl="+e);
            return;
        }
    }
    
    public MusikkIFilm initMusikkIFilm(Musikk musikk, Produksjon produksjon){
        this.musikkIFilm = new MusikkIFilm(musikk, produksjon);
        this.musikkIFilm.initialize(conn);
        return this.musikkIFilm;
    }
    
    public MusikkIFilm lagMusikkIFilm(Musikk musikk, Produksjon produksjon){
        this.musikkIFilm = new MusikkIFilm(musikk, produksjon);
        return this.musikkIFilm;
    }

    public void fullforMusikkIFilm() {
        this.musikkIFilm.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagMusikkIFilmCtrl="+e);
            return;
        }
    }
}











