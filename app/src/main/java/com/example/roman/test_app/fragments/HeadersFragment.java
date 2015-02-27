package com.example.roman.test_app.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.roman.test_app.R;
import com.example.roman.test_app.activities.ArticleActivity;
import com.example.roman.test_app.adapters.HeadersAdapter;
import com.example.roman.test_app.data.Category;
import com.example.roman.test_app.data.DataManager;
import com.example.roman.test_app.data.Header;

import java.util.List;

public class HeadersFragment extends Fragment {
    private static final String CATEGORY = "category";
    private static final String CATEGORY_POSITION = "category_position";
    private static final String ARTICLE_POSITION = "article_position";
    private int mCategoryPosition;
    private Category mCategory;
    private ListView mListView;
    private Context mContext;
    private TextView mCategoryTextView;

    public static HeadersFragment newInstance(int category) {
        HeadersFragment headersFragment = new HeadersFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORY, category);
        headersFragment.setArguments(args);
        return headersFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_headers, container, false);
        mContext = getActivity();
        mCategoryPosition = getArguments().getInt(CATEGORY);
        mListView = (ListView) rootView.findViewById(R.id.headers_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int articlePosition, long id) {
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra(CATEGORY_POSITION, mCategoryPosition);
                intent.putExtra(ARTICLE_POSITION, articlePosition);
                startActivity(intent);
            }
        });
        mCategoryTextView = (TextView) rootView.findViewById(R.id.name_fragment_category);
        mCategory = DataManager.getInstance(getActivity()).getCategories().get(mCategoryPosition);
        mCategoryTextView.setText(mCategory.getCategoryName());
        new LoadHeadersAsyncTask().execute(mCategory);
        return rootView;
    }

    private class LoadHeadersAsyncTask
            extends AsyncTask<Category, Void, List<Header>> {
        @Override
        protected List<Header> doInBackground(Category... params) {
            return DataManager.getInstance(getActivity()).getHeadersOfCategory(params[0]);
        }

        @Override
        protected void onPostExecute(List<Header> result) {
            super.onPostExecute(result);
            if (result != null) {
                mListView.setAdapter(new HeadersAdapter(mContext, 0, result));
            }
        }
    }
}
