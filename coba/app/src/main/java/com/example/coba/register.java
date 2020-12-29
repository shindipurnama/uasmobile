package com.example.coba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    EditText NAMA, EMAIL_PASIEN, PASSWORD_PASIEN;
    Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        NAMA=findViewById(R.id.NAMA);
        EMAIL_PASIEN=findViewById(R.id.EMAIL_PASIEN);
        PASSWORD_PASIEN=findViewById(R.id.PASSWORD_PASIEN);

        buttonRegister=findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    void register(){
        String url="http://192.168.56.1/uasmobile/insert_pasien.php"; //url api nyambung di back end php
        StringRequest respon=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equals("oke")){
                                AlertDialog.Builder builder= new AlertDialog.Builder(register.this);
                                builder.setTitle("Success");
                                builder.setMessage("You're already register");
                                builder.setPositiveButton("Oke",
                                        new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                                AlertDialog dialog= builder.create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> form=new HashMap<>();
                form.put("NAMA", NAMA.getText().toString());
                form.put("EMAIL_PASIEN", EMAIL_PASIEN.getText().toString());
                form.put("PASSWORD_PASIEN", PASSWORD_PASIEN.getText().toString());
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(respon);
    }
}