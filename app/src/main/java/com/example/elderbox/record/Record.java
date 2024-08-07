// Ref: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/

package com.example.elderbox.record;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elderbox.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Record extends AppCompatActivity {

    //URL to get contacts JSON
    private static String url = "http://10.0.2.2/elderbox/getScores_json.php";
    private ListView lv;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        lv = (ListView) findViewById(R.id.list);

        new JsonTask().execute(url);
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Record.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }

            // JSON to ArrayList
            ArrayList<Scores> arrayList = convert_ScoresJSonToArrayList(result);
            Log.v("Result: ", "> " + arrayList);

            //ArrayList to Array
            Scores[] scores_data = arrayList.toArray(new Scores[arrayList.size()]);
            ScoresAdapter adpater = new ScoresAdapter(Record.this,R.layout.list_item_record,scores_data);
            lv.setAdapter(adpater);

        }


        private ArrayList<Scores> convert_ScoresJSonToArrayList (String jsonStr) {
            JSONObject jsonObjTable;

            ArrayList<Scores> arrayList = new ArrayList<Scores>();

            try {
                jsonObjTable = new JSONObject(jsonStr);

                //get the table
                JSONArray jsonArrayTable = jsonObjTable.getJSONArray("scores");

                // looping through All Contacts
                for (int i=0; i<jsonArrayTable.length(); i++) {
                    //get on row
                    JSONObject jsonObjRow = jsonArrayTable.getJSONObject(i);
                    Scores scores = new Scores();
                    scores.playername = jsonObjRow.getString("playername");
                    scores.score1 = jsonObjRow.getInt("score1");
                    scores.score2 = jsonObjRow.getInt("score2");
                    scores.score3 = jsonObjRow.getInt("score3");
                    scores.score4 = jsonObjRow.getInt("score4");

                    arrayList.add(scores);
                }

            } catch (JSONException e) {
                Log.v("myLog", e.toString());
            }

            return arrayList;

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

