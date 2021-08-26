package com.example.studentmanagementapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText fullname, gender, course, semester, year;
    Button Submit;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullname = (EditText) findViewById(R.id.fullname);
        gender = (EditText) findViewById(R.id.gender);
        course = (EditText) findViewById(R.id.course);
        semester = (EditText) findViewById(R.id.semester);
        year = (EditText) findViewById(R.id.year);
        queue = Volley.newRequestQueue(this);

        Submit = (Button) findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(fullname.getText().toString()) || TextUtils.isEmpty(gender.getText().toString()) || TextUtils.isEmpty(course.getText().toString()) || TextUtils.isEmpty(semester.getText().toString()) || TextUtils.isEmpty(year.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Fill all Fields..!!", Toast.LENGTH_LONG).show();
                }else{
                    final Map<String, Object> data = new HashMap<>();

                    data.put("fullname",fullname.getText().toString());
                    data.put("gender",gender.getText().toString());
                    data.put("course",course.getText().toString());
                    data.put("semester",semester.getText().toString());
                    data.put("year",year.getText().toString());
                    String url = "http://192.168.43.54:1919/management/students";
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("", "work");


                        }
                    }, error -> {
                        error.printStackTrace();
                        VolleyLog.e("Error: ", error.getMessage());
                        Toast.makeText(MainActivity.this, "inserted succesfully", Toast.LENGTH_SHORT).show();
                    });

                    queue.add(request);
                }
            }
        });
    }

    public void clear(View view) {
        fullname.getText().clear();
        gender.getText().clear();
        course.getText().clear();
        semester.getText().clear();
        year.getText().clear();
    }
}