package tui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import JDBC.*;
import controllers.*;

public class TuiCreateNewMovie {
	
	private  Produksjon film;
	
	public void run() {
		String tittel = getInput("Skriv inn tittel: ");
		int lengde = Integer.parseInt(getInput("Skriv inn lengde (min): "));
		int utgivelsesaar = Integer.parseInt(getInput("Skriv inn utgivelsesaar (YYYY): "));
		String lanseringsdato = getInput("Skriv inn lanseringsdato (YYYY-MM-DD): ");
		String sammendrag = getInput("Skriv inn sammendrag: ");
		Filmselskap filmselskap = getFilmselskap();
		LagProduksjonCtrl lagProduksjonCtrl = new LagProduksjonCtrl();
		this.film = lagProduksjonCtrl.lagProduksjon(tittel, lengde, utgivelsesaar, lanseringsdato, sammendrag, filmselskap);
		lagProduksjonCtrl.fullforProduksjon();
		
		LagRegissertAvCtrl lagRegissertAvCtrl = new LagRegissertAvCtrl();
		while (true) {
			Artist regissor = getRegissor();
			lagRegissertAvCtrl.lagRessigertAv(regissor, film);
			lagRegissertAvCtrl.fullforRessigertAv();
			String choice = getInput("Vil du legge til en regissør til? (Y/n) ");
			if (choice.equals("n")) {
				break;
			}
		}
		
		LagSkrevetAvCtrl lagSkrevetAvCtrl = new LagSkrevetAvCtrl();
		while (true) {
			Artist manusforfatter = getManusforfatter();
			lagSkrevetAvCtrl.lagSkrevetAv(manusforfatter, film);
			lagSkrevetAvCtrl.fullforSkrevetAv();
			String choice = getInput("Vil du legge til en manusforfatter til? (Y/n) ");
			if (choice.equals("n")) {
				break;
			}
		}
		
		LagSkuespillerRolleCtrl lagSkuespillerRolleCtrl = new LagSkuespillerRolleCtrl();
		while (true) {
			Artist skuespiller = getSkuespiller();
			String rolle = getInput("Velg rolle: ");
			lagSkuespillerRolleCtrl.lagSkuespillerRolle(skuespiller, film, rolle);
			lagSkuespillerRolleCtrl.fullforSkuespillerRolle();
			String choice = getInput("Vil du legge til en skuespiller/rolle til? (Y/n) ");
			if (choice.equals("n")) {
				break;
			}
		}
		
		LagMusikkIFilmCtrl lagMusikkIFilmCtrl = new LagMusikkIFilmCtrl();
		while (true) {
			Musikk musikk = getMusikk();
			lagMusikkIFilmCtrl.lagMusikkIFilm(musikk, film);
			lagMusikkIFilmCtrl.fullforMusikkIFilm();
			String choice = getInput("Vil du legge til en musikk til? (Y/n) ");
			if (choice.equals("n")) {
				break;
			}
		}
		
		LagSjangerForProduksjonCtrl lagSjangerForProduksjonCtrl = new LagSjangerForProduksjonCtrl();
		while (true) {
			Sjanger sjanger = getSjanger();
			lagSjangerForProduksjonCtrl.lagSjangerForProduksjon(sjanger, film);
			lagSjangerForProduksjonCtrl.fullforSjangerForProduksjon();
			String choice = getInput("Vil du legge til en sjanger til? (Y/n) ");
			if (choice.equals("n")) {
				break;
			}
		}
		
	}
	
	public Filmselskap getFilmselskap() {
		LagFilmselskapCtrl lagFilmselskapCtrl = new LagFilmselskapCtrl();
		List<Filmselskap> filmselskap = lagFilmselskapCtrl.hentAlleFilmselskap();
		System.out.println("\nVelg filmselskap: ");
		System.out.println("0: Lag nytt filmselskap");
		lagFilmselskapCtrl.hentAlleFilmselskapString();
		int choice = Integer.parseInt(getInput("Ditt valg: "));
		if (choice == 0) {
			return nyttFilmselskap();
		}
		return filmselskap.get(choice-1);
	}
	
