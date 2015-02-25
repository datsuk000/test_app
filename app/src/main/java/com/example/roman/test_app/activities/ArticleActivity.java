package com.example.roman.test_app.activities;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.roman.test_app.R;
import com.example.roman.test_app.adapters.ArticlesFragmentAdapter;
import com.example.roman.test_app.data.Category;
import com.example.roman.test_app.data.DataManager;
import com.viewpagerindicator.CirclePageIndicator;

public class ArticleActivity extends FragmentActivity {
    private static final String CATEGORY_POSITION = "category_position";
    private static final String ARTICLE_POSITION = "article_position";
    private int mCategoryPosition;
    private int mArticlePosition;
    private TextView mCategoryTitle;
    private ViewPager mViewPager;
    private ArticlesFragmentAdapter mArticlesFragmentAdapter;
    private CirclePageIndicator mCirclePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mArticlePosition = getIntent().getIntExtra(ARTICLE_POSITION, 0);
        mCategoryPosition = getIntent().getIntExtra(CATEGORY_POSITION, 0);

        Category category = DataManager.getInstance(this).getCategories().get(mCategoryPosition);
        mCategoryTitle = (TextView) findViewById(R.id.name_fragment_category);
        mCategoryTitle.setText(category.getCategoryName());
        circleViewPager(mArticlePosition, mCategoryPosition);
    }

    private void circleViewPager(int articlePosition, int categoryPosition) {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_activity_article);
        mArticlesFragmentAdapter = new ArticlesFragmentAdapter(getSupportFragmentManager(),
                categoryPosition, ArticleActivity.this);
        mViewPager.setAdapter(mArticlesFragmentAdapter);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator_activity_article);
        mCirclePageIndicator.setViewPager(mViewPager, articlePosition);
    }

}
