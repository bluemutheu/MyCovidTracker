package android.ke.mycovidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView totalCasesWorld, totalDeathsWorld, totalRecoveredWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the objects
        totalCasesWorld = findViewById(R.id.newCasesWorld);
        totalDeathsWorld = findViewById(R.id.newDeathsWorld);
        totalRecoveredWorld = findViewById(R.id.newRecoveredWorld);

        getData();


        // You can also use the format below instead of creating your own method.

//        String myUrl = "https://corona.lmao.ninja/v2/all";

//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        final JsonObjectRequest objectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                myUrl,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            totalCasesWorld.setText(response.getString("cases"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            totalRecoveredWorld.setText(response.getString("recovered"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            totalDeathsWorld.setText(response.getString("deaths"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//                },
//
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                    }
//                }
//        );
//
//        requestQueue.add(objectRequest);
    }

    private void getData() {

        String myUrl = "https://corona.lmao.ninja/v2/all";
        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                   try{
                        //Create a JSON object containing information from the API.
                       JSONObject myJsonObject = new JSONObject(response);
                       totalCasesWorld.setText(myJsonObject.getString("cases"));
                        totalRecoveredWorld.setText(myJsonObject.getString("recovered"));
                        totalDeathsWorld.setText(myJsonObject.getString("deaths"));
                    } catch (JSONException e) {
                       e.printStackTrace();
                   }
                },
                volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
       );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }



}
