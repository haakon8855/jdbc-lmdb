package tui;

import java.util.Scanner;

public class TuiMain {
	
	public static void main(String[] args) {
		int choice = 10;
	    Scanner sc = new Scanner(System.in);
	    
	    
	    while (choice != 0) {
	    	System.out.println("\n\nWhat do you want to do?\n"
	    			+ "1: Få rollene til en gitt skuespiller\n"
	    			+ "2: Få filmene en gitt skuespiller opptrer i\n"
	    			+ "3: Få hvilke filmselskap som lager flest filmer innen en gitt sjanger\n"
	    			+ "4: Sette inn en ny film\n"
	    			+ "5: Skriv ny anmeldelse av en episode\n"); 
	    	choice = sc.nextInt();
	    	TuiGetArtistRoles t = new TuiGetArtistRoles();
	    	TuiAnmeldelseAvEpisodeISerie a = new TuiAnmeldelseAvEpisodeISerie();
	    	TuiCreateNewMovie newMovie = new TuiCreateNewMovie();
	    	TuiGetFilmsWithGenre g = new TuiGetFilmsWithGenre();
			
			
	    	
	    	switch (choice) {
			case 1:
				t.getArtistRolleScanner();
				break;
			case 2:
				t.getArtistRolleFilmScanner();
				break;
			case 3:
				g.getProductionGenreScanner();
				break;
			case 4:
				newMovie.run();
				break;
			case 5: 
				a.anmeldelseAvepisodeISerie();
				break;
				

			default:
				System.out.println("Ugyldig valg!");
				break;
			}
	    	
	    }
	    
	    sc.close();
	}
	
	
}
