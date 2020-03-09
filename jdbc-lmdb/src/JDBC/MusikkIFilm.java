package JDBC;

import java.sql.Connection;
import java.sql.Statement;

public class MusikkIFilm extends ActiveDomainObject {
	
	private Produksjon produksjon;
	private Musikk musikk;
	
	public MusikkIFilm(Musikk musikk, Produksjon produksjon) {
		// TODO Auto-generated constructor stub
		this.produksjon = produksjon;
		this.musikk = musikk;
	}

	@Override
	public void initialize(Connection conn) {
		return;
		
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into musikkIFilm values ('"+this.musikk.getPK()+"','"+this.produksjon.getPK()+"')");
		} catch (Exception e) {
			System.out.println("error during insert of musikkIFilm: " + e);
		}
		
	}

}
