package com.fragrancedubois.sg.fdbapp03;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by philip on 9/15/14.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private PersonalityActivity mActivity;

    private String[] mTitles;
    private List<FdbWheeler> mFdbWheelers;

    //private Integer mCwThumbId;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public ImageAdapter(PersonalityActivity c, List<FdbWheeler> dataList) {
        mContext = c;
        mActivity = c;

        mFdbWheelers = dataList;

        // create drawable grid array
        Integer listSize = mFdbWheelers.size();
        //mThumbIds = new Integer[listSize];
        mTitles = new String[listSize];

        Integer i = 0, flag;

        for (FdbWheeler entry : mFdbWheelers) {
            flag = i++;
            String title = entry.mName;
            mTitles[flag] = title;
        }
    }

    public int getCount() {
        return mTitles.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View grid;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            FdbWheeler wheeler = mFdbWheelers.get(position);
            grid = wheeler.createGridSelection(mActivity);
        } else {
            grid = (LinearLayout) convertView;
        }

        return grid;
    }
}