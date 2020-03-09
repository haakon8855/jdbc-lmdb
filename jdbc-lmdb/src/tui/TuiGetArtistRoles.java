package tui;

import java.util.Scanner;

import controllers.LagArtistCtrl;
import controllers.LagSkuespillerRolleCtrl;

public class TuiGetArtistRoles {
	
	private LagSkuespillerRolleCtrl lagSkuespillerRolleCtrl;
	private LagArtistCtrl lagArtistCtrl;
	
	public TuiGetArtistRoles() {
		this.lagSkuespillerRolleCtrl = new LagSkuespillerRolleCtrl();
		this.lagArtistCtrl = new LagArtistCtrl();
	}
	
	public void getArtistRolleScanner() {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		System.out.println("Velg artist du ønsker å finne rollene til i filmer og episoder ved å skrive inn tallet til artisten");
		
		this.lagArtistCtrl.hentAlleArtistString();
		System.out.println("\n");
		
		
		input = scanner.nextLine();
		
		this.lagSkuespillerRolleCtrl.getSkuespillerRolle(this.lagArtistCtrl.hentAlleArtist().get(Integer.parseInt(input) - 1).getPK());
	}
	
	public void getArtistRolleFilmScanner() {
		System.out.println("Velg artist du ønsker å finne rollene til i filmer ved å skrive inn tallet til artisten");
		
		this.lagArtistCtrl.hentAlleArtistString();
		System.out.println("\n");
		
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		
		input = scanner.nextLine();
		
		this.lagSkuespillerRolleCtrl.getSkuespillerRolleFilm(this.lagArtistCtrl.hentAlleArtist().get(Integer.parseInt(input) - 1).getPK());
	}
	
	public static void main(String[] args) {
		TuiGetArtistRoles t = new TuiGetArtistRoles();
		t.getArtistRolleScanner();
	}
	
}
