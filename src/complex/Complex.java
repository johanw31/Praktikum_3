package complex;


public class Complex {
	private double re,im;
	
	/**
	* get re of complex
	* @return re of complex as double
	*/
	public double getRe() {
		return re;
	}
	public void setRe(double re) {
		this.re = re;
	}
	/**
	* get re of complex
	* @return im of complex as double
	*/
	public double getIm() {
		return im;
	}
	public void setIm(double im) {
		this.im = im;
	}
	public Complex(double x, double y) {
	re = x;
	im = y;
	}
	/**
	* Calculate this plus that
	* @param that the Complex argument
	* @return (this + that) as new Complex
	*/
	public Complex add(final Complex that) {
	return new Complex(re + that.re, im + that.im);
	}
	/**
	* Calculate this minus that
	* @param that the Complex argument
	* @return (this - that) as new Complex
	*/
	public Complex sub(final Complex that) {
	return new Complex(re - that.re, im - that.im);
	}
	/**
	* Calculate this multiplied that
	* @param that the Complex argument
	* @return result as new Complex
	*/
	public Complex mul(final Complex that) {
		double re = this.re*that.re+(-1)*(this.im*that.im);
		double im = this.re*that.im+this.im*that.re;
		return new Complex (re,im);
	}
	/**
	* Calculate this divided that
	* @param that the Complex argument
	* @return (this / that) as Complex
	*/
	public Complex div(final Complex that) {
		Complex temp = mul(that.conj());
		double quad = (that.re*that.re)+(that.im*that.im);
	    double re = temp.getRe() / quad;
	    double im = temp.getIm() / quad;
	return new Complex (re,im);	
	}
	/**
	* Calculate the angle of the complex object
	* @return angle in degrees as double
	*/
	public double phi() {
		if (this.re > 0) {
		return Math.toDegrees(Math.atan(im/re));
		}
		else if (this.re < 0 & this.im >= 0){
			return 180 - Math.toDegrees(Math.atan(im/Math.abs(re)));
		}
		else if (this.re < 0 & this.im < 0){
			double test = Math.toDegrees(Math.atan(im/Math.abs(re)));
			return -180 - test;
		}
		else {
			throw new IllegalArgumentException ("Winkelfehler");
		}
	}
	/**
	* Calculate the conjugate complex of the object
	* @return conj as Complex
	*/
	public Complex conj() {
	return new Complex(re,-im);
	}
	/**
	* Calculate re^2 + im^2
	* @return result as double
	*/
	public double abs2() {
	return re*re + im*im;
	}
	/**
	* Calculate the absolute value of the complex object
	* @return result as double
	*/
	public double abs() {
	return Math.sqrt(abs2());
}

}

