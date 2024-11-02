public class Complex {
    private double real, imag;

    public Complex(){
        this(0,0);
    }

    public Complex(double real){
        this(real,0);
    }

    public Complex (double real, double imag){
        this.real=real;
        this.imag=imag;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void printComplex(){
        System.out.print(real + "+" + imag + "*i");
    }

    public Complex add(Complex z){
        if (z==null){
            throw new IllegalArgumentException("Переданное комплексное число не может быть null");
        }
        return new Complex (this.real+z.real, this.imag+z.imag);
    }
    public Complex subtract(Complex z){
        if (z==null){
            throw new IllegalArgumentException("Переданное комплексное число не может быть null");
        }
        return new Complex (this.real-z.real, this.imag-z.imag);
    }
    public Complex multiply(Complex z){
        if (z==null){
            throw new IllegalArgumentException("Переданное комплексное число не может быть null");
        }
        return new Complex((this.real*z.real - this.imag*z.imag), (this.real*z.imag+this.imag*z.real));
    }
    public Complex divide(Complex z){
        if (z==null){
            throw new IllegalArgumentException("Переданное комплексное число не может быть null");
        }
        if ((z.real*z.real+z.imag*z.imag)==0){
            throw new IllegalArgumentException("Деление выполнить невозможно");

        }
        return new Complex((this.real*z.real+this.imag*z.imag)/(z.real*z.real+z.imag*z.imag), (this.imag*z.real-this.real*z.imag)/(z.real*z.real+z.imag*z.imag));
    }

    //проверка на равернство комплексных чисел
    public boolean equals(Complex z){
        if (z==null){
            throw new IllegalArgumentException("Переданное комплексное число не может быть null");
        }
        return (this.real==z.real && this.imag==z.imag);
    }

}
