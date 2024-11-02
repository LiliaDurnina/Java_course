public class Main {
    public static void main(String[] args) {

        Complex a1=new Complex();
        Complex a2=new Complex(3);
        Complex a3=new Complex(2,3);
        System.out.print("a1:  ");
        a1.printComplex();
        System.out.print("\na2:  ");
        a2.printComplex();
        System.out.print("\na3:  ");
        a3.printComplex();
        System.out.print("\na1+a2= ");
        a2.add(a1).printComplex();
        System.out.print("\na3-a2= ");
        a3.subtract(a2).printComplex();
        System.out.print("\na3*a2= ");
        a3.multiply(a2).printComplex();
        //a2=a2.divide(a1); //exception - программа завершается
        System.out.print("\na2/a3= ");
        a2.divide(a3).printComplex();
        System.out.print("\na2/a2= ");
        a2.divide(a2).printComplex();
        //a2.add(null); // exception - программа завершается
        Matrix m1=new Matrix(2,3);
        System.out.print("\nm1: ");
        m1.printMatrix();
        Complex a[][]= {{new Complex(1),new Complex(2)},{new Complex(1),new Complex(2)},{new Complex(1),new Complex(2)} };

        //Matrix m2=new Matrix(3,3, a); // exception - программа завершается
        //Matrix m2=new Matrix(3,-3); // exception - программа завершается
        Matrix m2=new Matrix(3,2, a);
        System.out.print("\nm2: ");
        m2.printMatrix();
        Matrix m3=new Matrix( a);
        System.out.print("\nm3: ");
        m3.printMatrix();

        System.out.print("\nm3+m2: ");
        m3.add(m2).printMatrix();

        //m3.subtract(m1).printMatrix(); // exception - программа завершается
        System.out.print("\nm2-m3: ");
        m2.subtract(m3).printMatrix();

        System.out.print("\nm2*m1: ");
        m2.multiply(m1).printMatrix();
        System.out.print("\nm1*m2: ");
        m1.multiply(m2).printMatrix();

        System.out.print("\nm2-транспонирование: ");
        m2.transpose().printMatrix();

        //System.out.print("\nm2 - det: ");
        //m2.determinant().printComplex(); // exception - программа завершается

        m3=new Matrix (4,4);
        m3.setElement(0,0,new Complex(1));
        m3.setElement(0,1,new Complex(2));
        m3.setElement(0,2,new Complex(3));
        m3.setElement(0,3,new Complex(4));
        m3.setElement(1,0,new Complex(5));
        m3.setElement(1,1,new Complex(6));
        m3.setElement(1,2,new Complex(7));
        m3.setElement(1,3,new Complex(8));
        m3.setElement(2,0,new Complex(9));
        m3.setElement(2,1,new Complex(10));
        m3.setElement(2,2,new Complex(11));
        m3.setElement(2,3,new Complex(12));
        m3.setElement(3,0,new Complex(13));
        m3.setElement(3,1,new Complex(14));
        m3.setElement(3,2,new Complex(15));
        m3.setElement(3,3,new Complex(16));
        //m3.setElement(3,4,new Complex(17));// exception - программа завершается
        System.out.print("\nm3: ");
        m3.printMatrix();
        System.out.print("\nm3 - det: ");
        m3.determinant().printComplex();

        //System.out.print("\nm3 - обратная: ");
        //m3.inverse();// exception - программа завершается (det=0)
        m3.setElement(3,3,new Complex(9));
        m3.setElement(1,1,new Complex(9));
        System.out.print("\nm3 - new det: ");

        m3.determinant().printComplex();

        System.out.print("\nm3 - обратная: ");
        m3.inverse().printMatrix();

        m2=new Matrix(2,2);
        m2.setElement(0,0,new Complex(1));
        m2.setElement(0,1,new Complex(3));
        m2.setElement(1,0,new Complex(2));
        m2.setElement(1,1,new Complex(5));
        System.out.print("\nm2: ");
        m2.printMatrix();
        System.out.print("\nm2/m2=единичная матрица: ");
        m2.divide(m2).printMatrix();

























    }
}