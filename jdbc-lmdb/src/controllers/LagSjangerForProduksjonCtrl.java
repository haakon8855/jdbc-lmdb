package controllers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JDBC.SjangerForProduksjon;
import JDBC.DBConn;
import JDBC.Produksjon;
import JDBC.Sjanger;

public class LagSjangerForProduksjonCtrl extends DBConn {
	
	private SjangerForProduksjon sjangerForProduksjon;

    public LagSjangerForProduksjonCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagSjangerForProduksjonCtrl="+e);
            return;
        }
    }
    
    public SjangerForProduksjon initSjangerForProduksjon(Sjanger sjanger, Produksjon produksjon){
        this.sjangerForProduksjon = new SjangerForProduksjon(sjanger, produksjon);
        this.sjangerForProduksjon.initialize(conn);
        return this.sjangerForProduksjon;
    }
    
    public SjangerForProduksjon lagSjangerForProduksjon(Sjanger sjanger, Produksjon produksjon){
        this.sjangerForProduksjon = new SjangerForProduksjon(sjanger, produksjon);
        return this.sjangerForProduksjon;
    }

    public void fullforSjangerForProduksjon() {
        this.sjangerForProduksjon.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagSjangerForProduksjonCtrl="+e);
            return;
        }
    }
    
}











