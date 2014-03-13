package com.example.paylesslidermenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class FAQsFragment extends Fragment {
	
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
	
	public FAQsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_faqs, container, false);
        
        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
 
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 
            @Override
            public void onGroupExpand(int groupPosition) {
               
            }
        });
 
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
               
 
            }
        });
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                
                return false;
            }
        });
        return rootView;
    }
 
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Are generic drugs safe? ");
        listDataHeader.add("Are they as strong as brand name drugs? ");
        listDataHeader.add("Will they take longer to work in my body? ");
        listDataHeader.add("Why are they cheaper? ");
        listDataHeader.add("Are brand-name drugs made in better factories? ");
        listDataHeader.add("Why don’t the drugs look the same?");
        listDataHeader.add("Does every brand name drug have a generic form?");
        listDataHeader.add("What is the best source of information about generic drugs?");
        listDataHeader.add("Where can I get generic drugs? ");
        listDataHeader.add("Why have I not heard of generic drugs until now?");
        // Adding child data
        List<String> q1 = new ArrayList<String>();
        q1.add("Yes. The FDA requires that all drugs are safe and effective. Generic drugs use the same substances and work the same way in the body as brand name drugs. So they have the same risks and benefits as the brand name drug. ");
        
        List<String> q2 = new ArrayList<String>();
        q2.add("The FDA demands that generic drugs are just as strong, pure, and stable as brand name drugs. They must also be the same quality.");

        List<String> q3 = new ArrayList<String>();
        q3.add("Generics work in the same way and in the same amount of time as brand name drugs.");

        List<String> q4 = new ArrayList<String>();
        q4.add("New drugs are developed under patents. A patent gives only that one company the right to sell the drug for a period of time. This protects those who create the drug and pay to research, develop, and market it. Other companies can apply to the FDA to sell a generic when the patent is about to expire. Generic companies do not have the same up-front costs to research and develop the drug. They can sell their drug at a lower cost because their makers don’t have the costs of creating the new drug. • Also, once generic drugs are approved, there are more people trying to sell the drug. This keeps the price down. Today, almost half of all prescriptions are filled with generic drugs.");
        
        List<String> q5 = new ArrayList<String>();
        q5.add("Both brand name and generic drug factories must meet the same standards. The FDA will not let drugs be made in poor quality factories. The FDA inspects about 3,500 factories a year to make sure they meet the standards. Often the same factories make both brand name and generic drugs.");

        List<String> q6 = new ArrayList<String>();
        q6.add(" In the United States, laws do not allow a generic drug to look exactly like the brand name drug. A generic drug must use the same substances as brand name drugs. Colors, flavors, and some other inactive parts may be different.");
        
        List<String> q7 = new ArrayList<String>();
        q7.add("Brand name drugs are protected by patent for 20 years. When the patent expires, other drug companies can create generics. But they must be tested by the maker and approved by the FDA.");
        
        List<String> q8 = new ArrayList<String>();
        q8.add("Contact your doctor, pharmacist or insurance company for information on generic drugs.");
        
        List<String> q9 = new ArrayList<String>();
        q9.add("About 400 essential drugs are provided free of cost at about 15000 Drug Distribution Centre located in Govt. hospitals in Rajasthan, right from medical college hospitals to PHCs and Sub Centers (most of which are in rural areas). These medicines are procured by generic name through transparent open tender and pharmaceutical manufacturers supply them to the state govt. at unbelievably low prices.");
      
        List<String> q10 = new ArrayList<String>();
        q10.add("Medicines are the only commodity in which the end-user (the paying patient) does not decide what to buy and at what cost. The doctor prescribes and the patient pays. In addition, the doctors prescribe medicines by brand name of a particular drug company. This prevents competition and creates monopoly in the drug market and enables the drug company to put a very high MRP, despite the same drug being produced by many companies. Moreover, drug consumers are highly vulnerable and their requirement is urgent, hence they are not in a position to compare prices, bargain or choose. There is no regulation on prices of drugs by the Government, thus the pharmaceutical companies are operating in a manner which keeps prices of drugs (including the essential drugs) very high so that profitability is maximized.");


 
       
 
        listDataChild.put(listDataHeader.get(0), q1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), q2);
        listDataChild.put(listDataHeader.get(2), q3);
        listDataChild.put(listDataHeader.get(3), q4);
        listDataChild.put(listDataHeader.get(4), q5);
        listDataChild.put(listDataHeader.get(5), q6);
        listDataChild.put(listDataHeader.get(6), q7);
        listDataChild.put(listDataHeader.get(7), q8);
        listDataChild.put(listDataHeader.get(8), q9);
        listDataChild.put(listDataHeader.get(9), q10);
    
 
        
    }
}
