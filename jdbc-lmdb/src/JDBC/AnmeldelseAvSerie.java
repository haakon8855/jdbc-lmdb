package JDBC;

import java.sql.*;

public class AnmeldelseAvSerie extends ActiveDomainObject{
	private Bruker bruker;
	private Serie serie;
	private String tekst;
	private int rating;
	
	public AnmeldelseAvSerie(Bruker bruker, Serie serie) {
		this.bruker = bruker;
		this.serie = serie;
	}
	
	public AnmeldelseAvSerie(Bruker bruker, Serie serie, String tekst, int rating) {
		this.bruker = bruker;
		this.serie = serie;
		this.tekst = tekst;
		this.rating = rating;
	}
	
	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from anmeldesAvSerie where brukernavn = '" + bruker.getPK() + "' and serietittel = '" + serie.getPK() + "'");
			while (rs.next()) {
				this.tekst = rs.getString("tekst");
				this.rating= rs.getInt("rating");
			}
		} catch (Exception e) {
			System.out.println("db error during select of AnmeldesleAvSerie: "+e);
		}
	}



	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into anmeldelseAvSerie values ('"+bruker.getPK()+"','"+serie.getPK()+"','"+tekst+"',"+rating+")");
		} catch (Exception e) {
			System.out.println("db error during insert of AnmeldelseAvSerie" + e);
		}
	}
	
	@Override
	public String toString() {
		return this.bruker + ", " + this.serie + ", " + this.tekst + ", " + this.rating;
	}
}












