package com.evolvingvision.trackyouritems.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.evolvingvision.trackyouritems.Constants;
import com.evolvingvision.trackyouritems.R;
import com.evolvingvision.trackyouritems.entity.Transaction;

public class TransactionAdapter extends BaseAdapter{

	private Activity activity;
    private ArrayList<Transaction> data;
    private static LayoutInflater inflater=null;
    
	public TransactionAdapter(Activity a, ArrayList<Transaction> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Transaction getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
        
		if(convertView==null)
            view = inflater.inflate(R.layout.transaction_items, null);
 
        TextView itemNameText = (TextView)view.findViewById(R.id.transactItemName);
        TextView personNameText = (TextView)view.findViewById(R.id.transactPersonName);
        TextView categoryNameText = (TextView)view.findViewById(R.id.transactCategoryName);
        TextView personTypeText = (TextView)view.findViewById(R.id.transactPerson);
        Transaction transaction = data.get(position);
        itemNameText.setText(transaction.getItem().getItemName());
        personNameText.setText(transaction.getPerson().getPersonName());
        categoryNameText.setText(transaction.getCategory().getCategoryName());
        if(transaction.getStatus().getStatusID() == Constants.BORROWED_STATUS_ID){
        	personTypeText.setText(Constants.LENDER);
        }else if(transaction.getStatus().getStatusID() == Constants.LENT_STATUS_ID){
        	personTypeText.setText(Constants.BORROWER);
        }
        
        Button button = (Button)view.findViewById(R.id.returned);
        button.setTag(position);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int position = (Integer)v.getTag();
				data.remove(position);
				notifyDataSetChanged();
			}
		});
        
        return view;
	}
}
