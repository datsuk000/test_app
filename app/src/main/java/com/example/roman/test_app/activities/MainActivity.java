package com.example.roman.test_app.activities;

import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.roman.test_app.R;
import com.example.roman.test_app.adapters.CategoriesFragmentAdapter;
import com.example.roman.test_app.data.DataManager;

import java.util.List;

import com.example.roman.test_app.data.Category;
import com.viewpagerindicator.CirclePageIndicator;

public class MainActivity extends ActionBarActivity {
    private ViewPager mViewPager;
    private CategoriesFragmentAdapter mCategoriesFragmentAdapter;
    private CirclePageIndicator mCirclePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CategoriesAsyncTask().execute();

    }

    private void circleViewPager(List<Category> categoryList) {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_activity_main);
        mCategoriesFragmentAdapter =
                new CategoriesFragmentAdapter(getSupportFragmentManager(), categoryList);
        mViewPager.setAdapter(mCategoriesFragmentAdapter);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator_activity_main);
        mCirclePageIndicator.setViewPager(mViewPager);
    }

    private class CategoriesAsyncTask extends AsyncTask<Void, Void, List<Category>> {
        @Override
        protected List<Category> doInBackground(Void... params) {
            return DataManager.getInstance(MainActivity.this).getCategories();

        }

        @Override
        protected void onPostExecute(List<Category> categoryList) {
            super.onPostExecute(categoryList);
            circleViewPager(categoryList);
        }
    }

}
