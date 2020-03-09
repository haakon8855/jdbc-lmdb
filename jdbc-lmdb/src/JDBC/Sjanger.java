package JDBC;

import java.sql.*;

public class Sjanger extends ActiveDomainObject{
	private String sjanger_navn;
	
	public Sjanger(String navn) {
		this.sjanger_navn = navn;
	}
	
	@Override
	public void initialize(Connection conn) {
		return;
	}

	public String getPK() {
		return sjanger_navn;
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into sjanger values ('"+ sjanger_navn + "')");
			
		} catch (Exception e) {
			System.out.println("DB error during insert of sjanger " + e);
		}
		
	}

	@Override
	public String toString() {
		return this.sjanger_navn;
	}

}
