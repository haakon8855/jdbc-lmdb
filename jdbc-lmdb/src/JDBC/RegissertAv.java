package JDBC;

import java.sql.*;

public class RegissertAv extends ActiveDomainObject {
	
	private Artist artist;
	private Produksjon produksjon;
	
	public RegissertAv(Artist artist, Produksjon produksjon) {
		this.artist = artist;
		this.produksjon = produksjon;
	}
	
	@Override
	public void initialize(Connection conn) {
		return;
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into regissertAv values ('"+artist.getPK()+"', '"+produksjon.getPK()+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of regissertAv: " + e);
		}
	}
	
	@Override
	public String toString() {
		return this.artist + ", " + this.produksjon;
	}

}










