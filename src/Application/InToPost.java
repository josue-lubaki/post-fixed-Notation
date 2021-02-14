package Application;

/* InToPost.java	*******************************************************************************
**********			@Authors : ISMAEL COULIBALY & XUAYO HU & JOSUE LUBAKI					*******
**************************************************************************************************/
/*
 * CETTE CLASSE EST CENSÉ TRAITÉ CHAQUE DIGIT PASSÉ EN INPUT PAR L'UTILISATEUR ET ELLE S'INTERESSERA
 * ESSENTIELLEMENT AUX OPERATEURS (+,-,*,/,$) ET AUX CARACTÈRES TELS QUE LA PARENTHÈSE OUVRANTE ET FERMANTE
 * */

class InToPost // infix to postfix conversion
{
	private final StackX theStack;
	private final String input;
	private String output = "";

	// --------------------------------------------------------------
	public InToPost(String in) // constructor
	{
		input = in.replaceAll("\\s", "");
		int stackSize = input.length();
		theStack = new StackX(stackSize, 1);
	}

	// --------------------------------------------------------------
	public String doTrans() // do translation to postfix
	{
		for (int j = 0; j < input.length(); j++) {
			char ch;

			ch = input.charAt(j);

			switch (ch) {

			case '+': // it’s + or -
			case '-':
				gotOper(ch, 1); // go pop operators
				output = output + " ";
				break; // (precedence 1)
			case '*': // it’s * or /
			case '/':
				gotOper(ch, 2); // go pop operators
				output = output + " ";
				break; // (precedence 2)
			case '$':
				gotOper(ch, 3); // go pop operators
				break; // (precedence 3)
			case '(': // it’s a left paren
				theStack.push(ch); // push it
				break;
			case ')': // it’s a right paren
				gotParen(ch); // go pop operators
				break;
			default: // must be an operand
				output += ch; // write it to output
				break;
			} // end switch
		} // end for
		while (!theStack.isEmpty()) // pop remaining opers
		{
			/*
			 * CONDITION POUR UNE EXPRESSION POST-FIXÉ AYANT UNE ERREUR LORS DE SA
			 * TRANSLATION OU QUI SE TERMINE AVEC '(' UNE PARANTHÈSE OUVRANTE
			 * 
			 * @see peek() : connaître le sommet de la pile (le dernier élément introduit
			 *      dans la pile)
			 */
			if (theStack.peek() == '(') {
				output = "Error";
				break;
			}
			output = output + " " + theStack.pop(); // write to output
		}
		return output; // return postfix
	} // end doTrans()
		// --------------------------------------------------------------

	public void gotOper(char opThis, int prec1) { // got operator from input
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if (opTop == '(') // if it’s a ‘(‘
			{
				theStack.push(opTop); // restore ‘(‘
				break;
			} else // it’s an operator
			{
				int prec2; // precedence of new op
				if (opTop == '+' || opTop == '-') // find new op prec
					prec2 = 1;
				else if (opTop == '*' || opTop == '/')
					prec2 = 2;
				else
					prec2 = 3; // POUR OPTOP == '$' ET LES AUTRES
				if (prec2 < prec1) // if prec of new op less
				{ // than prec of old
					theStack.push(opTop); // save newly-popped op
					break;
				} else // prec of new not less
						// CRÉATION DE L'ESPACE ENTRE LE CHIFFRE À GAUCHE ET LE SIGNE À DROITE
					output = output + " " + opTop; // than prec of old
			} // end else (it’s an operator)
		} // end while
		theStack.push(opThis); // push new operator
	} // end gotOp()
		// --------------------------------------------------------------

	public void gotParen(char ch) { // got right paren from input
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			if (chx == '(') // if popped ‘(‘
				break; // we’re done
			else // if popped operator
				output = output + " " + chx; // CRÉER DE L'ESPACE ENTRE LE CHIFFRE À GAUCHE[OUTPUT] ET L'OPERATEUR À
												// DROITE[CHX]
		} // end while
	} // end popOps()

	// --------------------------------------------------------------
} // end class InToPost