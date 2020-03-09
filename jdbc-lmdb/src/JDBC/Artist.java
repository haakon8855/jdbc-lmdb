package JDBC;

import java.sql.*;

public class Artist extends ActiveDomainObject {
	
	private String navn;
	private int fodselsaar;
	private String nasjonalitet;
	
	public Artist(String navn) {
		this.navn = navn;
	}
	
	public Artist(String navn, int fodselsaar, String nasjonalitet) {
		this.navn = navn;
		this.fodselsaar = fodselsaar;
		this.nasjonalitet = nasjonalitet;
	}
	
	public String getPK() {
		return this.navn; 
	}

	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from artist where navn='" + navn + "'");
			while (rs.next()) {
				this.fodselsaar = rs.getInt("fodselsaar");
				this.nasjonalitet= rs.getString("nasjonalitet");
			}
		} catch (Exception e) {
			System.out.println("db error during select of artist= "+e);
		}
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into artist values ('"+navn+"',"+fodselsaar+",'"+nasjonalitet+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of artist" + e);
		}
	}
	
	@Override
	public String toString() {
		return this.navn + ", " + this.fodselsaar + ", " + this.nasjonalitet;
	}

}










