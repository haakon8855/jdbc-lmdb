package controllers;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JDBC.DBConn;
import JDBC.Filmselskap;

public class LagFilmselskapCtrl extends DBConn {
	
	private Filmselskap filmselskap;

    public LagFilmselskapCtrl() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagFilmselskapCtrl="+e);
            return;
        }
    }
    
    public Filmselskap initFilmselskap(String navn){
        this.filmselskap = new Filmselskap(navn);
        this.filmselskap.initialize(conn);
        return this.filmselskap;
    }

    public Filmselskap lagFilmselskap(String navn, String url, String adresse, String nasjonalitet){
        this.filmselskap = new Filmselskap(navn, url, adresse, nasjonalitet);
        return this.filmselskap;
    }
    
    public void fullforFilmselskap() {
        this.filmselskap.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagFilmselskapCtrl="+e);
            return;
        }
    }
    
    public List<Filmselskap> hentAlleFilmselskap() {
    	List<Filmselskap> FilmselskapListe = new ArrayList<>();
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from filmselskap");
			while (rs.next()) {
				FilmselskapListe.add(this.initFilmselskap(rs.getString("navn")));
			}
		} catch (Exception e) {
			System.out.println("db error during select of filmselskap= "+e);
		}
    	return FilmselskapListe;
    }
    
    public void hentAlleFilmselskapString() {
    	List<Filmselskap> FilmselskapListe = this.hentAlleFilmselskap();
    	for (int i=0; i < FilmselskapListe.size(); i++) {
    		System.out.println(i+1 + ": " + FilmselskapListe.get(i));
    	}
    }
    
    public void hentAlleFilmMedSjangerString(String sjanger_navn) {
    	int i = 0;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select P.selskapsNavn, count(P.tittel) as AntallFilmer \n" + 
					"from (sjangerForProduksjon as SFP \n" + 
					"natural join sjanger as S) \n" + 
					"inner join produksjon as P on (SFP.produksjonsTittel = P.tittel)\n" + 
					"where sjanger_navn='"+sjanger_navn+"' and P.tittel not in (\n" + 
					"	select P.tittel\n" + 
					"	from (sjangerForProduksjon as SFP \n" + 
					"	natural join sjanger as S) \n" + 
					"	natural join episodeISesong as E\n" + 
					"	inner join produksjon as P on (P.tittel = E.produksjonsTittel))\n" + 
					"group by P.SelskapsNavn\n" + 
					"order by AntallFilmer desc");
			while (rs.next()) {
				i++;
				System.out.println(i + ": " + rs.getString("selskapsNavn") + " har " + rs.getString("AntallFilmer")+ " " + sjanger_navn);
			}
		} catch (Exception e) {
			System.out.println("db error during select of FilmMedSjanger= "+e);
		}
    }
    
}











