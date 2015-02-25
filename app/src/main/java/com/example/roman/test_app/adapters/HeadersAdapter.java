package com.example.roman.test_app.adapters;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roman.test_app.R;
import com.example.roman.test_app.data.Header;
import com.koushikdutta.ion.Ion;

import java.util.List;

public class HeadersAdapter extends ArrayAdapter<Header> {
    Context mContext;
    List<Header> mHeaderList;

    public HeadersAdapter(Context context, int resource, List<Header> headerList) {
        super(context, resource, headerList);
        mContext = context;
        mHeaderList = headerList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;

        if (rowView == null) {
            rowView = LayoutInflater.from(mContext).inflate(
                    R.layout.fragment_headers_item, parent, false);
            holder = new ViewHolder();
            holder.mTitleHeaderItem = (TextView) rowView.findViewById(R.id.title_header_item);
            holder.mSubtitleHeaderItem = (TextView) rowView.findViewById(R.id.subtitle_header_item);
            holder.mThumbHeaderItem = (ImageView) rowView.findViewById(R.id.thumb_header_item);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        Header header = mHeaderList.get(position);
        holder.mTitleHeaderItem.setText(header.getTitle());
        holder.mSubtitleHeaderItem.setText(header.getSubTitle());
        if (TextUtils.isEmpty(header.getLink())) {
            holder.mThumbHeaderItem.setVisibility(View.GONE);
        } else {
            holder.mThumbHeaderItem.setVisibility(View.VISIBLE);
            String imageUrl = String.format(header.getLink(), 300,300);
            Ion.with(holder.mThumbHeaderItem).load(imageUrl);
        }
        return rowView;
    }

    private static class ViewHolder {
        public TextView mTitleHeaderItem;
        public TextView mSubtitleHeaderItem;
        public ImageView mThumbHeaderItem;
    }
}
