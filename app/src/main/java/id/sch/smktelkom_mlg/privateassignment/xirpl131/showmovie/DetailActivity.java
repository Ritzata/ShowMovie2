package id.sch.smktelkom_mlg.privateassignment.xirpl131.showmovie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity{
    private Integer mPostkey = null;
    private static final String URL_DATA = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=c80bc9a66e56420299138e8252d062ed";

    public TextView textViewHeadet;
    public TextView textViewDescet;
    public TextView textViewReview;
    public ImageView imageViewDetail;
    public String url;
    public String urlGambar;
    boolean isPressed = true;
    Place place;
    FloatingActionButton fab;
    boolean isNew;
    ArrayList<Place> pItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostkey = getIntent().getExtras().getInt("blog_id");
        loadRecycleViewData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPressed){
                    doSave();
                    Snackbar.make(view, "Pilihan anda telah ditambahkan ke favorit", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                else {
                    Snackbar.make(view, "Movie Favorit Anda", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        textViewHeadet = (TextView) findViewById(R.id.textViewHeadet);
        textViewDescet = (TextView) findViewById(R.id.textViewDescet);
        textViewReview = (TextView) findViewById(R.id.textViewReview);
        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }

    private void doSave() {
        String title = textViewHeadet.getText().toString();
        String description = textViewDescet.getText().toString();
        String urlgambar = urlGambar;
            place = new Place(title, description, urlgambar);
            place.save();




    }

    private void loadRecycleViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("results");
                            JSONObject o = array.getJSONObject(mPostkey);

                            setTitle(" ");

                            textViewHeadet.setText(o.getString("display_title"));
                            textViewDescet.setText(o.getString("byline"));
                            textViewReview.setText(o.getString("summary_short"));
                            url = o.getJSONObject("link").getString("url");
                            Glide
                                    .with(DetailActivity.this)
                                    .load(o.getJSONObject("multimedia").getString("src"))
                                    .into(imageViewDetail);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
