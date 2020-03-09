package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bruker extends ActiveDomainObject {
	
	private String brukernavn;
	private String epostadresse;
	private String passord;
	private String navn;
	private String fodselsdato;
	
	public Bruker(String brukernavn) {
		this.brukernavn = brukernavn;
	}
	
	public Bruker(String brukernavn, String epostadresse, String passord, String navn, String fodselsdato) {
		this.brukernavn = brukernavn;
		this.epostadresse = epostadresse;
		this.passord = passord;
		this.navn = navn;
		this.fodselsdato = fodselsdato;
	}
	
	public String getPK() {
		return this.brukernavn;
	}

	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bruker where brukernavn='" + this.brukernavn + "'");
            while (rs.next()) {
                this.epostadresse =  rs.getString("epostadresse");
                this.passord = rs.getString("passord");
                this.navn = rs.getString("navn");
                this.fodselsdato = rs.getString("fodselsdato");
            }

        } catch (Exception e) {
            System.out.println("db error during select of bruker: " + e);
            return;
        }
		
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into bruker values ('"+brukernavn+"','"+epostadresse+"','"+passord+"','"+navn+"','"+fodselsdato+"')");
		} catch (Exception e) {
			System.out.println("db error during insert of bruker: " + e);
		}
	}

	@Override
	public String toString() {
		return this.brukernavn + ", " + this.epostadresse + ", " + this.navn + ", " + this.fodselsdato + ", " + this.passord;
	}

}
