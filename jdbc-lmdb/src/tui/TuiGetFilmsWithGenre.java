package tui;

import java.util.Scanner;

import controllers.LagFilmselskapCtrl;
import controllers.LagProduksjonCtrl;
import controllers.LagSjangerCtrl;

public class TuiGetFilmsWithGenre {
	private LagSjangerCtrl lagSjangerCtrl;
	private LagFilmselskapCtrl lagFilmselskapCtrl;
	
	public TuiGetFilmsWithGenre() {
		this.lagSjangerCtrl = new LagSjangerCtrl();
		this.lagFilmselskapCtrl = new LagFilmselskapCtrl();
	}
	
	public void getProductionGenreScanner() {
		System.out.println("Velg sjanger du ønsker å finne hvilket filmselskap som har laget flest filmer av ved å skrive inn tallet til sjangeren");
		
		this.lagSjangerCtrl.hentAlleSjangerString();
		System.out.println("\n");
		
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		input = scanner.nextLine();
		System.out.println(this.lagSjangerCtrl.hentAlleSjanger().get(Integer.parseInt(input)-1).getPK());
		this.lagFilmselskapCtrl.hentAlleFilmMedSjangerString(this.lagSjangerCtrl.hentAlleSjanger().get(Integer.parseInt(input)-1).getPK());
		
		
	}
	
	public static void main(String[] args) {
		TuiGetFilmsWithGenre t = new TuiGetFilmsWithGenre();
		t.getProductionGenreScanner();
	}

}
