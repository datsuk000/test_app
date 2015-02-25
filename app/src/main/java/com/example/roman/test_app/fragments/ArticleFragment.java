package com.example.roman.test_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.roman.test_app.R;
import com.example.roman.test_app.activities.ArticleActivity;
import com.example.roman.test_app.data.Article;
import com.example.roman.test_app.data.Category;
import com.example.roman.test_app.data.DataManager;
import com.example.roman.test_app.data.Header;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ArticleFragment extends Fragment {

    private static final String LOG = "ArticleFragment";
    private static final String CATEGORY_POSITION = "category_position";
    private static final String ARTICLE_POSITION = "article_position";
    private static final int IMAGE_SIZE = 800;
    private Context mContext;
    private TextView mArticleTitle;
    private TextView mArticleSubTitle;
    private TextView mArticleAuthor;
    private TextView mArticleDate;
    private WebView mArticleText;
    private ImageView mArticleImage;
    private Header mArticleHeader;

    public static ArticleFragment newInstance(int categoryPosition, int articlePosition) {
        ArticleFragment articleFragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORY_POSITION, categoryPosition);
        args.putInt(ARTICLE_POSITION, articlePosition);
        articleFragment.setArguments(args);
        return articleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_article, container, false);
        int categoryPosition = getArguments().getInt(CATEGORY_POSITION);
        int articlePosition = getArguments().getInt(ARTICLE_POSITION);
        Category category = DataManager.getInstance(getActivity()).getCategories().get(categoryPosition);
        List<Header> articleHeaders = DataManager.getInstance(getActivity()).getHeadersOfCategory(category);
        mArticleHeader = articleHeaders.get(articlePosition);
        Log.i(LOG, "articleheader " + mArticleHeader.getTitle());
        initializeComponents(rootView);
        new LoadArticleAsynkTask().execute(mArticleHeader);
        return rootView;
    }

    private void initializeComponents(ViewGroup viewGroup) {
        mArticleTitle = (TextView) viewGroup.findViewById(R.id.article_title);
        mArticleAuthor = (TextView) viewGroup.findViewById(R.id.article_author);
        mArticleDate = (TextView) viewGroup.findViewById(R.id.article_date);
        mArticleSubTitle = (TextView) viewGroup.findViewById(R.id.article_subtitle);
        mArticleText = (WebView) viewGroup.findViewById(R.id.article_text);
        mArticleImage = (ImageView) viewGroup.findViewById(R.id.article_image);

    }

    private void setArticleToView(Article article) {
        mArticleTitle.setText(mArticleHeader.getTitle());
        mArticleAuthor.setText(article.getAuthor());
        Date date = new Date(article.getDate());
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
        mArticleDate.setText(", " + dateFormat.format(date).toString());
        if (TextUtils.isEmpty(mArticleHeader.getSubTitle())) {
            mArticleSubTitle.setVisibility(View.GONE);
        } else {
            mArticleSubTitle.setVisibility(View.VISIBLE);
            mArticleSubTitle.setText(mArticleHeader.getSubTitle());
        }

        Log.i(LOG, "date " + dateFormat.format(date));
        if (TextUtils.isEmpty(mArticleHeader.getLink())) {
            mArticleImage.setVisibility(View.GONE);
        } else {
            mArticleImage.setVisibility(View.VISIBLE);
            String imageUrl = String.format(mArticleHeader.getLink(), IMAGE_SIZE, IMAGE_SIZE);
            Ion.with(mArticleImage).load(imageUrl);
        }
        if (TextUtils.isEmpty(article.getContent())) {
            mArticleText.setVisibility(View.GONE);
        } else {
            mArticleText.setVisibility(View.VISIBLE);
            mArticleText.getSettings().setDefaultFontSize(14);
            mArticleText.loadData(article.getContent(), "text/html; charset=UTF-8", null);
        }
    }

    private class LoadArticleAsynkTask extends AsyncTask<Header, Void, Article> {

        @Override
        protected Article doInBackground(Header... params) {
            return DataManager.getInstance(getActivity()).getArticle(params[0]);
        }

        @Override
        protected void onPostExecute(Article result) {
            super.onPostExecute(result);
            if (result != null) {
                setArticleToView(result);
            }
        }
    }
}
