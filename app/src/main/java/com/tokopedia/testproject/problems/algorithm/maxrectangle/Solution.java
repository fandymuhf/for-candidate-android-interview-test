package com.tokopedia.testproject.problems.algorithm.maxrectangle;


import android.app.Application;
import android.util.Log;
import android.widget.TextView;

import java.util.Deque;
import java.util.LinkedList;


public class Solution {
    public static int maxRect(int[][] matrix) {
        // TODO, return the largest area containing 1's, given the 2D array of 0s and 1s
        // below is stub

        int kanan = 0;
        int maxkanan = 0;
        int minkanan = 0;
        int hasil = 0;
        int posisibawah = 1;
        //int posisiawal = 0;
        int posisiakhir = 0;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==1){ //ketemu val 1 nya

                    posisiakhir = j;
                    posisibawah = 0;
                    minkanan=0;
                    for(int k=i;k<matrix.length;k++) { //cek baris
                        kanan = 0;

                        if (matrix[k][j] == 1 && kanan==0) {
                            for (int l = j; l < matrix[i].length; l++) { //cek kolom
                                if (matrix[k][l] == 1) {
                                    kanan++;
                                    if(maxkanan<kanan)maxkanan=kanan;
                                } else {
                                    if(maxkanan<kanan)maxkanan=kanan;
                                    posisiakhir=l-1;
                                    break;
                                }

                                posisiakhir=l;
                            }
                            //bandingkan minkanan per row
                            if(minkanan==0)minkanan=kanan;
                            else if(minkanan!=0 && minkanan>kanan)minkanan=kanan;

                            //cek hasil output
                            //if(hasil<maxkanan)hasil=maxkanan;
                            //if(hasil<minkanan*(posisibawah+1))hasil=minkanan*(posisibawah+1);

                            //Log.d("MATRIX AREA "+i+": pos j:",j+" , min kanan: "+minkanan+" , hasil: "+hasil);

                        }else if(matrix[k][j] != 1){ //cek bawah != 0

                            break;
                        }
                        posisibawah++;


                    }
                    if(hasil<minkanan*(posisibawah))hasil=minkanan*(posisibawah);
                    Log.d("MATRIX AREA "+i+": pos j:",j+" , pos akhir: "+posisiakhir+" , min kanan: "+minkanan+" , pos bwh : "+posisibawah);


                }
            }
        }


        //Log.d("MATRIX 1:", matrix[0][0]+" "+matrix[0][1]+" "+matrix[0][2]+" "+matrix[0][3]+" "+matrix[0][4]);
        //Log.d("MATRIX 2:", matrix[1][0]+" "+matrix[1][1]+" "+matrix[1][2]+" "+matrix[1][3]+" "+matrix[1][4]);
        System.out.println("Output: "+hasil);
        return hasil;
    }

}
