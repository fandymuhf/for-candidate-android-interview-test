package com.tokopedia.testproject.problems.algorithm.waterJug;

public class Solution {

    //Cek solusi ada atau tidak
    public static int gcd(int a, int b)
    {
        if (b==0)
            return a;
        return gcd(b, a%b);
    }

    public static int minimalPourWaterJug(int MaxJug1, int MaxJug2, int target) {
        // TODO, return the smallest number of POUR action to do the water jug problem
        // below is stub, replace with your implementation!

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

                        if(Jug1==target){
                            break;
                        }

                        if(Jug2==MaxJug2){
                            Jug2 = 0;
                            langkah++;
                            System.out.println(langkah+". Empty Jug2");  // Output user input
                            System.out.println("=============");  // Output user input
                            System.out.println("Jug1 :"+Jug1);  // Output user input
                            System.out.println("Jug2 :"+Jug2);  // Output user input
                            System.out.println("=============");  // Output user input
                        }
                        langkah++;
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
                            System.out.println(langkah+". Empty Jug2");  // Output user input
                            System.out.println("=============");  // Output user input
                            System.out.println("Jug1 :"+Jug1);  // Output user input
                            System.out.println("Jug2 :"+Jug2);  // Output user input
                            System.out.println("=============");  // Output user input

                        }
                        langkah++;
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
                langkah = 0;
                System.out.println("=============");  // Output user input
                System.out.println("Cara 2");  // Output user input
                System.out.println("=============");  // Output user input
                while(Jug2 != target){
                    if(Jug2==0){
                        langkah++;
                        System.out.println(langkah+". Jug2 Fill in Water.");  // Output user input
                        Jug2+=MaxJug2;
                        System.out.println("=============");  // Output user input
                        System.out.println("Jug1 :"+Jug1);  // Output user input
                        System.out.println("Jug2 :"+Jug2);  // Output user input
                        System.out.println("=============");  // Output user input

                        if(Jug1==MaxJug1){
                            Jug1 = 0;
                            langkah++;
                            System.out.println(langkah+". Empty Jug1");  // Output user input
                            System.out.println("=============");  // Output user input
                            System.out.println("Jug1 :"+Jug1);  // Output user input
                            System.out.println("Jug2 :"+Jug2);  // Output user input
                            System.out.println("=============");  // Output user input
                        }
                        if(Jug1==target){
                            break;
                        }
                        langkah++;
                        System.out.println(langkah+". Jug2 Pour to Jug1");  // Output user input
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
                            langkah++;
                            System.out.println(langkah+". Empty Jug1");  // Output user input
                            System.out.println("=============");  // Output user input
                            System.out.println("Jug1 :"+Jug1);  // Output user input
                            System.out.println("Jug2 :"+Jug2);  // Output user input
                            System.out.println("=============");  // Output user input

                        }
                        if(Jug1==target){
                            break;
                        }
                        langkah++;
                        System.out.println(langkah+". Jug2 Pour to Jug1");  // Output user input
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
                System.out.println("Jumlah Pour Cara ke-1 : " + pour);  // Output user input
                System.out.println("Jumlah Pour Cara ke-2 : " + pour2);  // Output user input
                System.out.println("=============");  // Output user input
                if(pour<pour2){
                    System.out.println("Minimum Pour : " + pour);  // Output user input
                    hasil = pour;
                }
                else{
                    System.out.println("Minimum Pour : " + pour2);  // Output user input
                    hasil = pour2;
                }
                System.out.println("=============");  // Output user input
            }

        System.out.println("Output: "+hasil);
        return hasil;
    }
}
