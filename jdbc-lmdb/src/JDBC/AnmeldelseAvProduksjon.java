package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AnmeldelseAvProduksjon extends ActiveDomainObject {
	
	private Bruker bruker;
	private Produksjon produksjon;
	private String tekst;
	private int rating;
	
	public String[] getPK() {
		String[] out = {bruker.getPK(), produksjon.getPK()};
		return out;
	}
	
	public AnmeldelseAvProduksjon(Bruker bruker, Produksjon produksjon) {
		this.bruker = bruker;
		this.produksjon = produksjon;
	}
	
	public AnmeldelseAvProduksjon(Bruker bruker, Produksjon produksjon, String tekst, int rating) {
		this.bruker = bruker;
		this.produksjon = produksjon;
		this.tekst = tekst;
		this.rating = rating;
	}
	

	@Override
	public void initialize(Connection conn) {
		try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bruker where brukernavn='" + bruker.getPK() + 
            								 "' and produksjonsTittel='" + produksjon.getPK() + "'");
            while (rs.next()) {
                this.tekst =  rs.getString("tekst");
                this.rating = rs.getInt("rating");
            }

        } catch (Exception e) {
            System.out.println("db error during select of anmeldelseAvProduksjon= "+e);
            return;
        }
		
	}

	@Override
	public void save(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("insert into anmeldelseAvProduksjon values ('" + bruker.getPK() + "','" + produksjon.getPK() + 
														  "','" + tekst + "'," + rating + ")");
		} catch (Exception e) {
			System.out.println("db error during insert of anmeldelseAvProduksjon: " + e);
		}
	}
	
	@Override
	public String toString() {
		return this.bruker + ", " + this.produksjon + ", " + tekst + ", " + rating;
	}

}














