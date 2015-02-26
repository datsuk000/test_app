package com.example.roman.test_app.data;
import android.content.Context;
import android.util.Log;

import com.example.roman.test_app.utilities.HttpHelper;
import com.example.roman.test_app.utilities.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    private static final String LOG = "DataManager";
    private static final String URL = "http://figaro.service.yagasp.com/article/";
    private static final String URL_CATEGORIES = "http://figaro.service.yagasp.com/article/categories";
    private static final String URL_HEADERS = "http://figaro.service.yagasp.com/article/header/";

    private static DataManager sInstance;

    private List<Category> mCategories;
    private Map<Category, List<Header>> mCategoryHeadersListMap;
    private Map<Header, Article> mHeaderArticleMap;

    private DataManager(Context context) {
        mCategoryHeadersListMap = new HashMap<>();
        mHeaderArticleMap = new HashMap<>();
    }

    public static synchronized DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context.getApplicationContext());
        }
        return sInstance;
    }

    public List<Category> getCategories() {
        Log.i(LOG, "Starting getCategories");
        if (mCategories == null) {
            try {
                String url = HttpHelper.downloadData(URL_CATEGORIES);
                mCategories = JsonParser.getCategoriesFromJson(url);
                Log.i(LOG, "categories size: " + mCategories.size());
                return mCategories;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return mCategories;
        }

    }

    public List<Header> getHeadersOfCategory(Category category) {
        Log.i(LOG, "Starting getHeadersOfCategory");
        List<Header> articleHeaders = mCategoryHeadersListMap.get(category);
        if (articleHeaders == null) {
            try {
                String articleHeadersUrl = URL_HEADERS + category.getCategoryId();
                String jsonArticleHeaders = HttpHelper.downloadData(articleHeadersUrl);
                articleHeaders = JsonParser.getHeaderFromJson(jsonArticleHeaders);
                mCategoryHeadersListMap.put(category, articleHeaders);
                return articleHeaders;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                return null;
            }
        } else {
            return articleHeaders;
        }

    }

    public Article getArticle(Header header) {
        Log.i(LOG, "Starting getting article");
        Article article = mHeaderArticleMap.get(header);
        if (article == null) {
            try {
                String articleUrl = URL + header.getHeaderId();
                Log.i(LOG, "url" + articleUrl);
                String jsonArticle = HttpHelper.downloadData(articleUrl);
                if (jsonArticle == null) {
                    return null;
                } else {
                    article = JsonParser.getArticleFromJson(jsonArticle);
                    mHeaderArticleMap.put(header, article);
                }
                return article;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                return null;
            }
        } else {
            return article;
        }
    }
}
