package complex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexTest {

	public Complex neuComplex () {
		double randomRe = Math.random()*100 - 50;
		double randomIm = Math.random()*100 - 50;
		return new Complex(randomRe,randomIm);
	}
	
	@Test
	  public void test_addcomplex() {
		Complex test  = neuComplex();
	    Complex test2 = neuComplex();
	    double re = test.getRe()+test2.getRe();
	    double im = test.getIm()+test2.getIm();
	    Complex result = test.add(test2);
	    assertEquals(re,result.getRe(),1E-9);
	    assertEquals(im,result.getIm(),1E-9);
	  }
	
	@Test
	  public void test_mulcomplex() {
		Complex test  = neuComplex();
	    Complex test2 = neuComplex();
	    double im = test.getRe()*test2.getIm()+test2.getRe()*test.getIm();
	    double re = test.getRe()*test2.getRe() - test.getIm()*test2.getIm();
	    Complex result = test.mul(test2);
	    assertEquals(re,result.getRe(),1E-9);
	    assertEquals(im,result.getIm(),1E-9);
	  }
	
	@Test
	  public void test_divcomplex() {
		Complex test  = neuComplex();
	    Complex test2 = neuComplex();
	    Complex temp  = test.mul(test2.conj());
		double quad = (test2.getRe()*test2.getRe())+(test2.getIm()*test2.getIm());
	    double re = temp.getRe() / quad;
	    double im = temp.getIm() / quad;
	    Complex result = test.div(test2);
	    assertEquals(re,result.getRe(),1E-9);
	    assertEquals(im,result.getIm(),1E-9);
	  }

}
