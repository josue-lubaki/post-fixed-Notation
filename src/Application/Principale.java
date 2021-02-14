package Application;

/* Principale.java	*******************************************************************************
**********			@Authors : ISMAEL COULIBALY & XUAYO HU & JOSUE LUBAKI					*******
**************************************************************************************************/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Principale {
	public static void main(String[] args) {
		String input, output;
		String resultat;
		List<String> affichage_finale = new ArrayList<>();
		Scanner scan = new Scanner(System.in);

		System.out.println("\n\t**************************************************\n\t**\t\tNOTATION POST-FIXÉ\t\t**"
				+ "\n\t**************************************************");
		System.out.println(
				"Saisissez Autant d'opération à résoudre, \nEnsuite tapez deux fois sur la touche ENTER pour afficher le resultat\n");
		while (true) {
			
			input = scan.nextLine();

			// CONDITION DE SORTIE
			if (input.equals("")) // quit if [Enter]
				break;

			// make a translator
			InToPost theTrans = new InToPost(input);
			output = theTrans.doTrans(); // do the translation

			// FAIRE LE CALCUL DANS L'ORDRE INVERSE DE INTOPOST.CLASS
			BackPost aParser = new BackPost(output + " ");

			try {
				resultat = aParser.doParse(); // do the evaluation
			} catch (Exception e) {
				resultat = "Error";
			}

			// AJOUTER LE RESULTAT DU TRAITEMENT DANS LA LISTE
			affichage_finale.add(input + "\t;\t" + output + "\t;\t" + resultat + "\n");

		}

		int iterator = 0;
		// AFFICHER LE RESULTAT FINAL
		while (iterator < affichage_finale.size()) {
			System.out.println(affichage_finale.get(iterator));
			iterator++;
		}

		scan.close();
	}
}
