package com.example.animationslider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	int mGalleryItemBackground;
	private Context mContext;

	private Integer[] mImageIds = {  
			  R.drawable.vb1,
			  R.drawable.vb2,
			  R.drawable.vb3,
			 
	};

	 public ImageView[] iImages;

	public ImageAdapter(Context c) 
	{
		mContext = c;

		iImages=new ImageView[mImageIds.length];
	}
    
	@SuppressWarnings("deprecation")
	public boolean createReflectedImages() {
		// The gap we want between the reflection and the original image
		final int reflectionGap = 4;
		
		System.out.println("CreateReflected Image:"); 
		int index = 0;
		for (int a=0;a<mImageIds.length;a++) {
							Bitmap originalImage = BitmapFactory.decodeResource(
									mContext.getResources(), mImageIds[a]);
							int width = originalImage.getWidth();
							int height = originalImage.getHeight();
			              
							// This will not scale but will flip on the Y axis
							Matrix matrix = new Matrix();
							matrix.preScale(1, -1);
			
							// Create a Bitmap with the flip matrix applied to it.
							// We only want the bottom half of the image
							Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
									height / 2, width, height / 2, matrix, false);
			
							// Create a new bitmap with same width but taller to fit
							// reflection
							Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
									(height + height / 2), Config.ARGB_8888);
			
							// Create a new Canvas with the bitmap that's big enough for
							// the image plus gap plus reflection
							Canvas canvas = new Canvas(bitmapWithReflection);
							// Draw in the original image
							canvas.drawBitmap(originalImage, 0, 0, null);
							// Draw in the gap
							Paint deafaultPaint = new Paint();
							canvas.drawRect(0, height, width, height + reflectionGap,
									deafaultPaint);
							// Draw in the reflection
							canvas.drawBitmap(reflectionImage, 0, height + reflectionGap,
									null);
			
							// Create a shader that is a linear gradient that covers the
							// reflection
							Paint paint = new Paint();
							LinearGradient shader = new LinearGradient(0,
									originalImage.getHeight(), 0,
									bitmapWithReflection.getHeight() + reflectionGap,
									0x70ffffff, 0x00ffffff, TileMode.CLAMP);
							// Set the paint to use this shader (linear gradient)
							paint.setShader(shader);
							// Set the Transfer mode to be porter duff and destination in
							paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
							// Draw a rectangle using the paint with our linear gradient
							canvas.drawRect(0, height, width,
									bitmapWithReflection.getHeight() + reflectionGap, paint);
							
							ImageView imageView = new ImageView(mContext);
							imageView.setImageBitmap(bitmapWithReflection);
							//imageView.setBackgroundResource(R.drawable.logo_large);
							//imageView.setTag("http://www.iipacademy.in/dhjodhpur/advertise_image/"+XMLSArrayLists.getAdImg().get(a));
							//	imageManager.displayImage("http://www.iipacademy.in/dhjodhpur/advertise_image/"+XMLSArrayLists.getAdImg().get(a),imageView,R.drawable.logo_large);
							android.widget.Gallery.LayoutParams imgLayout = new CoverFlow.LayoutParams(
									320, 480);
							imageView.setLayoutParams(imgLayout);
							imageView.setPadding(30, 100, 20, 20);
							iImages[index++] = imageView;

			
        }
		return true;
	}

	public int getCount() {
		return mImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	public View getView(final int position, View convertView, ViewGroup parent) {

		
		//System.out.println("getView CoverFlow:"); 
		// Use this code if you want to load from resources
		ImageView i = new ImageView(mContext);
        
		//i.setBackgroundResource(R.drawable.logo_large);
		//i.setTag("http://www.dhoondojodhpur.com/advertise_image/"+XMLSArrayLists.getAdImg().get(position));
		//i.setTag(123,position); 18-10-13
		//Drawable drawableshow = LoadImageFromWebOperations("http://www.iipacademy.in/dhjodhpur/advertise_image/"+XMLSArrayLists.getAdImg().get(position));
		i.setImageResource(mImageIds[position]);
		//imageManager.displayImage("http://www.dhoondojodhpur.com/advertise_image/"+XMLSArrayLists.getAdImg().get(position),i,R.drawable.logo_large);
        
		//	i.setImageResource(XMLSArrayLists.getAdID().);
		i.setLayoutParams(new CoverFlow.LayoutParams(200, 400)); 
		i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//		i.setOnClickListener(new View.OnClickListener() {
//        
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				
//				
//			}
//		});
		// Make sure we set anti-aliasing otherwise we get jaggies
		BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
		drawable.setAntiAlias(true);
		return i;
		// return mImages[position];
	}
	/**
	 * Returns the size (0.0f to 1.0f) of the views depending on the
	 * 'offset' to the center.
	 */
	public float getScale(boolean focused, int offset) {
		/* Formula: 1 / (2 ^ offset) */
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}
}
