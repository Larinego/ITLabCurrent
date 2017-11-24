package com.larinego;

public class Test
{
    public static int count(int[] a){
        int i = 0;
        int aLength = a.length;
        int sumLeft = 0;
        int sumrRight = 0;
        for(int n = 0; n < aLength; n++){
            sumrRight += a[n];
        }
        if(aLength > 2){
            while(i < aLength){
                if(i != 0) {
                    sumLeft += a[i - 1];
                }
                sumrRight -= a[i];
                i++;
                if(sumLeft == sumrRight && i != 0){
                    return i;
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {

        int[] a = {0,1,1,3,4,1,1,3};

        System.out.println(count(a));

        int[] b = {0,0};

        System.out.println(count(b));

        int[] c = {};

        System.out.println(count(c));

        int[] d = {0,1,1,2,2,1,0,6};

        System.out.println(count(d));


    }
}
