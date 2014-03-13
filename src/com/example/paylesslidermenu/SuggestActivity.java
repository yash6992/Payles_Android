package com.example.paylesslidermenu;

import com.example.paylesslidermenu.SuggestActivity;
import com.example.paylesslidermenu.ServiceHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.paylesslidermenu.R;
import android.app.ListActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SuggestActivity extends ListActivity{
    
private ProgressDialog pDialog;

//URL to get contacts JSON
private String url1; 
private String url2;
//JSON Node names


private static final String TAG_NAME = "brand";


//contacts JSONArray
JSONArray contacts = null;
JSONArray contacts2 = null;
//Hashmap for ListView
ArrayList<HashMap<String, String>> contactList;

String manufacturer;
String brand;
String category;
String d_class;
String unit_qty;
String unit_type;
String package_qty;
String package_type;
String package_price;
String unit_price;
String generic_id;

	
	 TextView brand_TV;
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.ex1);
	        
	        // getting intent data
	        Intent in = getIntent();
	        
	        // Get JSON values from previous intent
	        String name = in.getStringExtra("suggestion");
	        
	contactList = new ArrayList<HashMap<String, String>>();
	try {
		name= URLEncoder.encode(name, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	url1= "http://oaayush-aayush.rhcloud.com/api/medicine.json?id="+name+"&key=yash6992&limit=10";
	
	url2= "http://oaayush-aayush.rhcloud.com/api/search.json?id="+name+"&key=yash6992&limit=10";
	
	ListView lv = getListView();

	// Listview on item click listener
	/*lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// getting values from selected ListItem
			String name = ((TextView) view.findViewById(R.id.name))
					.getText().toString();
			

			// Starting single contact activity
			Intent in = new Intent(getApplicationContext(),
					SingleContactActivity.class);
			in.putExtra(TAG_NAME, name);
			
			startActivity(in);

		}
	});
*/
	// Calling async task to get json
	new GetContacts().execute();
}

/**
 * Async task class to get json by making HTTP call
 * */
private class GetContacts extends AsyncTask<Void, Void, Void> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Showing progress dialog
		pDialog = new ProgressDialog(SuggestActivity.this);
		pDialog.setMessage("Zara sa intezaar...");
		pDialog.setCancelable(false);
		pDialog.show();

	}

	@Override
	protected Void doInBackground(Void... arg0) {
		// Creating service handler class instance
		ServiceHandler sh1 = new ServiceHandler();
		ServiceHandler sh2 = new ServiceHandler();
		// Making a request to url and getting response
		String jsonStr1 = sh1.makeServiceCall(url1, ServiceHandler.GET);
		String jsonStr2 = sh2.makeServiceCall(url2, ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr1+jsonStr2);

		if (jsonStr1 != null) {
			try {
				JSONObject jsonObj1 = new JSONObject(jsonStr1);
				
				// Getting JSON Array node
				contacts = jsonObj1.getJSONArray("medicine");

				// looping through All Contacts
				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);
					
					
					
					 manufacturer= c.getString("manufacturer");
					 brand= c.getString("brand");
					 category= c.getString("category");
					 d_class= c.getString("d_class");
					 unit_qty= c.getString("unit_qty");
					 unit_type= c.getString("unit_type");
					 package_qty= c.getString("package_qty");
					 package_type= c.getString("package_type");
					 package_price= c.getString("package_price");
					 unit_price= c.getString("unit_price");
					 generic_id= c.getString("generic_id");
											// tmp hashmap for single contact
					//HashMap<String, String> contact = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					
					/*contact.put("brand", brand);
					contact.put("manufacturer", manufacturer);
					contact.put("unit_price", unit_price);
					contact.put("category", category);
					contact.put("package_qty", package_qty);
					contact.put("package_type", package_type);
					contact.put("package_price", package_price);
					
					// adding contact to contact list
					contactList.add(contact);*/
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}

		// begin search code here.
		
		if (jsonStr2 != null) {
			try {
				JSONObject jsonObj2 = new JSONObject(jsonStr2);
				
				// Getting JSON Array node
				contacts2 = jsonObj2.getJSONArray("drugs");

				// looping through All Contacts
				for (int i = 0; i < contacts2.length(); i++) {
					JSONObject c = contacts2.getJSONObject(i);
					
					
					
					String manufacturer= c.getString("manufacturer");
					String brand= c.getString("brand");
					String category= c.getString("category");
					String d_class= c.getString("d_class");
					String unit_qty= c.getString("unit_qty");
					String unit_type= c.getString("unit_type");
					String package_qty= c.getString("package_qty");
					String package_type= c.getString("package_type");
					String package_price= c.getString("package_price");
					String unit_price= c.getString("unit_price");
					String generic_id= c.getString("generic_id");
											// tmp hashmap for single contact
					HashMap<String, String> contact = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					
					contact.put("alternativename", brand);
					contact.put("costperunit", unit_price);
					contact.put("pkgsize", package_qty);
					
					
					// adding contact to contact list
					contactList.add(contact);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		// Dismiss the progress dialog
		if (pDialog.isShowing())
			pDialog.dismiss();
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(
				SuggestActivity.this, contactList,
				R.layout.row_medicine_singleitem, new String[] { "alternativename", "costperunit", "pkgsize"
						 }, new int[] { R.id.alternativename, R.id.costperunit, R.id.pkgsize
						 });

		setListAdapter(adapter);
		
		((TextView) findViewById(R.id.brand)).setText(brand);
		((TextView) findViewById(R.id.manufacturer)).setText(manufacturer);
		((TextView) findViewById(R.id.unit_price)).setText(unit_price);
		((TextView) findViewById(R.id.category)).setText(category);
		((TextView) findViewById(R.id.package_qty)).setText(package_qty);
		((TextView) findViewById(R.id.package_type)).setText(package_type);
		((TextView) findViewById(R.id.package_price)).setText(package_price);
	}

}

}
