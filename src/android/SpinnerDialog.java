package hu.dpal.phonegap.plugins;

import java.util.Stack;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class SpinnerDialog extends CordovaPlugin {

	public Stack<EniProgressDialog> spinnerDialogStack = new Stack<EniProgressDialog>();

	public SpinnerDialog() {
	}

	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		if (action.equals("show")) {

			final String title = args.getString(0) == "null" ? null : args
					.getString(0);
			final String message = args.getString(1) == "null" ? null : args
					.getString(1);

			final CordovaInterface cordova = this.cordova;
			Runnable runnable = new Runnable() {
				public void run() {
					EniProgressDialog dialog = new EniProgressDialog(cordova.getActivity());
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					dialog.show();
					SpinnerDialog.this.spinnerDialogStack.push(dialog);

				}
			};
			this.cordova.getActivity().runOnUiThread(runnable);

		} else if (action.equals("hide")) {

			Runnable runnable = new Runnable() {
				public void run() {

					if (!SpinnerDialog.this.spinnerDialogStack.empty()) {
						SpinnerDialog.this.spinnerDialogStack.pop().dismiss();
					}

				}
			};
			this.cordova.getActivity().runOnUiThread(runnable);

		}

		callbackContext.success();
		return true;
	}

}
