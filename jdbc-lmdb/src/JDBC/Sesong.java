package JDBC;

import java.sql.Connection;
import java.sql.Statement;

public class Sesong extends ActiveDomainObject {
	private int sesongNR;
	private Serie serie;
	
	public Sesong(int sesongNR, Serie serie) {
		this.sesongNR = sesongNR;
		this.serie = serie;
	}
	
	public int getSesongNR() {
		return this.sesongNR;
	}
	
	public String getSeriePK() {
		return this.serie.getPK();
	}

	@Override
	public void initialize(Connection conn) {
		return;
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into sesong values ("+ this.sesongNR + ",'"+ this.serie.getPK() + "')");
			
		} catch (Exception e) {
			System.out.println("DB error during insert of sesong " + e);
		}
	}
	

	@Override
	public String toString() {
		return this.getSesongNR() + ", " + this.getSeriePK();
	}
	
}
