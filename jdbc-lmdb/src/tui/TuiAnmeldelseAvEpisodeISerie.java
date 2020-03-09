package tui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

import JDBC.Bruker;
import JDBC.DBConn;
import JDBC.Produksjon;
import JDBC.Serie;
import JDBC.Sesong;
import controllers.LagAnmeldelseAvProduksjonCtrl;
import controllers.LagBrukerCtrl;
import controllers.LagProduksjonCtrl;
import controllers.LagSerieCtrl;
import controllers.LagSesongCtrl;

public class TuiAnmeldelseAvEpisodeISerie  extends DBConn{
	
	private String anmeldelse = "";
	private int rating;
	private ArrayList<Serie> serier = new ArrayList<Serie>();
	private ArrayList<Integer> sesonger = new ArrayList<Integer>();
	private ArrayList<Produksjon> episoder = new ArrayList<Produksjon>();
	private LagSerieCtrl lagSerieCtrl = new LagSerieCtrl();
	private LagSesongCtrl lagSesongCtrl = new LagSesongCtrl();
	private LagProduksjonCtrl lagProduksjonCtrl = new LagProduksjonCtrl();
	private LagAnmeldelseAvProduksjonCtrl lagAnmeldelseAvProduksjonCtrl = new LagAnmeldelseAvProduksjonCtrl();
	private Serie valgtSerie;
	private Sesong valgtSesong;
	private Produksjon valgtEpisode;
	// default bruker
	private LagBrukerCtrl lagBrukerCtrl = new LagBrukerCtrl();
	private Bruker bruker;
	
	public TuiAnmeldelseAvEpisodeISerie() {
		// TODO Auto-generated constructor stub
		connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of TuiAnmeldelseAvEpisodeISerie="+e);
            return;
        }
		getSerier(conn);
		this.bruker = lagBrukerCtrl.initBruker("haakon"); // velge en eksisterende bruker
		
	}
	
	public void getSerier(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from serie");
			while(rs.next()) {
				String serieTittel = rs.getString("tittel");
				Serie serie = lagSerieCtrl.initSerie(serieTittel);
				serier.add(serie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getSesonger(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sesong where serieTittel= '"+this.valgtSerie.getPK()+"'");
			while(rs.next()) {
				int sesongNR = rs.getInt("sesongNR");
				this.sesonger.add(sesongNR);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getEpisoder(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select produksjonsTittel from episodeISesong where serieTittel= '"+this.valgtSerie.getPK()+"'"+"and SesongNR ='"+this.valgtSesong.getSesongNR()+"'");
			while(rs.next()) {
				String episodeTittel = rs.getString("produksjonsTittel");
				Produksjon episode = lagProduksjonCtrl.initProduksjon(episodeTittel);
				this.episoder.add(episode);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String anmeldelse(String prompt) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(prompt);
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	
	public void rating() {
		int rating = Integer.parseInt(anmeldelse(""));
		this.rating = rating;
	}
	
	public void anmeldelseAvepisodeISerie() {
		System.out.println("Hvilken serie vil du anmelde: ");
		for (int i = 0; i < serier.size(); i++) {
			int nr = i+1;
			System.out.println(nr + ". " + serier.get(i).getPK());
		}
		int serieNr =Integer.parseInt(anmeldelse("")); 
		int serieIndex = serieNr-1;
		this.valgtSerie = lagSerieCtrl.initSerie(serier.get(serieIndex).getPK());
		getSesonger(conn);
		System.out.println("Hvilken Sesong er episoden i: ");
		for (int i = 0; i < sesonger.size(); i++) {
			int nr = i+1;
			System.out.println(nr+". "+sesonger.get(i));
		}
		int sesongNr = Integer.parseInt(anmeldelse(""));
		int sesongIndex = sesongNr-1;
		this.valgtSesong = lagSesongCtrl.initSesong(this.sesonger.get(sesongIndex), this.valgtSerie);
		getEpisoder(conn);
		System.out.println("Hvilken episode vil du anmelde: ");
		for (int i = 0; i < episoder.size(); i++) {
			int nr = i+1;
			System.out.println(nr+". "+episoder.get(i).getPK());
		}
		int episodeNr = Integer.parseInt(anmeldelse(""));
		int episodeIndex = episodeNr-1;
		this.valgtEpisode = lagProduksjonCtrl.initProduksjon(this.episoder.get(episodeIndex).getPK());
		this.anmeldelse = this.anmeldelse("skriv en anmeldelse: ");
		System.out.println("Gi en rating fra 1 til 10");
		this.rating();
		lagAnmeldelseAvProduksjonCtrl.lagAnmeldelseAvProduksjon(bruker, this.valgtEpisode, this.anmeldelse, this.rating);
		lagAnmeldelseAvProduksjonCtrl.fullforAnmeldelseAvProduksjon();
		
	}
	
}
