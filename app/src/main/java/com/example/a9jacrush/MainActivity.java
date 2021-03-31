package com.example.a9jacrush;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private  static  final  int TIME_DELAY=2000;
    private  static  long  back_press;
    AdView mAdView;

    private  WebView ewWebView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.spin);
        progressBar.setVisibility(View.VISIBLE);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViews);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ewWebView =findViewById(R.id.web);

        ewWebView.setWebViewClient(new WebViewClient());

        ewWebView.loadUrl("https://9jacrush.com");
        progressBar.setVisibility(View.GONE);
        WebSettings settings=ewWebView.getSettings();
        settings.setJavaScriptEnabled(true);


    }


    @Override
    public void onBackPressed() {
        if(ewWebView.canGoBack())
            ewWebView.goBack();
        else if(back_press + TIME_DELAY > System.currentTimeMillis()){
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();

        }else{
            Toast.makeText(this, "Press once again to exit ", Toast.LENGTH_SHORT).show();
        }
        back_press=System.currentTimeMillis();

    }
}