package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static List<WaterJugAction> list = new ArrayList<>();
    static List<WaterJugAction> list2 = new ArrayList<>();
    static List<WaterJugAction> listhasil = new ArrayList<>();

    public static List<WaterJugAction> simulateWaterJug(int jug1, int jug2, int target) {
        // TODO, simulate the smallest number of action to do the water jug problem
        // below is stub, replace with your implementation!

        list.clear();
        list2.clear();

        /*list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));
        list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));*/

        minimalLangkahWaterJug(jug1,jug2,target);
        return listhasil;
    }

    //Cek solusi ada atau tidak
    public static int gcd(int a, int b)
    {
        if (b==0)
            return a;
        return gcd(b, a%b);
    }

    //Cek minimum langkah
    public static int minimalLangkahWaterJug(int MaxJug1, int MaxJug2, int target) {

        int hasil = -1;
        int solusi = 1;
        if(MaxJug2 > MaxJug1 && target > MaxJug2){
            System.out.println("=============");  // Output user input
            System.out.println("Tidak Ada Solusi");  // Output user input
            System.out.println("=============");  // Output user input
            solusi = 0;
        }
        if(MaxJug1 > MaxJug2 && target > MaxJug1){
            System.out.println("=============");  // Output user input
            System.out.println("Tidak Ada Solusi");  // Output user input
            System.out.println("=============");  // Output user input
            solusi = 0;
        }
        if ((target % gcd(MaxJug2,MaxJug1)) != 0){
            System.out.println("=============");  // Output user input
            System.out.println("Tidak Ada Solusi");  // Output user input
            System.out.println("=============");  // Output user input
            solusi=0;
        }
        if(solusi==1){
            int pour = 0;
            int Jug1 = 0;
            int Jug2 = 0;
            int langkah = 0;
            System.out.println("=============");  // Output user input
            System.out.println("Ada Solusi");  // Output user input
            System.out.println("=============");  // Output user input
            System.out.println("=============");  // Output user input
            System.out.println("Cara 1");  // Output user input
            System.out.println("=============");  // Output user input
            while(Jug2 != target){
                if(Jug1==0){
                    langkah++;
                    System.out.println(langkah+". Jug1 Fill in Water.");  // Output user input
                    Jug1+=MaxJug1;
                    System.out.println("=============");  // Output user input
                    System.out.println("Jug1 :"+Jug1);  // Output user input
                    System.out.println("Jug2 :"+Jug2);  // Output user input
                    System.out.println("=============");  // Output user input
                    list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));

                    if(Jug1==target){
                        break;
                    }

                    if(Jug2==MaxJug2){
                        Jug2 = 0;
                        langkah++;
                        list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));
                        System.out.println(langkah+". Empty Jug2");  // Output user input
                        System.out.println("=============");  // Output user input
                        System.out.println("Jug1 :"+Jug1);  // Output user input
                        System.out.println("Jug2 :"+Jug2);  // Output user input
                        System.out.println("=============");  // Output user input
                    }
                    langkah++;
                    list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
                    System.out.println(langkah+". Jug1 Pour to Jug2");  // Output user input
                    Jug2+=Jug1;
                    pour+=1;
                    Jug1-=Jug1;

                    if(Jug2 > MaxJug2){
                        Jug1 = Jug2-MaxJug2;
                        Jug2 = MaxJug2;
                        //	pour+=1;

                    }
                    else{
                    }
                    System.out.println("=============");  // Output user input
                    System.out.println("Jug1 :"+Jug1);  // Output user input
                    System.out.println("Jug2 :"+Jug2);  // Output user input
                    System.out.println("=============");  // Output user input

                    if(Jug1==target){
                        break;
                    }
                }
                else{
                    if(Jug2==MaxJug2){
                        Jug2 = 0;
                        langkah++;
                        list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));
                        System.out.println(langkah+". Empty Jug2");  // Output user input
                        System.out.println("=============");  // Output user input
                        System.out.println("Jug1 :"+Jug1);  // Output user input
                        System.out.println("Jug2 :"+Jug2);  // Output user input
                        System.out.println("=============");  // Output user input

                    }
                    langkah++;
                    list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));
                    System.out.println(langkah+". Jug1 Pour to Jug2");  // Output user input
                    Jug2+=Jug1;
                    pour+=1;
                    Jug1-=Jug1;
                    if(Jug2 > MaxJug2){
                        Jug1 = Jug2-MaxJug2;
                        Jug2 = MaxJug2;
                        //	pour+=1;

                    }
                    else{
                    }
                    System.out.println("=============");  // Output user input
                    System.out.println("Jug1 :"+Jug1);  // Output user input
                    System.out.println("Jug2 :"+Jug2);  // Output user input
                    System.out.println("=============");  // Output user input

                    if(Jug1==target){
                        break;
                    }
                }
            }

            int pour2 = 0;
            Jug1 = 0;
            Jug2 = 0;
            int langkah2 = 0;
            System.out.println("=============");  // Output user input
            System.out.println("Cara 2");  // Output user input
            System.out.println("=============");  // Output user input
            while(Jug2 != target){
                if(Jug2==0){
                    langkah2++;
                    list2.add(new WaterJugAction(WaterJugActionEnum.FILL, 2));
                    System.out.println(langkah2+". Jug2 Fill in Water.");  // Output user input
                    Jug2+=MaxJug2;
                    System.out.println("=============");  // Output user input
                    System.out.println("Jug1 :"+Jug1);  // Output user input
                    System.out.println("Jug2 :"+Jug2);  // Output user input
                    System.out.println("=============");  // Output user input

                    if(Jug1==MaxJug1){
                        Jug1 = 0;
                        langkah2++;
                        list2.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 1));
                        System.out.println(langkah2+". Empty Jug1");  // Output user input
                        System.out.println("=============");  // Output user input
                        System.out.println("Jug1 :"+Jug1);  // Output user input
                        System.out.println("Jug2 :"+Jug2);  // Output user input
                        System.out.println("=============");  // Output user input
                    }
                    if(Jug1==target){
                        break;
                    }
                    langkah2++;
                    list2.add(new WaterJugAction(WaterJugActionEnum.POUR, 1));
                    System.out.println(langkah2+". Jug2 Pour to Jug1");  // Output user input
                    Jug1+=Jug2;
                    pour2+=1;
                    Jug2-=Jug2;

                    if(Jug1 > MaxJug1){
                        Jug2 = Jug1-MaxJug1;
                        Jug1 = MaxJug1;
                        //pour2+=1;

                    }
                    else{
                    }
                    System.out.println("=============");  // Output user input
                    System.out.println("Jug1 :"+Jug1);  // Output user input
                    System.out.println("Jug2 :"+Jug2);  // Output user input
                    System.out.println("=============");  // Output user input

                    if(Jug1==target){
                        break;
                    }
                }
                else{
                    if(Jug1==MaxJug1){
                        Jug1 = 0;
                        langkah2++;
                        list2.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 1));
                        System.out.println(langkah2+". Empty Jug1");  // Output user input
                        System.out.println("=============");  // Output user input
                        System.out.println("Jug1 :"+Jug1);  // Output user input
                        System.out.println("Jug2 :"+Jug2);  // Output user input
                        System.out.println("=============");  // Output user input

                    }
                    if(Jug1==target){
                        break;
                    }
                    langkah2++;
                    list2.add(new WaterJugAction(WaterJugActionEnum.POUR, 1));
                    System.out.println(langkah2+". Jug2 Pour to Jug1");  // Output user input
                    Jug1+=Jug2;
                    pour2+=1;
                    Jug2-=Jug2;
                    if(Jug1 > MaxJug1){
                        Jug2 = Jug1-MaxJug1;
                        Jug1 = MaxJug1;
                        //pour2+=1;

                    }
                    else{
                    }
                    System.out.println("=============");  // Output user input
                    System.out.println("Jug1 :"+Jug1);  // Output user input
                    System.out.println("Jug2 :"+Jug2);  // Output user input
                    System.out.println("=============");  // Output user input

                    if(Jug1==target){
                        break;
                    }
                }
            }
            //HASIL LIST DIRETURN
            if(list.size()<=list2.size())listhasil=list;
            else listhasil=list2;

            System.out.println("Jumlah Langkah Cara ke-1 : " + langkah);  // Output user input
            System.out.println("Jumlah Langkah Cara ke-2 : " + langkah2);  // Output user input
            System.out.println("=============");  // Output user input
            if(langkah<langkah2){
                System.out.println("Minimum Langkah : " + langkah);  // Output user input
                hasil = langkah;
            }
            else{
                System.out.println("Minimum Langkah : " + langkah2);  // Output user input
                hasil = langkah2;
            }
            System.out.println("=============");  // Output user input
        }

        return hasil;
    }
}
