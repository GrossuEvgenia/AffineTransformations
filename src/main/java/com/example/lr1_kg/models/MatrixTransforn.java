package com.example.lr1_kg.models;

public class MatrixTransforn {


  public double [][] diagonalMatrix()
    {
        double[][] matrix = new double[4][4];
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix.length;j++)
            {
                if(i==j)
                    matrix[i][j]=1;
                else
                    matrix[i][j]=0;


            }

        }
        return  matrix;
    }


    public double [][] transform(double cofX, double cofY, double cofZ)
    {

        double [][] trans = diagonalMatrix();
        trans[3][0]=cofX;
        trans[3][1]=cofY;
        trans[3][2]=cofZ;

        return trans;

    }

    public double [][] dilation(double cofX, double cofY, double cofZ)
    {

        double [][] delate = diagonalMatrix();
        delate[0][0]=cofX;
        delate[1][1]=cofY;
        delate[2][2]=cofZ;

        return delate;

    }
    public double [][] rotationX(double corner)
    {

        double [][] rotate = diagonalMatrix();
        rotate[1][1]=Math.cos(corner);
        rotate[1][2]=Math.sin(corner);
        rotate[2][1]=-Math.sin(corner);
        rotate[2][2]=Math.cos(corner);

        return rotate;

    }
    public double [][] rotationY(double corner)
    {

        double [][] rotate =diagonalMatrix();
        rotate[0][0]=Math.cos(corner);
        rotate[0][2]=-Math.sin(corner);
        rotate[2][0]=Math.sin(corner);
        rotate[2][2]=Math.cos(corner);

        return rotate;

    }
    public double [][] rotationZ(double corner)
    {

        double [][] rotate = diagonalMatrix();

        rotate[0][0]=Math.cos(corner);
        rotate[0][1]=Math.sin(corner);
        rotate[1][0]=-Math.sin(corner);
        rotate[1][1]=Math.cos(corner);

        return rotate;

    }
    public  double[][] multiplay(double [][]matr1, double [][]matr2)
    {

        double[][] newMatrix = new double[matr1.length][matr1.length];
        for(int i=0;i<matr1.length;i++)
        {
            for(int k=0;k<matr1.length;k++)
            {
                for(int j=0;j<matr1.length;j++)
                {

                    newMatrix[i][k]+=matr1[i][j]*matr2[j][k];
                }
            }
        }
        return  newMatrix;
    }
}
