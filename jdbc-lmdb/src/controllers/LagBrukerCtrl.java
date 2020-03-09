package controllers;


import java.sql.*;

import JDBC.Bruker;
import JDBC.DBConn;

public class LagBrukerCtrl extends DBConn {
	private Bruker bruker;

    public LagBrukerCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagBrukerCtrl="+e);
            return;
        }
    }
    
    public Bruker initBruker(String brukernavn){
        this.bruker = new Bruker(brukernavn);
        this.bruker.initialize(conn);
        return this.bruker;
    }

    public Bruker lagBruker(String brukernavn, String epostadresse, String passord, String navn, String fodselsdato){
        this.bruker = new Bruker(brukernavn, epostadresse, passord, navn, fodselsdato);
        return this.bruker;
    }
    
    public void fullforBruker() {
        this.bruker.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagBrukerCtrl="+e);
            return;
        }
    }
}











