package com.example.sman1batam.View;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sman1batam.R;

import java.net.URLEncoder;

public class ViewJadwalHarian extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jadwal_harian);

        webView =(WebView)findViewById(R.id.viewHarian);
        webView.getSettings().setJavaScriptEnabled(true);

        String namaJadwal=getIntent().getStringExtra("namaJadwal");
        String document= getIntent().getStringExtra("document");

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle(namaJadwal);
        pd.setMessage("Opening....!!!");


        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url="";
        try {
            url= URLEncoder.encode(document,"UTF-8");
        }catch (Exception ex)
        {}

        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);

    }
}