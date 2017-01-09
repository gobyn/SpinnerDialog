package hu.dpal.phonegap.plugins;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.R;
import android.content.Context;
import android.widget.ImageView;
import android.os.Bundle;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import android.content.res.Resources;
public class EniProgressDialog extends ProgressDialog {

	private AnimationDrawable animation;
	private Context context;

	public EniProgressDialog(Context context){
		super(context);
		this.context = context;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String package_name = context.getPackageName();
		Resources resources = context.getResources();
		LinearLayout layout = new LinearLayout(context);
		ImageView imageView = new ImageView(context);
		animation = new AnimationDrawable();
		animation.setOneShot(false);
		for(int i=1;i<=12;i++){
			Drawable draw = resources.getDrawable( resources.getIdentifier("loadicon"+i, "drawable", package_name) );
			animation.addFrame(draw, 50);
		}
		imageView.setBackground(animation);
		layout.addView(imageView);
		setContentView(layout);

		setCancelable(false);
        setCanceledOnTouchOutside(false);
	}

	@Override
	public void show() {
	  super.show();
	  animation.start();
	}

	@Override
	public void dismiss() {
	  super.dismiss();
	  animation.stop();
	}
}
