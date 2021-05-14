package com.tester.cases;

import java.util.Scanner;

public class Matrix {
    public void snake(int n)
    {
        int[][] data = new int[n][n];
        int intA;
        int value = 1;
        if(n % 2 != 0)       //奇数循环n/2+1次
        {
            intA = n/2 + 1;
        }else{
            intA = n/2;      //偶数循环n/2次
        }

        for(int i = 0; i < intA; i++)  //从外向里循环
        {
            //从左到右循环
            for(int j = i; j < n - i; j++)
            {
                data[i][j] = value++;
            }

            //右列从上向下循环
            for(int k = i + 1; k < n - i; k++)
            {
                data[k][n-i-1] = value++;
            }

            //从右到左
            for(int l = n-i-2; l >= i; l--)
            {
                data[n-i-1][l] = value++;
            }

            //从下到上
            for(int m = n - i - 2; m > i; m--)
            {
                data[m][i] = value++;
            }
        }

        for(int i = 0; i < n; i++) //遍历输出
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        Matrix sn = new Matrix();
        while (sin.hasNextInt()) {
            int n = sin.nextInt();
            sn.snake(n);
        }
    }

}
