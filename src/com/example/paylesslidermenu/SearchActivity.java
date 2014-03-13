package com.example.paylesslidermenu;


import com.example.paylesslidermenu.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.paylesslidermenu.ServiceHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchActivity extends ListActivity {
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private String url;

	// JSON Node names
	private static final String TAG_CONTACTS = "suggestions";
	
	private static final String TAG_NAME = "suggestion";
	

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;

	
	 @Override
	    protected void onCreate(Bundle savedInstanceState)  {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_search);
	        
	        Intent i=getIntent();
	         String find = i.getStringExtra("find");
	        contactList = new ArrayList<HashMap<String, String>>();
	       
	        try {
	        	
				find= URLEncoder.encode(find, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        url = "http://oaayush-aayush.rhcloud.com/api/suggest.json?id="+find+"&key=yash6992&limit=100";
		       
			ListView lv = getListView();

			// Listview on item click listener
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// getting values from selected ListItem
					String name = ((TextView) view.findViewById(R.id.textView1))
							.getText().toString();
					
					//Toast.makeText(getApplicationContext(), name, 6000).show();
					// Starting single contact activity
					Intent in = new Intent(getApplicationContext(),
							SuggestActivity.class);
					in.putExtra("suggestion", name);
					
					
					startActivity(in);

				}
			});

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
				pDialog = new ProgressDialog(SearchActivity.this);
				pDialog.setMessage("Zara sa intezaar...");
				pDialog.setCancelable(false);
				pDialog.show();

			}

			@Override
			protected Void doInBackground(Void... arg0) {
				// Creating service handler class instance
				ServiceHandler sh = new ServiceHandler();

				// Making a request to url and getting response
				String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

				Log.d("Response: ", "> " + jsonStr);

				if (jsonStr != null) {
					try {
						JSONObject jsonObj = new JSONObject(jsonStr);
						
						// Getting JSON Array node
						contacts = jsonObj.getJSONArray(TAG_CONTACTS);

						// looping through All Contacts
						for (int i = 0; i < contacts.length(); i++) {
							JSONObject c = contacts.getJSONObject(i);
							
							
							String name = c.getString(TAG_NAME);
							

							
							// tmp hashmap for single contact
							HashMap<String, String> contact = new HashMap<String, String>();

							// adding each child node to HashMap key => value
							
							contact.put(TAG_NAME, name);
							
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
						SearchActivity.this, contactList,
						R.layout.row_search_singleitem, new String[] { TAG_NAME,
								 }, new int[] { R.id.textView1,
								 });

				setListAdapter(adapter);
			}

		}
	        
	       
	        
	    }   


