package com.haz.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOBLIN on 11/20/2016.
 */
public class list extends BaseAdapter {

    Context context;
    Activity a;
    List<adaptervb> daftardetail;
    ArrayList<adaptervb> filterData;
    LayoutInflater inflater;
    TextView txtid,txtnama,txtket,txt_url_image;
    ImageView imggambar;


    @SuppressWarnings({"unchecked", "rawtypes"})
    public list(Context paramContext, List<adaptervb> paramList) {
        this.daftardetail = paramList;
        this.inflater = ((LayoutInflater) paramContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        this.context = paramContext;
        this.filterData = new ArrayList();
        this.filterData.addAll(paramList);
    }

    public int getCount() {
        return this.daftardetail.size();
    }

    @SuppressLint({"DefaultLocale"})
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            protected FilterResults performFiltering(
                    CharSequence paramCharSequence) {
                paramCharSequence = paramCharSequence.toString().toLowerCase();
                FilterResults localFilterResults = new FilterResults();
                if ((paramCharSequence != null)
                        && (paramCharSequence.toString().length() > 0)) {
                    @SuppressWarnings("rawtypes")
                    ArrayList localArrayList = new ArrayList();
                    int i = 0;
                    int j = list.this.filterData.size();
                    while (true) {
                        if (i >= j) {
                            localFilterResults.count = localArrayList.size();
                            localFilterResults.values = localArrayList;
                            return localFilterResults;
                        }
                        adaptervb locallistdetail = (adaptervb) list.this.filterData
                                .get(i);
                        if ((locallistdetail.getId().toString().toLowerCase()
                                .contains(paramCharSequence))
                                || (locallistdetail.getNama().toString()
                                .toLowerCase()
                                .contains(paramCharSequence)))
                            localArrayList.add(locallistdetail);
                        i += 1;
                    }
                }
                // monitorenter;
                try {
                    localFilterResults.values = list.this.filterData;
                    localFilterResults.count = list.this.filterData.size();
                    return localFilterResults;
                } finally {
                    // monitorexit;
                }
                // throw paramCharSequence;
            }

            @SuppressWarnings({"unchecked", "rawtypes"})
            protected void publishResults(CharSequence paramCharSequence,
                                          FilterResults paramFilterResults) {
                list.this.daftardetail = ((ArrayList) paramFilterResults.values);
                if (paramFilterResults.count > 0) {
                    list.this.notifyDataSetChanged();
                    return;
                }
                list.this.notifyDataSetInvalidated();
            }
        };
    }

    public Object getItem(int paramInt) {
        return null;
    }

    public long getItemId(int paramInt) {
        return 0L;
    }

    @SuppressLint("InflateParams")
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {

        final adaptervb locallistdetail = (adaptervb) this.daftardetail.get(paramInt);
        if (paramView == null)
            paramView = inflater.inflate(R.layout.adaptervb, null);
        this.txtid = ((TextView) paramView.findViewById(R.id.textView));
        this.txtnama = ((TextView) paramView.findViewById(R.id.textView2));
        this.txtket = ((TextView) paramView.findViewById(R.id.textView3));
        this.txt_url_image = ((TextView) paramView.findViewById(R.id.textView6));
        this.imggambar = ((ImageView) paramView.findViewById(R.id.imageView));

        this.txtid.setText(locallistdetail.getId());
        this.txtnama.setText(locallistdetail.getNama());
        this.txtket.setText(locallistdetail.getKet());
        this.txt_url_image.setText(locallistdetail.getImage());

        try
        {
            // get input stream
            InputStream ims = context.getAssets().open(locallistdetail.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imggambar.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
        }
        return paramView;
    }
}
