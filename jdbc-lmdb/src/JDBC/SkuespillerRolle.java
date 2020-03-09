package JDBC;

import java.sql.*;

public class SkuespillerRolle extends ActiveDomainObject {
	
	private Artist artist;
	private Produksjon produksjon;
	private String rolle;
	
	public SkuespillerRolle(Artist artist, Produksjon produksjon, String rolle) {
		this.artist = artist;
		this.produksjon = produksjon;
		this.rolle = rolle;
	}
	

	@Override
	public void initialize(Connection conn) {
		return;
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into skuespillerRolle values ('"+artist.getPK()+"', '"+produksjon.getPK()+"','"+rolle+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of skuespillerRolle" + e);
		}
	}
	
	@Override
	public String toString() {
		return this.artist + ", " + this.produksjon + ", " + this.rolle;
	}

}










