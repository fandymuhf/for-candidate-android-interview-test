package com.tokopedia.testproject.problems.androidView.slidingImagePuzzle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tokopedia.testproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlidingImageGameActivity extends AppCompatActivity {
    public static final String X_IMAGE_URL = "x_image_url";
    public static final int GRID_NO = 4;
    private String imageUrl;
    ImageView[][] imageViews = new ImageView[4][4];
    private GridLayout gridLayout;
    Papangame[][] papangame = new Papangame[4][4];
    List<Integer> randomcol = new ArrayList<>();
    List<Integer> randomrow = new ArrayList<>();
    int youwin = 0;
    int[] mytime = {0};

    public static Intent getIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, SlidingImageGameActivity.class);
        intent.putExtra(X_IMAGE_URL, imageUrl);
        return intent;
    }

    //Class data papan
    public class Papangame{
        int row;
        int col;

        public void setData(int x,int y){
            row=x;
            col=y;
        }
        public int getCol(){
            return col;
        }
        public int getRow(){
            return row;
        }

    }

    //Image Click Listener
    public class ImageClickLIstener implements View.OnClickListener
    {
        int row;
        int col;
        List<Bitmap> bitmap;

        ImageClickLIstener(int x, int y, List<Bitmap> bitmapList)
        {
            this.row= x;
            this.col= y;
            this.bitmap = bitmapList;
        }

        @Override
        public void onClick(View v) {
            System.out.println("CLICKED: " + row +" "+col);
            BitmapDrawable drawable = (BitmapDrawable) imageViews[row][col].getDrawable();
            Bitmap bitmaptmp = drawable.getBitmap();

            //cek atas blank space
            if(row>0)
            if(papangame[row-1][col].row==3 && papangame[row-1][col].col==3) {
                imageViews[row][col].setImageDrawable(null);
                imageViews[row-1][col].setImageBitmap(bitmaptmp);
                papangame[row-1][col].row=papangame[row][col].row;
                papangame[row-1][col].col=papangame[row][col].col;
                papangame[row][col].row=3;
                papangame[row][col].col=3;
                System.out.println("KE ATAS");

                //Animate image to up
                TranslateAnimation animation = new TranslateAnimation(0, 0, 200, 0);
                animation.setDuration(500);
                animation.setFillAfter(false);

                imageViews[row-1][col].startAnimation(animation);
            }
            //cek bawah blank space
            if(row<3)
                if(papangame[row+1][col].row==3 && papangame[row+1][col].col==3) {
                    imageViews[row][col].setImageDrawable(null);
                    imageViews[row+1][col].setImageBitmap(bitmaptmp);
                    papangame[row+1][col].row=papangame[row][col].row;
                    papangame[row+1][col].col=papangame[row][col].col;
                    papangame[row][col].row=3;
                    papangame[row][col].col=3;
                    System.out.println("KE BAWAH");

                    //Animate image to down
                    TranslateAnimation animation = new TranslateAnimation(0, 0, -200, 0);
                    animation.setDuration(500);
                    animation.setFillAfter(false);

                    imageViews[row+1][col].startAnimation(animation);
                }
            //cek kanan blank space
            if(col<3)
            if(papangame[row][col+1].row==3 && papangame[row][col+1].col==3) {
                imageViews[row][col].setImageDrawable(null);
                imageViews[row][col+1].setImageBitmap(bitmaptmp);
                papangame[row][col+1].row=papangame[row][col].row;
                papangame[row][col+1].col=papangame[row][col].col;
                papangame[row][col].row=3;
                papangame[row][col].col=3;
                System.out.println("KE KANAN");

                //Animate image to right
                TranslateAnimation animation = new TranslateAnimation(-200, 0, 0, 0);
                animation.setDuration(500);
                animation.setFillAfter(false);

                imageViews[row][col+1].startAnimation(animation);
            }
            //cek kiri blank space
            if(col>0)
                if(papangame[row][col-1].row==3 && papangame[row][col-1].col==3) {
                    imageViews[row][col].setImageDrawable(null);
                    imageViews[row][col-1].setImageBitmap(bitmaptmp);
                    papangame[row][col-1].row=papangame[row][col].row;
                    papangame[row][col-1].col=papangame[row][col].col;
                    papangame[row][col].row=3;
                    papangame[row][col].col=3;
                    System.out.println("KE KIRI");

                    //Animate image to left
                    TranslateAnimation animation = new TranslateAnimation(200, 0, 0, 0);
                    animation.setDuration(500);
                    animation.setFillAfter(false);

                    imageViews[row][col-1].startAnimation(animation);
                }

            getdatapapan();
        }
    }

    private void getdatapapan() {
        youwin=0;
        // Get data papan game
        for (int i = 0; i < GRID_NO; i++) {
            System.out.println("Game: "+i);
            for (int j = 0; j < GRID_NO; j++) {

                System.out.println(papangame[i][j].getRow()+" "+papangame[i][j].getCol());
                if(papangame[i][j].getRow()==i && papangame[i][j].getCol()==j)youwin++;

            }
        }
        System.out.println("Winorno: "+youwin);
        if(youwin>15){
            TextView textmenang = findViewById(R.id.wincondition);
            textmenang.setText("Success\nYour Time: "+mytime[0]);
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUrl = getIntent().getStringExtra(X_IMAGE_URL);
        setContentView(R.layout.activity_sliding_image_game);
        gridLayout = findViewById(R.id.gridLayout);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < GRID_NO; i++) {
            for (int j = 0; j < GRID_NO; j++) {
                ImageView view = (ImageView) inflater.inflate(R.layout.item_image_sliding_image,
                        gridLayout, false);
                gridLayout.addView(view);

                imageViews[i][j] = view;
            }
        }

        Solution.sliceTo4x4(this, new Solution.onSuccessLoadBitmap() {
            @Override
            public void onSliceSuccess(List<Bitmap> bitmapList) {
                //TODO will randomize placement to grid. Note: the game must be solvable.
                //replace below implementation to your implementation.
                int counter = 0;

                for (int i=0;i<4;i++){
                    randomrow.add(i);
                    randomcol.add(i);
                }

                //RANDOM SOLVABLE
                int temprandom;
                int blankspace[] = {3,3}; //Lokasi blankspace
                int jumlahshuffle = 20; //Jumlah shuffle

                int bitmapSize = bitmapList.size();
                for (int i = 0; i < GRID_NO; i++) {

                    for (int j = 0; j < GRID_NO; j++) {
                        imageViews[i][j].setOnClickListener(new ImageClickLIstener(i,j, bitmapList));

                        if (counter >= bitmapSize) break;

                        if(counter<15)
                            imageViews[randomrow.get(i)][randomcol.get(j)].setImageBitmap(bitmapList.get(counter));

                        papangame[i][j] = new Papangame();
                        if(i==3 && j==3){
                            for (int k = 0; k < GRID_NO; k++) {
                                for (int l = 0; l < GRID_NO; l++) {
                                    papangame[randomrow.get(k)][randomcol.get(l)].setData(k,l);
                                    if(k==3 && l==3){
                                        for(i=0;i<jumlahshuffle;i++) {
                                            temprandom = (int)(Math.random() * 4) + 1;
                                            //KIRI
                                            if(temprandom==1 && blankspace[1]>0){
                                                BitmapDrawable drawable = (BitmapDrawable) imageViews[blankspace[0]][blankspace[1]-1].getDrawable();
                                                Bitmap bitmaptmp = drawable.getBitmap();
                                                imageViews[blankspace[0]][blankspace[1]-1].setImageDrawable(null);
                                                imageViews[blankspace[0]][blankspace[1]].setImageBitmap(bitmaptmp);
                                                papangame[blankspace[0]][blankspace[1]].row=papangame[blankspace[0]][blankspace[1]-1].row;
                                                papangame[blankspace[0]][blankspace[1]].col=papangame[blankspace[0]][blankspace[1]-1].col;
                                                papangame[blankspace[0]][blankspace[1]-1].row=3;
                                                papangame[blankspace[0]][blankspace[1]-1].col=3;
                                                blankspace[1]--;
                                                System.out.println("KE KIRI BLANKSPACE");
                                            }
                                            //KANAN
                                            else if(temprandom==2 && blankspace[1]<3){
                                                BitmapDrawable drawable = (BitmapDrawable) imageViews[blankspace[0]][blankspace[1]+1].getDrawable();
                                                Bitmap bitmaptmp = drawable.getBitmap();
                                                imageViews[blankspace[0]][blankspace[1]+1].setImageDrawable(null);
                                                imageViews[blankspace[0]][blankspace[1]].setImageBitmap(bitmaptmp);
                                                papangame[blankspace[0]][blankspace[1]].row=papangame[blankspace[0]][blankspace[1]+1].row;
                                                papangame[blankspace[0]][blankspace[1]].col=papangame[blankspace[0]][blankspace[1]+1].col;
                                                papangame[blankspace[0]][blankspace[1]+1].row=3;
                                                papangame[blankspace[0]][blankspace[1]+1].col=3;
                                                blankspace[1]++;
                                                System.out.println("KE KANAN BLANKSPACE");
                                            }
                                            //ATAS
                                            else if(temprandom==3 && blankspace[0]>0){
                                                BitmapDrawable drawable = (BitmapDrawable) imageViews[blankspace[0]-1][blankspace[1]].getDrawable();
                                                Bitmap bitmaptmp = drawable.getBitmap();
                                                imageViews[blankspace[0]-1][blankspace[1]].setImageDrawable(null);
                                                imageViews[blankspace[0]][blankspace[1]].setImageBitmap(bitmaptmp);
                                                papangame[blankspace[0]][blankspace[1]].row=papangame[blankspace[0]-1][blankspace[1]].row;
                                                papangame[blankspace[0]][blankspace[1]].col=papangame[blankspace[0]-1][blankspace[1]].col;
                                                papangame[blankspace[0]-1][blankspace[1]].row=3;
                                                papangame[blankspace[0]-1][blankspace[1]].col=3;
                                                blankspace[0]--;
                                                System.out.println("KE ATAS BLANKSPACE");
                                            }
                                            //BAWAH
                                            else if(temprandom==4 && blankspace[0]<3){
                                                BitmapDrawable drawable = (BitmapDrawable) imageViews[blankspace[0]+1][blankspace[1]].getDrawable();
                                                Bitmap bitmaptmp = drawable.getBitmap();
                                                imageViews[blankspace[0]+1][blankspace[1]].setImageDrawable(null);
                                                imageViews[blankspace[0]][blankspace[1]].setImageBitmap(bitmaptmp);
                                                papangame[blankspace[0]][blankspace[1]].row=papangame[blankspace[0]+1][blankspace[1]].row;
                                                papangame[blankspace[0]][blankspace[1]].col=papangame[blankspace[0]+1][blankspace[1]].col;
                                                papangame[blankspace[0]+1][blankspace[1]].row=3;
                                                papangame[blankspace[0]+1][blankspace[1]].col=3;
                                                blankspace[0]++;
                                                System.out.println("KE BAWAH BLANKSPACE");
                                            }else {i--;}
                                        }
                                    }
                                }
                            }
                        }


                        counter++;
                    }
                    if (counter >= bitmapSize) break;
                }



            }



            @Override
            public void onSliceFailed(Throwable throwable) {
                Toast.makeText(SlidingImageGameActivity.this,
                        throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        }, imageUrl);

        // TODO add implementation of the game.
        // There is image adjacent to blank space (either horizontal or vertical).
        // If that image is clicked, it will swap to the blank space
        // if the puzzle is solved (the image in the view is aligned with the original image), then show a "success" dialog

        //Optional timer
        final Handler handler = new Handler();
        final TextView timer = findViewById(R.id.wincondition);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try{
                    if(youwin<16) {
                        timer.setText("Time: " + mytime[0]);
                        mytime[0]++;
                    }
                }
                catch (Exception e) {

                }
                finally{
                    if(youwin<16)handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(runnable);



        // TODO add handling for rotation to save the user input.
        // If the device is rotated, it should retain user's input, so user can continue the game.



    }

}
