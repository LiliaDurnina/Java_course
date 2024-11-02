public class Matrix {
    private int n, m;
    private Complex matrix[][];


    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Невозможно создать матрицу");
        }
        this.n = n;
        this.m = m;
        matrix = new Complex[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = new Complex();
            }
        }
    }

    public Matrix(int n, int m, Complex matrix[][]) {
        if ((n <= 0) || (m <= 0) || (n != matrix.length) || (m != matrix[0].length)) {
            throw new IllegalArgumentException("Невозможно создать матрицу");
        }
        this.n = n;
        this.m = m;
        this.matrix = matrix;
    }

    public Matrix(Complex matrix[][]) {
        if (matrix!=null && matrix.length>0 && matrix[0].length>0) {
            this.n = matrix.length;
            this.m = matrix[0].length;
            this.matrix = matrix;
        }
        else{
            throw new IllegalArgumentException("Невозможно создать матрицу");

        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Complex[][] getMatrix() {
        return matrix;
    }

    public void setElement(int i, int j, Complex element) {
        if ((i < this.n) && (j < this.m)) {
            this.matrix[i][j] = element;
        }
        else{
            throw new IllegalArgumentException("Обращение к несуществующему элементу");
        }
    }
    public Complex getElement(int i, int j) {
        if ((i < this.n) && (j < this.m)) {
            return this.matrix[i][j];
        }
        else{
            throw new IllegalArgumentException("Обращение к несуществующему элементу");
        }
    }

    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j].printComplex();
                if (j!=m-1){
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    //транспонирование
    public Matrix transpose() {
        Matrix matrix_transpose = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix_transpose.setElement(i, j, matrix[j][i]);
            }
        }
        return matrix_transpose;
    }

    //сравнение для суммы/разности
    private boolean compare(Matrix second) {
        if (second == null) {
            throw new IllegalArgumentException("Матрица не может быть null");
        }
        return n == second.getN() && m == second.getM();
    }

    //сравнение для Умножения
    private boolean compareMultiply(Matrix second) {
        if (second == null) {
            throw new IllegalArgumentException("Матрица не может быть null");
        }
        return this.m == second.getN();
    }


    public Matrix add(Matrix second) {
        if (!this.compare(second)) {
            throw new IllegalArgumentException("Матрица неподходящего размера");
        }
        Matrix matrix_add = new Matrix(this.n, this.m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix_add.setElement(i, j, (this.matrix[i][j].add(second.getMatrix()[i][j])));
            }
        }
        return matrix_add;
    }

    public Matrix subtract(Matrix second) {
        if (!this.compare(second)) {
            throw new IllegalArgumentException("Матрица неподходящего размера");
        }
        Matrix matrix_subtract = new Matrix(this.n, this.m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix_subtract.setElement(i, j, (this.matrix[i][j].subtract(second.getMatrix()[i][j])));
            }
        }
        return matrix_subtract;
    }

    //умножение на матрицу
    public Matrix multiply(Matrix second) {
        if (!this.compareMultiply(second)) {
            throw new IllegalArgumentException("Матрица неподходящего размера");
        }
        Matrix matrixMultiply = new Matrix(this.n, second.getM());
        for (int z = 0; z < second.getM(); z++) {
            for (int i = 0; i < n; i++) {
                Complex cur = new Complex(0, 0);
                for (int j = 0; j < m; j++) {
                    cur = cur.add(this.matrix[i][j].multiply(second.getMatrix()[j][z]));
                }
                matrixMultiply.setElement(i, z, cur);
            }
        }
        return matrixMultiply;
    }

    //умножение на число
    public Matrix multiply(Complex second) {
        Matrix matrixMultiply = new Matrix(this.n, this.m);
        for (int i=0;i<this.n;i++){
            for (int j=0;j<this.m;j++){
                matrixMultiply.setElement(i, j, (this.matrix[i][j].multiply(second)));
            }
        }
        return matrixMultiply;
    }


    public Matrix divide(Matrix second) {
        second = second.inverse();
        return this.multiply(second);

    }

    public Complex determinant() {
        if (this.n != this.m) {
            throw new IllegalArgumentException("Невозомжно вычислить det");
        }
        return this.detRecursion();
    }

    //составлене матрицы миноров
    private Matrix matrixForDet(int n, int m) {
        int size = this.getN() - 1;
        Matrix newMatrix = new Matrix(size, size);
        int nNew = 0;
        for (int i = 0; i < size + 1; i++) {
            if (i != n) {
                int mNew = 0;
                for (int j = 0; j < size + 1; j++) {
                    if (j != m) {
                        newMatrix.setElement(nNew, mNew, this.matrix[i][j]);
                        mNew++;
                    }
                }
                nNew++;
            }
        }
        return newMatrix;
    }

    //функция для высчета определителя
    private Complex detRecursion() {
        if (this.n == 1) {
            return this.matrix[0][0];
        }
        if (this.n == 2) {
            return this.matrix[0][0].multiply(this.matrix[1][1]).subtract(this.matrix[0][1].multiply(this.matrix[1][0]));
        }
        Complex det = new Complex(0, 0);
        for (int i = 0; i < this.n; i++) {
            Matrix minor = this.matrixForDet(i, 0);
            if ((i + 2) % 2 == 0) {
                det = det.add(this.matrix[i][0].multiply(minor.detRecursion()));

            } else {
                det = det.subtract(this.matrix[i][0].multiply(minor.detRecursion()));
            }
        }
        return det;
    }

    //составление матрицы миноров для обратной матрицы
    private Matrix matrixMinor(){
        Matrix matrixMinor = new Matrix(this.n, this.m);
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                Matrix curr=matrixForDet(i,j);
                Complex det=new Complex();
                det=curr.determinant();
                if ((i+j)%2!=0) {
                    det=det.multiply(new Complex(-1));}

                matrixMinor.setElement(i, j, det);
            }
        }
        return matrixMinor;
    }

    //обратная матрица
    public Matrix inverse (){
        Complex det = this.determinant();
        if ((this.n != this.m) || (det.equals(new Complex(0,0)))) {
            throw new IllegalArgumentException("Не существует обратной матрицы");
        }
        Matrix matrixInverse = this.matrixMinor().transpose();
        det=new Complex(1).divide(det);
        return matrixInverse.multiply(det);
    }
}






