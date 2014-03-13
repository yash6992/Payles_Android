 package com.example.paylesslidermenu;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaylesFragment extends Fragment{
	
	private Button submit;
	private EditText find;
	// flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
	
	public PaylesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_payles, container, false);
        cd = new ConnectionDetector(getActivity());
        submit=(Button) rootView.findViewById(R.id.button1);
        find= (EditText)rootView.findViewById(R.id.editText1);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				 // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
 
                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                   // showAlertDialog(getActivity(), "Internet Connection",
                          //  "You have internet connection", true);
                	Intent i = new Intent(getActivity(), SearchActivity.class);
    				i.putExtra("find", find.getText().toString());
    				
    				startActivity(i);	
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(getActivity(), "No Internet Connection",
                            "You don't have internet connection.", false);
                }

				
			}			
		});
       
        
        return rootView;
    }

	 @SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
	        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
	 
	        // Setting Dialog Title
	        alertDialog.setTitle(title);
	 
	        // Setting Dialog Message
	        alertDialog.setMessage(message);
	         
	        // Setting alert dialog icon
	        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
	 
	        // Setting OK Button
	        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            }
	        });
	 
	        // Showing Alert Message
	        alertDialog.show();
	    }	

}
