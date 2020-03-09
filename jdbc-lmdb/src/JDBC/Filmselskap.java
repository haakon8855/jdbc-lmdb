package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Filmselskap extends ActiveDomainObject {
	
    private String navn;
    private String url;
    private String adresse;
    private String nasjonalitet;
    
    public Filmselskap(String navn) {
    	this.navn = navn;
    }
	
	public Filmselskap(String navn, String url, String adresse, String nasjonalitet) {
		this.navn = navn;
		this.url = url;
		this.adresse = adresse;
		this.nasjonalitet = nasjonalitet;
	}
	
	public String getPK() {
		return navn;
	}

	@Override
	public void initialize(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from filmselskap where navn='" + navn + "'");
			while (rs.next()) {
				this.url = rs.getString("url");
				this.adresse = rs.getString("adresse");
				this.nasjonalitet = rs.getString("nasjonalitet");
			}
		} catch (Exception e) {
			System.out.println("db error during select of filmselskap= "+e);
		}
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into filmselskap values ('"+navn+"','"+url+"','"+adresse+"','"+nasjonalitet+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of filmselskap" + e);
		}
	}
	
	@Override
	public String toString() {
		return navn + ", " + url + ", " + adresse + ", " + nasjonalitet;
	}
	
}












