package com.buddycloud;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.buddycloud.fragments.SearchChannelsFragment;

public class SearchActivity extends SherlockFragmentActivity {

	public static final int REQUEST_CODE = 102;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        TextView searchView = (TextView)findViewById(R.id.searchTxt);
		
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		final SearchChannelsFragment searchChannelsFragment = new SearchChannelsFragment();
		searchChannelsFragment.setWindowToken(searchView.getWindowToken());
		transaction.replace(R.id.contentFrame, searchChannelsFragment);
		transaction.commitAllowingStateLoss();
		
		searchView.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				String q = arg0.toString();
				searchChannelsFragment.filter(SearchActivity.this, q);
			}
		});
    }
    
    @Override
    protected void onPause() {
    	TextView et = ((TextView)findViewById(R.id.searchTxt));
    	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        super.onPause();
    }
}
