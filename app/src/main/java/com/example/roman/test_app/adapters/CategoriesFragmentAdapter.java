package com.example.roman.test_app.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.roman.test_app.data.Category;
import com.example.roman.test_app.fragments.HeadersFragment;

import java.util.List;

public class CategoriesFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Category> mCategories;

    public CategoriesFragmentAdapter(FragmentManager fragmentManager, List<Category> categories) {
        super(fragmentManager);
        mCategories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return HeadersFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }
}