package Application;

/* BackPost.java	*******************************************************************************
**********			@Authors : ISMAEL COULIBALY & XUAYO HU & JOSUE LUBAKI					*******
**************************************************************************************************/

/*
 * CETTE CLASSE EST CENSÉ TRAITÉ CHAQUE DIGIT PASSÉ EN INPUT PAR L'UTILISATEUR
 * ET ELLE S'INTERESSERA ESSENTIELLEMENT AUX OPERANDES
 */

class BackPost {
	private final String input;

	// --------------------------------------------------------------
	public BackPost(String s) {
		input = s;
	}

	// --------------------------------------------------------------
	public String doParse() {
		StackX theStack = new StackX(20, 2); // make new stack
		char ch;
		StringBuilder str = new StringBuilder();
		int j;
		double num1 = 0, num2, interAns;
		for (j = 0; j < input.length(); j++) // for each char,
		{
			ch = input.charAt(j); // read from input

			if ((ch >= '0' && ch <= '9') || ch == '.') // if it’s a number
				str.append(ch);
			else if (ch == ' ') {
				if (str.toString().matches("[0-9]{1,13}(\\.[0-9]*)?")) { // regex

					theStack.pushNumber(Double.parseDouble(str.toString())); // push it
					str = new StringBuilder(); // reinitialise
				}
			} else // it’s an operator
			{
				if (ch == '$')
					num2 = theStack.popNumber();
				else {
					num2 = theStack.popNumber(); // pop operands
					num1 = theStack.popNumber();
				}

				switch (ch) // do arithmetic
				{
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/': {
					// Gérer l'erreur lors de la division d'un nombre par 0
					if (num2 == 0) {
						return "Error";
					} else
						interAns = num1 / num2;
					break;
				}
				case '$': {
					if (num2 < 0) // erreur au calcul de la racine carre d'un nombre negatif
						return "Error";
					else
						interAns = Math.sqrt(num2);
					break;
				}
				default:
					interAns = 0;
				}
				theStack.pushNumber(interAns); // push result
			}
		}
		interAns = theStack.popNumber(); // get answer
		return Double.toString(interAns);
	}
}