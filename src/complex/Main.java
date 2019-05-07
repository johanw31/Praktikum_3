package complex;


public class Main {

	public static void main(String[] args) {

		LASolver test = new LASolver();
		/*Praktikum2 test = new Praktikum2();
		test.aufgabe1();
		test.aufgabe2();
		test.aufgabe3();*/
		double r1=1000;
		double r2=1000;
		double r3=1000000;
		double r4=2000;
		double r5=1000;
		double c1=1E-6;
		double c2=1E-6;
		double f;
		
		
		System.out.println("\tFrequenz\t|\tSpannung u2\t|\tWinkel u2 \t|\tStrom i3\t|\t\tWinkel i3");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		for ( f=100;f<=200;f=f+10) {
	    double xc1 = -(1/(2*Math.PI*f*c1));
	    double xc2 = -(1/(2*Math.PI*f*c2));
		
		Complex A [][]= {{new Complex(-r1,-xc1),new Complex(0,0),new Complex(-r3,0),new Complex(r4,0),new Complex(0,0),new Complex(0,0)},
		                 {new Complex(r1,xc1),new Complex(r2,0),new Complex(0,0),new Complex(0,0),new Complex(0,0),new Complex(0,0)},		                 
		                 {new Complex(1,0),new Complex(-1,0),new Complex(-1,0),new Complex(0,0),new Complex(0,0),new Complex(-1,0)},
		                 {new Complex(0,0),new Complex(0,0),new Complex(1,0),new Complex(1,0),new Complex(-1,0),new Complex(0,0)},
		                 {new Complex(0,0),new Complex(0,0),new Complex(r3,0),new Complex(0,0),new Complex(r5,0),new Complex(0,-xc2)},
		                 {new Complex(0,0),new Complex(-r2,0),new Complex(0,0),new Complex(0,0),new Complex(0,0),new Complex(0,xc2)},
		                 };
		Complex Acopy [][]= {{new Complex(-r1,-xc1),new Complex(0,0),new Complex(-r3,0),new Complex(r4,0),new Complex(0,0),new Complex(0,0)},
                        {new Complex(r1,xc1),new Complex(r2,0),new Complex(0,0),new Complex(0,0),new Complex(0,0),new Complex(0,0)},		                 
                        {new Complex(1,0),new Complex(-1,0),new Complex(-1,0),new Complex(0,0),new Complex(0,0),new Complex(-1,0)},
                        {new Complex(0,0),new Complex(0,0),new Complex(1,0),new Complex(1,0),new Complex(-1,0),new Complex(0,0)},
                        {new Complex(0,0),new Complex(0,0),new Complex(r3,0),new Complex(0,0),new Complex(r5,0),new Complex(0,-xc2)},
                        {new Complex(0,0),new Complex(-r2,0),new Complex(0,0),new Complex(0,0),new Complex(0,0),new Complex(0,xc2)},
                        };
		
		Complex Y[]    = {new Complex(0,0),new Complex(3/Math.sqrt(2),0),new Complex(0,0),new Complex(0,0),new Complex(0,0),new Complex(0,0)};
		Complex Ycopy[]    = {new Complex(0,0),new Complex(3/Math.sqrt(2),0),new Complex(0,0),new Complex(0,0),new Complex(0,0),new Complex(0,0)};
		
		
		
		Complex erg[] = test.solvec(A, Y);
		Complex u2 = erg[1].mul(new Complex (r2,0));
		double u2abs = u2.abs();
		double u2phi = u2.phi();
		double i3abs = erg[2].abs()*1E9;
		double i3phi = erg[2].phi();
		System.out.println ();
		System.out.printf ("\t%3.0fHz \t\t\t%5.3fV\t\t\t%5.3f°\t\t\t%5.3fnA \t\t\t%5.3f°",f,u2abs,u2phi,i3abs,i3phi);
		System.out.println();
		System.out.println(test.complexfehler(Acopy,erg,Ycopy,1E-9));
		
		
		
		
		}

	}

}

//{new Complex(-1,-1),new Complex(1,1),new Complex(0,0),new Complex(-1,-1),new Complex(1,1),new Complex(1,1)},










