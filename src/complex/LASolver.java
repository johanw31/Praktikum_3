package complex;

public class LASolver {
	/**
	 * Solve the equation A*x = y for x.
	 * 
	 * @param A double[][] real NxN matrix.
	 * @param y double[] real N dimensional right side vector.
	 * @return double[] real N dimensional solution x.
	 */
	public double[] solve(double[][] A, double[] y) {
		matcheck (A);
		veccheck (A,y);
		int n = A.length;
		double[] z = y, x = new double[n];
		// decompose A into left and right triangular
		// matrix A = L*R.
		lrdecompose(A);
		// forward elimination: R*x=:z => L*z = y => z
		for(int i = 0; i<n; i++) {
		for(int j = 0; j<i; j++) {
		z[i] -= A[i][j]*z[j];
		}
		}
		// backward elimination: z=R*x => x
		for (int j = n - 1; j >= 0; j--) {
		for (int i = j + 1; i < n; i++) {
		z[j] -= A[j][i] * x[i];
		}
		x[j] = z[j] / A[j][j];
		}
		return x;
	}
    
	/**
	 * Solve the complex equation A*x = y for x.
	 * 
	 * @param A double[][] complex NxN matrix.
	 * @param y double[] complex N dimensional right side vector.
	 * @return double[] complex N dimensional solution x.
	 */
	public Complex[] solvec(Complex[][] A, Complex[] y) {
		int n = A.length;
		Complex[] z = y, x = new Complex[n];
		// decompose A into left and right triangular
		// matrix A = L*R.
		lrdecomposec(A);
		// forward elimination: R*x=:z => L*z = y => z
		for(int i = 0; i<n; i++) {
		for(int j = 0; j<i; j++) {
	//	z[i] -= A[i][j]*z[j];
		z[i] = z[i].sub(A[i][j].mul(z[j]));
		}
		}
		// backward elimination: z=R*x => x
		for (int j = n - 1; j >= 0; j--) {
		for (int i = j + 1; i < n; i++) {
		//z[j] -= A[j][i] * x[i];
		z[j] = z[j].sub(A[j][i].mul(x[i]));
		}
		//x[j] = z[j] / A[j][j];
		x[j] = z[j].div(A[j][j]);
		}
		return x;
	}
	/**
	 * Calculate the determinant of matrix A.
	 * 
	 * @param A double[][] the NxN matrix
	 * @throws Determinante = 0
	 * @return double the determinant of A
	 */
	public double det(double[][] A) {
		int n = A.length;
		double [][] B = new double [n][n] ;
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				B[i][j] = A[i][j];
			}
		}
		double determinante = 1;
		lrdecompose(B);
		for (int i = 0; i < n; i++) {
			determinante *= B[i][i];
		}
		if (determinante == 0) {
			throw new IllegalArgumentException ("Determinante = 0");
		}
		return determinante;
	}

	/**
	 * Internal method to perform the L*R decomposition.
	 * 
	 * @param A double[][] the matrix to decompose.
	 */
	private void lrdecompose(double[][] A) {
		int n = A.length;
		matcheck (A);
		for (int i = 0; i < n - 1; i++) {
			for (int k = i + 1; k < n; k++) {
				A[k][i] = A[k][i] / A[i][i];
				for (int j = i + 1; j < n; j++) {
					A[k][j] = A[k][j] - A[k][i] * A[i][j];
				}
			}
		}

	}
	/**
	 * Internal method to perform the complex L*R decomposition.
	 * @throws A not NxN
	 * @throws A[%d][%d] singular
	 * @param A double[][] the complex matrix to decompose.
	 */
	private void lrdecomposec(Complex[][] A) {
		int n = A.length;
		int m = A[0].length;
		if (n!=m) {
		throw new IllegalArgumentException("A not NxN");
		}
		for(int i = 0; i< n; i++) {
		for(int j = i; j<n; j++) {
		for(int k = 0; k<i; k++) {
		A[i][j] = A[i][j].sub(A[i][k].mul(A[k][j]));
		}
		}
		if(A[i][i].abs()<0.000001) {
		String msg = String.format("A[%d][%d] singular",i,i);
		throw new IllegalArgumentException(msg);
		}
		for(int j=i+1; j<n; j++) {
		for(int k = 0; k<i; k++) {
		A[j][i] = A[j][i].sub(A[j][k].mul(A[k][i]));
		}
		A[j][i]= A[j][i].div(A[i][i]);
		}
		}
	}
	/**
	 * Method to check the result
	 * 
	 * @param A double[][] die berechnetet Matrix.
	 * @param E double[]   der berechnete Lösungsvektor
	 * @param L double[]   muss mit dem rechtseitigem Vektor übereinstimmen
	 * @return value of the calculation
	 */
	public double [] ergebnisPrüfen (double [][] A,double[] E, double[] L ) {
		double [] y = new double [L.length];
		for (int i=0;i<L.length;i++) {
			for (int j=0;j<L.length;j++) {
				y[i] +=A[i][j]*L[j];
			}
			System.out.printf("Eingabevektor: %5.2f Kontrollrechnung: %5.2f\n",E[i],y[i]);
		}
		return y;
		}

	/**
	 * Internal method to check the matrix dimension (only for real matrix)
	 * @param M double[][] matrix that has to be checked.
	 * @throws Keine NxN Matrix!
	 * @throws Null auf der Hauptdiagonalen!
	 */
	private void matcheck (double[][] M) {
		int Zeilen = M.length;
		for (int i=0;i<Zeilen;i++) {
			int Spalten = M[i].length;
			if (Spalten != Zeilen) {
				throw new IllegalArgumentException ("Keine NxN Matrix!");
			}
			if (M[i][i] == 0) {
				throw new IllegalArgumentException ("Null auf der Hauptdiagonalen!");
			}

		}
	}
	
	/**
	 * Internal method to check if the matrix and vector are the right demension (only real matrix)
	 * @param M double[][] matrix
	 * @param V double[]   vector
	 * @throws Vektor Dimension überprüfen
	 */
	private void veccheck (double[][] M, double [] V) {
		int matdim = M.length;
		int vecdim = V.length;
		if (matdim != vecdim) {
			throw new IllegalArgumentException ("Vektor Dimension überprüfen!");
		}
	}
	
	/**
	 * method for copying real matrix and vector (only real matrix)
	 * @param M double[][] matrix you want to copy
	 * @param Mcopy double [][] matrix to paste the original matrix
	 * @param V double[]   vector you want to copy
	 * @param Vcopy double[]     vector to paste the original vector
	 */
	public void matveccopy (double[][] M,double[][] Mcopy, double[] V, double [] Vcopy) {
		matcheck (M);
		veccheck (M,V);
		int n = M.length;
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
			Mcopy[i][j]=M[i][j];
			}
			Vcopy[i] = V[i];
		}
	}

	
	/**
	 * print method (only real matrix)
	 * @param B double[][] matrix to print.
	 * @param kommaStelle  amount of decimals
	 */
	public void matprint (double [][] B, double kommaStelle ) {
		for (int i=0; i<B.length; i++) {
			for (int j=0;j<B.length; j++) {
				System.out.printf ("%"+kommaStelle+"f  ",B[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * print method (only real vector)
	 * @param B double[] vector to print 
	 * @param kommaStelle  amount of decimals
	 * @param c char  letter vor amps, volts, or resistance
	 */
	public void vecprint (double [] B, double kommaStelle, char c) {
		for (int i=0; i<B.length; i++) {
			if (c == 'i') {
				System.out.print("I"+(i+1)+":  ");
			}
			if (c == 'u') {
				System.out.print("U"+(i+1)+":  ");
			}
			if (c == 'r') {
				System.out.print("R"+(i+1)+":  ");
			}
			System.out.printf ("%"+kommaStelle+"f\n",B[i]);
		
		}
		System.out.println();
	}
	
	/**
	 * compute the deviation
	 * @param y1 double[] vector 1 
	 * @param y2 double[] vector 2
	 * @param delta deviation size
	 */
	public void fehler (double[] y1, double[] y2, double delta) {
		int n = y1.length;
		double ergebnis;
		for (int i=0;i<n;i++) {
			ergebnis =Math.abs(y1[i]-y2[i]);
			if (ergebnis < delta) {
				System.out.println("Abweichung i.O");
			}
			else {
		    System.out.println ("Abweichung zu groß");
			}
		}
		System.out.println();
	}
	
	/**
	 * Bisektionsverfahren
	 * @param A double[][] real matrix 
	 * @param Y double[] real vector
	 * @param eps deviation size
	 * @return zero point as double 
	 */
	public double bisektion (double[][]A, double[] Y, double eps) {
		int n = A.length;
		double start = 20;
		double end = 300;
		double c;
		double[][] Atemp = new double [n][n];
        double[] Ytemp = new double [n];
        double[] ergebnis = new double[n];
        while (eps<=2*(end-start)) {
        matveccopy (A,Atemp,Y,Ytemp);
        c = (start+end)/2;
        Atemp[3][4] = c;
        Atemp[4][4] = c;
		ergebnis = solve (Atemp,Ytemp);
		if (ergebnis[5]>0) {
			end = c;
		}
		else {
			start = c;
		}
        }
        c = (start + end) / 2;
		return (end);
	}
	
	public boolean complexfehler (Complex[][] A1, Complex[] Y1, Complex[] Spannungsvektor, double delta) {
		int n = A1.length;
	    Complex temp[] = new Complex[n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				temp[i] = A1[i][j].mul(Y1[j]);
			}
		}
		for (int i=0;i<n;i++) {
		double diff = temp[i].sub(Spannungsvektor[i]).abs();
		if (diff > delta) {
			return true;
		}
		
		}
		return false;
	}
	
	public Complex [] kontrollRechnungComplex (Complex [][] A, Complex[] L ) {
		Complex [] y = new Complex [L.length];
		for (int i=0;i<L.length;i++) {
			for (int j=0;j<L.length;j++) {
				y[i] =A[i][j].mul(L[j]);
			}
		}
		return y;
		}
}

