package controllers;


import java.sql.*;

import JDBC.EpisodeISesong;
import JDBC.Produksjon;
import JDBC.DBConn;
import JDBC.Sesong;

public class LagEpisodeISesongCtrl extends DBConn {
	
	private EpisodeISesong episodeISesong;

    public LagEpisodeISesongCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagEpisodeISesongCtrl="+e);
            return;
        }
    }
    
    public EpisodeISesong initEpisodeISesong(Produksjon produksjon, Sesong sesong){
        this.episodeISesong = new EpisodeISesong(produksjon, sesong);
        this.episodeISesong.initialize(conn);
        return this.episodeISesong;
    }

    public EpisodeISesong lagEpisodeISesong(Produksjon produksjon, Sesong sesong, int episodeNR){
        this.episodeISesong = new EpisodeISesong(produksjon, sesong, episodeNR);
        return this.episodeISesong;
    }
    
    public void fullforEpisodeISesong() {
        this.episodeISesong.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagEpisodeISesongCtrl="+e);
            return;
        }
    }
}











