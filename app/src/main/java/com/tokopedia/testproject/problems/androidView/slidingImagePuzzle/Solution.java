package com.tokopedia.testproject.problems.androidView.slidingImagePuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.tokopedia.testproject.R;
import com.tokopedia.testproject.UtilKt;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Solution {

    private static Bitmap bitmap; //var gambar bitmap disimpan
    static ArrayList<Bitmap> bitmapList = new ArrayList<>(); //List array pecahan gambar
    static boolean downloadselesai=false;   //var trigger download selesai

    private static class ConvertUrlToBitmap extends AsyncTask<String, Long, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return true;
            } catch (Exception e) {
                Log.e("EROR DONLOD: ", e.toString());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            System.out.println("GAMBAR OK");
            if(aBoolean) {
                // download was successful

                bitmapList.clear();
                Bitmap croppedBmp = Bitmap.createBitmap(bitmap, 0, 0, 400, 400);

                for(int myY = 0;myY<400;myY+=100)
                for(int myX = 0;myX<400;myX+=100) {
                    Bitmap cuttedBmp = Bitmap.createBitmap(croppedBmp, myX, myY,
                            croppedBmp.getWidth() / 4, croppedBmp.getHeight() / 4);
                    bitmapList.add(cuttedBmp);
                }
                downloadselesai=true;


            } else {
                // download failed
            }
        }
    }

    public interface onSuccessLoadBitmap{
        void onSliceSuccess(List<Bitmap> bitmapList);
        void onSliceFailed(Throwable throwable);
    }

    public static void sliceTo4x4(final Context context, final onSuccessLoadBitmap onSuccessLoadBitmap, String imageUrl) {

        // TODO, download the image, crop, then sliced to 15 Bitmap (4x4 Bitmap). ignore the last Bitmap
        // below is stub, replace with your implementation!

        new ConvertUrlToBitmap().execute(imageUrl);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try{
                    //do your code here
                    System.out.println(bitmapList.size());
                    if(bitmapList.size()==16)onSuccessLoadBitmap.onSliceSuccess(bitmapList);
                }
                catch (Exception e) {

                }
                finally{
                    //also call the same runnable to call it at regular interval
                    if(!downloadselesai)handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(runnable);

        /*
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample11));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample12));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample13));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample14));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample21));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample22));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample23));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample24));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample31));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample32));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample33));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample34));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample41));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample42));
        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample43));*/




    }
}
