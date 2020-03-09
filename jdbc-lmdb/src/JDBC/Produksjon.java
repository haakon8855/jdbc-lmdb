package JDBC;

import java.sql.*;

public class Produksjon extends ActiveDomainObject {
	
	private String tittel;
	private int lengde;
	private int utgivelsesaar;
	private String lanseringsdato;
	private String sammendrag;
	private Filmselskap filmselskap;

	public Produksjon(String tittel) {
		this.tittel = tittel;
	}
	
	public Produksjon(String tittel, int lengde, int utgivelsesaar, String lanseringsdato, String sammendrag, Filmselskap filmselskap) {
		this.tittel = tittel;
		this.lengde = lengde;
		this.utgivelsesaar = utgivelsesaar;
		this.lanseringsdato = lanseringsdato;
		this.sammendrag = sammendrag;
		this.filmselskap = filmselskap;
	}
	
	public String getPK() {
		return this.tittel;
	}

	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from produksjon where tittel='" + tittel + "'");
			while (rs.next()) {
				this.lengde = rs.getInt("lengde");
				this.utgivelsesaar = rs.getInt("utgivelsesaar");
				this.lanseringsdato = rs.getString("lanseringsdato");
				this.sammendrag = rs.getString("sammendrag");
				this.filmselskap = new Filmselskap(rs.getString("selskapsNavn"));
				this.filmselskap.initialize(conn);
			}
		} catch (Exception e) {
			System.out.println("db error during select of produksjon= "+e);
		}
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into produksjon values ('"+tittel+"',"+lengde+","+utgivelsesaar+",'"+lanseringsdato+"','"+sammendrag+"','"+filmselskap.getPK()+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of produksjon" + e);
		}
	}
	
	@Override
	public String toString() {
		return tittel + ", " + lengde + ", " + utgivelsesaar + ", " + lanseringsdato + ", " + sammendrag + ", " + filmselskap;
	}
	
}














