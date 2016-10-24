package com.fifty_five.testga;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context myContext = this;
    private Tracker mTracker;
    private static final String TAG = "MainActivity";
    private Product product = new Product()
            .setId("ID product")
            .setBrand("Brand Product")
            .setCategory("Category Product")
            .setCouponCode("CouponCode Product")
            .setCustomDimension(55, "CustomDim Product")
            .setCustomMetric(55, 42)
            .setName("Name Product")
            .setPosition(55)
            .setPrice(55.42)
            .setQuantity(42)
            .setVariant("Variant Product");

    private Promotion promotion = new Promotion()
            .setCreative("Creative Promotion")
            .setId("ID Promotion")
            .setName("Name Promotion")
            .setPosition("Position Promotion");

    private ProductAction productAction = new ProductAction("ProductAction")
            .setProductActionList("setProductActionList")
            .setCheckoutOptions("setCheckoutOptions")
            .setProductListSource("setProductListSource")
            .setCheckoutStep(777)
            .setTransactionAffiliation("setTransactionAffiliation")
            .setTransactionCouponCode("setTransactionCouponCode")
            .setTransactionId("setTransactionId");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button hitBuilder = (Button) findViewById(R.id.HitBuilder);
        Button eventBuilder = (Button) findViewById(R.id.EventBuilder);
        Button exceptionBuilder = (Button) findViewById(R.id.ExceptionBuilder);
        Button screenViewBuilder = (Button) findViewById(R.id.ScreenViewBuilder);
        Button socialBuilder = (Button) findViewById(R.id.SocialBuilder);
        Button timingBuilder = (Button) findViewById(R.id.TimingBuilder);
        Button tracker = (Button) findViewById(R.id.Tracker);
        Button phone = (Button) findViewById(R.id.Phone);

        hitBuilder.setOnClickListener(hitBuilderListener);
        eventBuilder.setOnClickListener(eventBuilderListener);
        exceptionBuilder.setOnClickListener(exceptionBuilderListener);
        screenViewBuilder.setOnClickListener(screenViewBuilderListener);
        socialBuilder.setOnClickListener(socialBuilderListener);
        timingBuilder.setOnClickListener(timingBuilderListener);
        tracker.setOnClickListener(trackerListener);
        phone.setOnClickListener(phoneListener);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }



    View.OnClickListener hitBuilderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Map<String, String> myMap = new HitBuilders.EventBuilder()
                    .setCategory("EventBuilder -> demo HitBuilder")
                    .setAction("Demo")
                    .setLabel("Label")
                    .addProduct(product)
                    .addImpression(product, "Impression")
                    .addPromotion(promotion)
                    .setCampaignParamsFromUrl("CampaignParamsFromUrl")
                    .setCustomDimension(10, "CustomDim")
                    .setCustomMetric(10, 45f)
                    .setProductAction(productAction)
                    .setPromotionAction("setPromotionAction")
                    .build();
            Log.d("HitBuilder Demo", myMap.toString());
            printTrackerDetails();
            mTracker.send(myMap);
        }
    };

    View.OnClickListener eventBuilderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Map<String, String> myMap = new HitBuilders.EventBuilder()
                    .setAction("Action")
                    .setCategory("Category")
                    .setLabel("Label")
                    .setValue(1200)
                    .build();
            Log.d("EventBuilder", myMap.toString());
            printTrackerDetails();
            mTracker.send(myMap);
        }
    };

    View.OnClickListener exceptionBuilderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Map<String, String> myMap = new HitBuilders.ExceptionBuilder()
                    .setDescription("Description Exception")
                    .setFatal(false)
                    .build();
            Log.d("ExceptionBuilder", myMap.toString());
            Log.d("Tracker", mTracker.toString());
            mTracker.send(myMap);
        }
    };

    View.OnClickListener screenViewBuilderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mTracker.setScreenName("mainActivityScreen");
            Map<String, String> myMap = new HitBuilders.ScreenViewBuilder()
                    .build();
            Log.d("ScreenViewBuilder", myMap.toString());
            printTrackerDetails();
            mTracker.send(myMap);
        }
    };

    View.OnClickListener socialBuilderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Map<String, String> myMap = new HitBuilders.SocialBuilder()
                    .setAction("Action Social")
                    .setNetwork("Network Social")
                    .setTarget("Target Social")
                    .build();
            Log.d("SocialBuilder", myMap.toString());
            printTrackerDetails();
            mTracker.send(myMap);
        }
    };


    View.OnClickListener timingBuilderListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Map<String, String> myMap = new HitBuilders.TimingBuilder()
                    .setCategory("Category Timing")
                    .setLabel("Label Timing")
                    .setValue(5555)
                    .setVariable("Variable Timing")
                    .build();
            Log.d("TimingBuilder", myMap.toString());
            printTrackerDetails();
            mTracker.send(myMap);
        }
    };

    View.OnClickListener trackerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            printTrackerDetails();
        }
    };

    View.OnClickListener phoneListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("MANUFACTURER", Build.MANUFACTURER);
            Log.d("BRAND", Build.BRAND);
            Log.d("MODEL", Build.MODEL);
            Log.d("PRODUCT", Build.PRODUCT);
            Log.d("SDK_INT", String.valueOf(Build.VERSION.SDK_INT));
            Log.d("OS_RELEASE", Build.VERSION.RELEASE);
            task.execute();
        }
    };


    private void printTrackerDetails() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("enableAdvertisingIdCollection", mTracker.get("enableAdvertisingIdCollection"));
        myMap.put("enableAutoActivityTracking", mTracker.get("enableAutoActivityTracking"));
        myMap.put("enableExceptionReporting", mTracker.get("enableExceptionReporting"));
        myMap.put("anonymize", mTracker.get("anonymize"));
        myMap.put("appId", mTracker.get("appId"));
        myMap.put("appInstallerId", mTracker.get("appInstallerId"));
        myMap.put("appName", mTracker.get("appName"));
        myMap.put("appVersion", mTracker.get("appVersion"));
        myMap.put("campaignParamsOnNextHit", mTracker.get("campaignParamsOnNextHit"));
        myMap.put("clientId", mTracker.get("clientId"));
        myMap.put("encoding", mTracker.get("encoding"));
        myMap.put("hostname", mTracker.get("hostname"));
        myMap.put("language", mTracker.get("language"));
        myMap.put("location", mTracker.get("location"));
        myMap.put("page", mTracker.get("page"));
        myMap.put("referrer", mTracker.get("referrer"));
        myMap.put("sampleRate", mTracker.get("sampleRate"));
        myMap.put("screenColors", mTracker.get("screenColors"));
//      "ScreenName", "ScreenName", "screen name", "cd", "Screen Name", "screenname", "SCREEN_NAME", "SCREEN NAME", "SCREENNAME" are not working
        myMap.put("screenName", mTracker.get("screenName"));
        myMap.put("screenResolution", mTracker.get("screenResolution"));
        myMap.put("sessionTimeout", mTracker.get("sessionTimeout"));
        myMap.put("title", mTracker.get("title"));
        myMap.put("useSecure", mTracker.get("useSecure"));
        myMap.put("viewportSize", mTracker.get("viewportSize"));
        Log.d("Tracker", myMap.toString());
        Log.d("Tracker", mTracker.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
            String advertisingID = null;

            try {
                advertisingID = String.valueOf(AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return advertisingID;
        }

        @Override
        protected void onPostExecute(String advertisingID) {
            Log.d("advertisingID", advertisingID);
        }

    };
}
