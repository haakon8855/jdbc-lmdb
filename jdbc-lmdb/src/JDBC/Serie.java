package JDBC;

import java.sql.Connection;
import java.sql.Statement;

public class Serie extends ActiveDomainObject {
	
	private String tittel;

	public Serie (String tittel) {
		this.tittel = tittel;
	}
	
	public String getPK() {
		return this.tittel;
	}
	
	@Override
	public void initialize(Connection conn) {
		return;
    }

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into serie values ('"+ tittel + "')");
			
		} catch (Exception e) {
			System.out.println("DB error during insert of serie " + e);
		}
	}
	
	@Override
	public String toString() {
		return tittel;
	}

}
