package com.duckduckgo.mobile.android.fragment;

import com.duckduckgo.mobile.android.DDGApplication;
import com.duckduckgo.mobile.android.R;
import com.duckduckgo.mobile.android.activity.DuckDuckGo;
import com.duckduckgo.mobile.android.adapters.HistoryCursorAdapter;
import com.duckduckgo.mobile.android.adapters.SavedFeedCursorAdapter;
import com.duckduckgo.mobile.android.objects.HistoryObject;
import com.duckduckgo.mobile.android.views.MainFeedListView;
import com.duckduckgo.mobile.android.views.RecentSearchListView;
import com.duckduckgo.mobile.android.views.RecentSearchListView.OnHistoryItemSelectedListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * @author mwho
 *
 */
public class Tab2Fragment extends Fragment {
	MainFeedListView savedFeedView = null;
	SavedFeedCursorAdapter savedFeedAdapter = null;
	
	/** (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
		
		// setup for real work
		final Activity activity = getActivity();
		
		LinearLayout fragmentLayout = (LinearLayout)inflater.inflate(R.layout.tab_fragment2, container, false);
		
		if(activity instanceof DuckDuckGo) {	
			
			savedFeedAdapter = new SavedFeedCursorAdapter(activity, activity, DDGApplication.getDB().getCursorStoryFeed());
			
    		savedFeedView = (MainFeedListView) fragmentLayout.findViewById(R.id.savedFeedItems);
    		savedFeedView.setAdapter(savedFeedAdapter);
//    		savedFeedView.setOnHistoryItemSelectedListener(new OnHistoryItemSelectedListener() {
//				
//				public void onHistoryItemSelected(HistoryObject historyObject) {
//					if(historyObject != null){	
//						if(historyObject.getType().equals("R"))
//							((DuckDuckGo) activity).searchWebTerm(historyObject.getData());
//						else if(historyObject.getType().equals("W")) 
//							((DuckDuckGo) activity).showWebUrl(historyObject.getUrl());
//					}			
//				}
//			});
    		
    		savedFeedView.setOnMainFeedItemSelectedListener(((DuckDuckGo) activity).mFeedItemSelectedListener);
    		savedFeedView.setOnMainFeedItemLongClickListener(((DuckDuckGo) activity).mFeedItemLongClickListener);
		}
		
		return fragmentLayout;
	}
}