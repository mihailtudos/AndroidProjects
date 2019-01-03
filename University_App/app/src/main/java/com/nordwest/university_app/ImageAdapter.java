package com.nordwest.university_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
    //keeps truck of the content
    private Context mContext;
    //image resources stored in an array
    private int[] mImage = new int[] {
            R.drawable.level3,
            R.drawable.lavel2,
            R.drawable.lavel1,
            R.drawable.groundlevel};

    ImageAdapter(Context context){
        mContext =context;
    }
    /*implemented automatically in order to implement the PagerAdapter*/
    //returns the number of images so that the context will create exactly as many pages as images we have stored in the array
    @Override
    public int getCount() {
        return mImage.length;
    }

    /*this method works as a key here to help the viewPager to chose the right view for the right item
    * basically it checks it the iageView created by instantiateItem method belongs to the object identifier and therefor is displayed if
    * it returns true*/
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /*methods generated that will create and destroy the content
    *
    * first method will instantiate set an image content with preset full display image size, starting from index 0 of our array and will pass
    * as an object to isViewFromObject() method*/
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(mImage[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    /*when the item gets descried ot removes the item form the context*/
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ImageView) object);
    }
}
