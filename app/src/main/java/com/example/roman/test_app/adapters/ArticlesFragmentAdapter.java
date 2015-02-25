package com.example.roman.test_app.adapters;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.roman.test_app.activities.ArticleActivity;
import com.example.roman.test_app.data.Category;
import com.example.roman.test_app.data.DataManager;
import com.example.roman.test_app.data.Header;
import com.example.roman.test_app.fragments.ArticleFragment;

import java.util.List;

public class ArticlesFragmentAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private List<Header> mHeaderList;
    private int mCategoryPosition;


    public ArticlesFragmentAdapter(FragmentManager fragmentManager, int categoryPosition,
                                   Context context) {
        super(fragmentManager);
        mHeaderList = DataManager.getInstance(context).getHeadersOfCategory(
                DataManager.getInstance(context).getCategories().get(categoryPosition));
        mCategoryPosition = categoryPosition;
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleFragment.newInstance(mCategoryPosition, position);
    }

    @Override
    public int getCount() {
        return mHeaderList.size();
    }
}
