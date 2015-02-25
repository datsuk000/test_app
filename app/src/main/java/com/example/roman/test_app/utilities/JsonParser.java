package com.example.roman.test_app.utilities;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.roman.test_app.data.Article;
import com.example.roman.test_app.data.Category;
import com.example.roman.test_app.data.Header;

public class JsonParser {
    private static final String TAG = "JsonParser";

    private static final String JSON_SUBCATEGORIES_ARRAY = "subcategories";
    private static final String JSON_SUBCATEGORY_ID = "id";
    private static final String JSON_SUBCATEGORY_NAME = "name";

    private static final String JSON_HEADER_ID = "id";
    private static final String JSON_HEADER_UPDATE = "update";
    private static final String JSON_HEADER_DATE = "date";
    private static final String JSON_HEADER_RANKING = "ranking";
    private static final String JSON_HEADER_TITLE = "title";
    private static final String JSON_HEADER_SUBTITLE = "subtitle";
    private static final String JSON_HEADER_THUMB = "thumb";
    private static final String JSON_HEADER_THUMB_LINK = "link";

    private static final String JSON_ARTICLE_ID = "_id";
    private static final String JSON_ARTCILE_AUTHOR = "author";
    private static final String JSON_ARTICLE_CATEGORY_ID = "categoryId";
    private static final String JSON_ARTICLE_CONTENT = "content";
    private static final String JSON_ARTCILE_DATE = "date";

    public static List<Category> getCategoriesFromJson(String jsonString) throws JSONException {
        Log.i(TAG, "Starting getting categories from json");
        JSONArray jsonCategoriesArray = new JSONArray(jsonString);
        List<Category> categoriesList = new ArrayList<>();
        for (int i = 0; i < jsonCategoriesArray.length(); i++) {
            JSONArray jsonSubcategories =
                    jsonCategoriesArray.getJSONObject(i).getJSONArray(JSON_SUBCATEGORIES_ARRAY);
            for (int j = 0; j < jsonSubcategories.length(); j++) {
                JSONObject jsonSubcategory = jsonSubcategories.getJSONObject(j);
                String jsonSubcategoryId = jsonSubcategory.getString(JSON_SUBCATEGORY_ID);
                String jsonSubcategoryName = jsonSubcategory.getString(JSON_SUBCATEGORY_NAME);
                categoriesList.add(new Category(jsonSubcategoryName, jsonSubcategoryId));
                Log.i(TAG, "----------------------------------------------------------");
                Log.i(TAG, "category id :" + categoriesList.get(j).getCategoryId());
                Log.i(TAG, "category name :" + categoriesList.get(j).getCategoryName());
                Log.i(TAG, "----------------------------------------------------------");
            }
        }
        Log.i(TAG, "Finishing getting categories from json" + categoriesList.size());
        return categoriesList;
    }

    public static List<Header> getHeaderFromJson(String jsonString) throws JSONException {
        Log.i(TAG, "Starting getting headers from json");
        JSONArray jsonHeadersArray = new JSONArray(jsonString).getJSONArray(1);
        List<Header> headersList = new ArrayList<>();

        for (int i = 0; i < jsonHeadersArray.length(); i++) {
            JSONObject jsonHeader = jsonHeadersArray.getJSONObject(i);
            String jsonHeaderId = jsonHeader.getString(JSON_HEADER_ID);
            long jsonHeaderUpdate = jsonHeader.getLong(JSON_HEADER_UPDATE);
            long jsonHeaderDate = jsonHeader.getLong(JSON_HEADER_DATE);
            int jsonHeaderRanking = jsonHeader.getInt(JSON_HEADER_RANKING);
            String jsonHeaderTitle = jsonHeader.getString(JSON_HEADER_TITLE);
            String jsonHeaderSubTitle = jsonHeader.getString(JSON_HEADER_SUBTITLE);
            String jsonHeaderLink = jsonHeader.getJSONObject(JSON_HEADER_THUMB).getString(
                    JSON_HEADER_THUMB_LINK);
            headersList.add(new Header(jsonHeaderId, jsonHeaderUpdate, jsonHeaderDate,
                    jsonHeaderRanking, jsonHeaderTitle, jsonHeaderSubTitle, jsonHeaderLink));
            Log.i(TAG, "----------------------------------------------------------");
            Log.i(TAG, "jsonHeaderId:" + headersList.get(i).getHeaderId());
            Log.i(TAG, "jsonHeaderUpdate :" + headersList.get(i).getUpdate());
            Log.i(TAG, "jsonHeaderDate :" + headersList.get(i).getDate());
            Log.i(TAG, "jsonHeaderRanking  :" + headersList.get(i).getRanking());
            Log.i(TAG, "jsonHeaderTitle :" + headersList.get(i).getTitle());
            Log.i(TAG, "jsonHeaderSubTitle :" + headersList.get(i).getSubTitle());
            Log.i(TAG, "jsonHeaderLink :" + headersList.get(i).getLink());
            Log.i(TAG, "----------------------------------------------------------");
        }
        Log.i(TAG, "Finishing getting headers from json " + headersList.size());
        return headersList;
    }

    public static Article getArticleFromJson(String jsonString) throws JSONException {
        Log.i(TAG, "Starting getting headers from json");
        JSONObject jsonArticle = new JSONObject(jsonString);

        String jsonArticleId = jsonArticle.getString(JSON_ARTICLE_ID);
        String jsonAuthor = jsonArticle.getString(JSON_ARTCILE_AUTHOR);
        String jsonCategoryId = jsonArticle.getString(JSON_ARTICLE_CATEGORY_ID);
        String jsonArticleContent = jsonArticle.getString(JSON_ARTICLE_CONTENT);
        long jsonArticleDate = jsonArticle.getLong(JSON_ARTCILE_DATE);
        Article article = new Article(jsonArticleId, jsonAuthor, jsonCategoryId, jsonArticleContent,
                jsonArticleDate);
        Log.i(TAG, "----------------------------------------------------------");
        Log.i(TAG, "jsonArticleId:" + article.getArticleId());
        Log.i(TAG, "jsonAuthor :" + article.getAuthor());
        Log.i(TAG, "jsonCategoryId :" + article.getCategoryId());
        Log.i(TAG, "jsonArticleContent :" + article.getContent());
        Log.i(TAG, "jsonArticleDate :" + article.getDate());
        Log.i(TAG, "----------------------------------------------------------");

        Log.i(TAG, "Finishing getting headers from json ");
        return article;
    }
}