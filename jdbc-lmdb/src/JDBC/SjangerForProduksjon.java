package JDBC;

import java.sql.*;

public class SjangerForProduksjon extends ActiveDomainObject {
	
	private Sjanger sjanger;
	private Produksjon produksjon;
	
	public SjangerForProduksjon(Sjanger sjanger, Produksjon produksjon) {
		this.sjanger = sjanger;
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
			stmt.executeUpdate("insert into sjangerForProduksjon values ('"+sjanger.getPK()+"','"+produksjon.getPK()+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of sjangerForProduksjon" + e);
		}		
	}
	
	@Override
	public String toString() {
		return this.sjanger + ", " + this.produksjon;
	}
	

}
