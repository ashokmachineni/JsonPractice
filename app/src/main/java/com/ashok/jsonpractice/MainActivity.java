package com.ashok.jsonpractice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashok.jsonpractice.events.RecyclerItemClickEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RecyclerItemClickEvent event) {
        /** PLAYER */
        UriSample uriSample = new UriSample("Test", null, null, null, false, event.getUrl(), null);
        startActivity(uriSample.buildIntent(getApplicationContext()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rcview);

        //layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        requestJsonObject();
    }

    private void requestJsonObject() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://thecodeeasy.com/movie/channelpearjson.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<ItemObject> posts = new ArrayList<ItemObject>();
                posts = Arrays.asList(mGson.fromJson(response, ItemObject[].class));
                adapter = new RecyclerViewAdapter(MainActivity.this, posts);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    /** EXOPLAYER */
    private abstract static class Sample {

        public final String name;
        public final boolean preferExtensionDecoders;
        public final UUID drmSchemeUuid;
        public final String drmLicenseUrl;
        public final String[] drmKeyRequestProperties;

        public Sample(String name, UUID drmSchemeUuid, String drmLicenseUrl,
                      String[] drmKeyRequestProperties, boolean preferExtensionDecoders) {
            this.name = name;
            this.drmSchemeUuid = drmSchemeUuid;
            this.drmLicenseUrl = drmLicenseUrl;
            this.drmKeyRequestProperties = drmKeyRequestProperties;
            this.preferExtensionDecoders = preferExtensionDecoders;
        }

        public Intent buildIntent(Context context) {
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra(PlayerActivity.PREFER_EXTENSION_DECODERS, preferExtensionDecoders);
            if (drmSchemeUuid != null) {
                intent.putExtra(PlayerActivity.DRM_SCHEME_UUID_EXTRA, drmSchemeUuid.toString());
                intent.putExtra(PlayerActivity.DRM_LICENSE_URL, drmLicenseUrl);
                intent.putExtra(PlayerActivity.DRM_KEY_REQUEST_PROPERTIES, drmKeyRequestProperties);
            }
            return intent;
        }
    }

    private static final class UriSample extends Sample {

        public final String uri;
        public final String extension;

        public UriSample(String name, UUID drmSchemeUuid, String drmLicenseUrl,
                         String[] drmKeyRequestProperties, boolean preferExtensionDecoders, String uri,
                         String extension) {
            super(name, drmSchemeUuid, drmLicenseUrl, drmKeyRequestProperties, preferExtensionDecoders);
            this.uri = uri;
            this.extension = extension;
        }

        @Override
        public Intent buildIntent(Context context) {
            return super.buildIntent(context)
                    .setData(Uri.parse(uri))
                    .putExtra(PlayerActivity.EXTENSION_EXTRA, extension)
                    .setAction(PlayerActivity.ACTION_VIEW);
        }
    }

    private static final class PlaylistSample extends Sample {

        public final UriSample[] children;

        public PlaylistSample(String name, UUID drmSchemeUuid, String drmLicenseUrl,
                              String[] drmKeyRequestProperties, boolean preferExtensionDecoders,
                              UriSample... children) {
            super(name, drmSchemeUuid, drmLicenseUrl, drmKeyRequestProperties, preferExtensionDecoders);
            this.children = children;
        }

        @Override
        public Intent buildIntent(Context context) {
            String[] uris = new String[children.length];
            String[] extensions = new String[children.length];
            for (int i = 0; i < children.length; i++) {
                uris[i] = children[i].uri;
                extensions[i] = children[i].extension;
            }
            return super.buildIntent(context)
                    .putExtra(PlayerActivity.URI_LIST_EXTRA, uris)
                    .putExtra(PlayerActivity.EXTENSION_LIST_EXTRA, extensions)
                    .setAction(PlayerActivity.ACTION_VIEW_LIST);
        }
    }
}