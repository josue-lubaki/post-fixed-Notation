package Application;

/** StackX.java	***********************************************************************************
**********			@Authors : ISMAEL COULIBALY & XUAYO HU & JOSUE LUBAKI					*******
**************************************************************************************************/
/**
 * CETTE CLASSE SERT DE SOCLE, C'EST À PARTIR D'ELLE QU'ON IMPLEMENTE LA CLASSE
 * BackPost ET InToPost ELLE DISPOSE D'UN TABLEAU DES CARACTÈRES []stackArray
 * QUI PERMET DE STOCKER CHACUN DE DIGIT SON BUT EST AUSSI DE SEPARER LES
 * DONNÉES (CHIIFRES ET OPERATEURS)
 */
class StackX {
	private final int maxSize;
	private char[] stackArray;
	private double[] stackArrayNumber;// pille des operandes
	private int top;

	// --------------------------------------------------------------
	public StackX(int s, int choix) // constructor
	{
		maxSize = s;

		if (choix == 1)// cree une pile de char si choix est 1
			stackArray = new char[maxSize];
		else// cree une pile de double
			stackArrayNumber = new double[maxSize];

		top = -1;
	}

	// --------------------------------------------------------------
	public void push(char j) // put item on top of stack
	{
		stackArray[++top] = j;

	}

	// --------------------------------------------------------------
	public char pop() // take item from top of stack
	{
		return stackArray[top--];
	}

	// --------------------------------------------------------------
	public char peek() // peek at top of stack
	{
		return stackArray[top];
	}

	// --------------------------------------------------------------
	public boolean isEmpty() // true if stack is empty
	{
		return (top == -1);
	}

	// -------------------------------------------------------------
	public boolean isFull() // true if stack is full
	{
		return (top == maxSize - 1);
	}

	// -------------------------------------------------------------
	public int size() // return size
	{
		return top + 1;
	}

	// --------------------------------------------------------------
	public char peekN(int n) // return item at index n
	{
		return stackArray[n];
	}

	// ---------------------------------------------------------------
	// METHODES DE LA PILES CONCERNANT LES NOMBRES // TRÈS UTILE LORS DU CALCUL
	// (BackPost.class)
	// --------------------------------------------------------------
	public void pushNumber(double j) // put item on top of stack
	{
		stackArrayNumber[++top] = j;
	}

	// --------------------------------------------------------------
	public double popNumber() // take item from top of stack
	{
		return stackArrayNumber[top--];
	}

	// --------------------------------------------------------------
	public double peekNumber() // peek at top of stack
	{
		return stackArrayNumber[top];
	}

	// --------------------------------------------------------------
	public double peekNumber(int n) // retourne l'item a la n eme position
	{
		return stackArrayNumber[n];
	}

	// --------------------------------------------------------------

	public void displayStack(String s) {
		System.out.print(s);
		System.out.print("Stack (bottom-->top): ");
		for (int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.print(' ');
		}
		System.out.println("");
	}
}
