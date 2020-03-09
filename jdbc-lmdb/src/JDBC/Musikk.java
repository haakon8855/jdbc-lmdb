package JDBC;

import java.sql.*;

public class Musikk extends ActiveDomainObject {
	
	private String tittel;
	private int utgivelsesaar;
	private Artist artist;
	
	public Musikk(String tittel) {
		this.tittel = tittel;
	}
	
	public Musikk(String tittel, int utgivelsesaar, Artist artist) {
		this.tittel = tittel;
		this.utgivelsesaar = utgivelsesaar;
		this.artist = artist;
	}
	
	public String getPK() {
		return this.tittel;
	}

	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from musikk where tittel='" + tittel + "'");
			while (rs.next()) {
				this.utgivelsesaar = rs.getInt("utgivelsesaar");
				this.artist = new Artist(rs.getString("artistNavn"));
				this.artist.initialize(conn);
			}
		} catch (Exception e) {
			System.out.println("db error during select of musikk= "+e);
		}
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into musikk values ('"+tittel+"',"+utgivelsesaar+",'"+artist.getPK()+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of musikk" + e);
			return;
		}
	}

	@Override
	public String toString() {
		return this.tittel + ", " + this.utgivelsesaar + ", " + this.artist;
	}

}












