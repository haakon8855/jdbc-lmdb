package JDBC;

import java.sql.*;
	
public class EpisodeISesong extends ActiveDomainObject{
	private Produksjon produksjon;
	private Sesong sesong;
	private int episodeNR;
	
	public EpisodeISesong(Produksjon produksjon, Sesong sesong) {
		this.produksjon = produksjon;
		this.sesong = sesong;
		}
	
	public EpisodeISesong(Produksjon produksjon, Sesong sesong, int episodeNR) {
		this.produksjon = produksjon;
		this.sesong = sesong;
		this.episodeNR = episodeNR;
		
	}

	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from episodeISesong "
				+ "where produksjonsTittel = '" + this.produksjon.getPK()
				+ "' and serieTittel = '" + this.sesong.getSeriePK()
				+ "' and sesongNR = " + this.sesong.getSesongNR());
			while (rs.next()) {
				this.episodeNR = rs.getInt("episodeNR");
			}
		} catch (Exception e) {
			System.out.println("db error during select of EpisodeISesong: " + e);
		}
	}


	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into episodeISesong values ('"+produksjon.getPK()+"','"+sesong.getSeriePK()+"',"+sesong.getSesongNR()+","+episodeNR+")");
		} catch (Exception e) {
			System.out.println("db error during insert of EpisodeISesong" + e);
		}
	}
	

	@Override
	public String toString() {
		return this.produksjon.getPK() + ", " + this.sesong + ", " + this.episodeNR;
	}
	
}