	public Filmselskap nyttFilmselskap() {
		System.out.println("\nNytt filmselskap: ");
		String navn = getInput("Skriv inn navn: ");
		String url = getInput("Skriv inn url: ");
		String adresse = getInput("Skriv inn adresse: "); 
		String nasjonalitet = getInput("Skriv inn nasjonalitet: ");
		LagFilmselskapCtrl lagFilmselskapCtrl = new LagFilmselskapCtrl();
		Filmselskap filmselskap = lagFilmselskapCtrl.lagFilmselskap(navn, url, adresse, nasjonalitet);
		lagFilmselskapCtrl.fullforFilmselskap();
		return filmselskap;
	}
	
	public Artist getRegissor() {
		LagArtistCtrl lagArtistCtrl = new LagArtistCtrl();
		List<Artist> artister = lagArtistCtrl.hentAlleArtist();
		System.out.println("\nVelg regissør: ");
		System.out.println("0: Lag ny regissør");
		lagArtistCtrl.hentAlleArtistString();
		int choice = Integer.parseInt(getInput("Ditt valg: "));
		if (choice == 0) {
			return nyArtist();
		}
		return artister.get(choice-1);
	}

	public Artist getManusforfatter() {
		LagArtistCtrl lagArtistCtrl = new LagArtistCtrl();
		List<Artist> artister = lagArtistCtrl.hentAlleArtist();
		System.out.println("\nVelg manusforfatter: ");
		System.out.println("0: Lag ny manusforfatter");
		lagArtistCtrl.hentAlleArtistString();
		int choice = Integer.parseInt(getInput("Ditt valg: "));
		if (choice == 0) {
			return nyArtist();
		}
		return artister.get(choice-1);
	}

	public Artist getSkuespiller() {
		LagArtistCtrl lagArtistCtrl = new LagArtistCtrl();
		List<Artist> artister = lagArtistCtrl.hentAlleArtist();
		System.out.println("\nVelg Skuespiller: ");
		System.out.println("0: Lag ny skuespiller");
		lagArtistCtrl.hentAlleArtistString();
		int choice = Integer.parseInt(getInput("Ditt valg: "));
		if (choice == 0) {
			return nyArtist();
		}
		return artister.get(choice-1);
	}
	
	public Artist nyArtist() {
		System.out.println("\nNy artist: ");
		String navn = getInput("Skriv inn navn: ");
		int fodselsaar = Integer.parseInt(getInput("Skriv inn fødselsår: "));
		String nasjonalitet = getInput("Skriv inn nasjonalitet: "); 
		LagArtistCtrl lagArtistCtrl = new LagArtistCtrl();
		Artist artist = lagArtistCtrl.lagArtist(navn, fodselsaar, nasjonalitet);
		lagArtistCtrl.fullforArtist();
		return artist;
	}
	
	public Musikk getMusikk() {
		LagMusikkCtrl lagMusikkCtrl = new LagMusikkCtrl();
		List<Musikk> musikker = lagMusikkCtrl.hentAlleMusikk();
		System.out.println("\nVelg Musikk: ");
		lagMusikkCtrl.hentAlleMusikkString();
		int choice = Integer.parseInt(getInput("Ditt valg: "));
		return musikker.get(choice-1);
	}

	public Sjanger getSjanger() {
		LagSjangerCtrl lagSjangerCtrl = new LagSjangerCtrl();
		List<Sjanger> sjangere = lagSjangerCtrl.hentAlleSjanger();
		System.out.println("\nVelg Sjanger: ");
		System.out.println("0: Lag ny sjanger");
		lagSjangerCtrl.hentAlleSjangerString();
		int choice = Integer.parseInt(getInput("Ditt valg: "));
		if (choice == 0) {
			return nySjanger();
		}
		return sjangere.get(choice-1);
	}
	
	public Sjanger nySjanger() {
		System.out.println("\nNy sjanger: ");
		String navn = getInput("Skriv inn sjangernavn: ");
		LagSjangerCtrl lagSjangerCtrl = new LagSjangerCtrl();
		Sjanger sjanger = lagSjangerCtrl.lagSjanger(navn);
		lagSjangerCtrl.fullforSjanger();
		return sjanger;
	}
	
	public String getInput(String prompt) {
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
	
	public static void main(String[] args) {
		TuiCreateNewMovie a = new TuiCreateNewMovie();
		a.run();
	}
	
}
