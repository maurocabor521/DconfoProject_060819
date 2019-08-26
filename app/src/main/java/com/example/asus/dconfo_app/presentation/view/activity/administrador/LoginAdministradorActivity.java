package com.example.asus.dconfo_app.presentation.view.activity.administrador;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.Administrador;
import com.example.asus.dconfo_app.domain.model.Estudiante;
import com.example.asus.dconfo_app.domain.model.VolleySingleton;
import com.example.asus.dconfo_app.helpers.Globals;
import com.example.asus.dconfo_app.presentation.view.activity.docente.LoginDocenteActivity;
import com.example.asus.dconfo_app.presentation.view.activity.estudiante.HomeEstudianteActivity;
import com.example.asus.dconfo_app.presentation.view.activity.estudiante.LoginEstudianteActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginAdministradorActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    private EditText edt_email;
    private EditText edt_pass;
    private Button btn_ingresar;
    private int iddconte_bundle = 0;
    private String namedocente_bundle;
    ProgressDialog progreso;

    //******** CONEXIÓN CON WEBSERVICE
    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = (EditText) findViewById(R.id.edt_login_doc_email);
        edt_pass = (EditText) findViewById(R.id.edt_login_doc_password);
        btn_ingresar = (Button) findViewById(R.id.btn_Login_Docente);
        progreso = new ProgressDialog(this);
        progreso = new ProgressDialog(getApplicationContext());
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargarWebService();
                cargarWebService1();
            }
        });
    }

    private void cargarWebService1() {

      /*  progreso.setMessage("Consultando...");
        progreso.show();*/

        //int cod=Integer.parseInt(edt_codigo.getText().toString());
        String email = edt_email.getText().toString();
        String password = edt_pass.getText().toString();

        String url_lh = Globals.url;
        // String ip = getString(R.string.ip);

        //String url = "http://192.168.0.13/" +
        String url = "http://" + url_lh + "/" +
                //"ejemploBDRemota/wsJSONConsultarUsuario.php?documento=" + campoDocumento.getText().toString();
                "proyecto_dconfo_v1/30wsJSONConsultarAdministrador.php?passwork_administrador=" + password + "&emailAdministrador=" + email;
        // Toast.makeText(getApplicationContext(), "Mensaje: " + cod, Toast.LENGTH_SHORT).show();
        // String url = ip+"ejemploBDRemota/wsJSONConsultarUsuarioImagen.php?documento=" + campoDocumento.getText().toString();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);//p21
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se ha realizado la consulta de usuario" , Toast.LENGTH_LONG).show();
        Log.i("ERROR", error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        //Toast.makeText(getApplicationContext(), "Mensaje: " + response.toString(), Toast.LENGTH_SHORT).show();


        JSONArray json = response.optJSONArray("adminstrador");
        JSONObject jsonObject = null;

        try {
            jsonObject = json.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // jsonObject = new JSONObject(response);

    /*    if (usuario == "administrador") {

        } else if (usuario == "docente") {*/
        Administrador administrador = new Administrador();
        administrador.setIdAdminstrador(jsonObject.optInt("idAdminstrador"));
        administrador.setNameAdminstrador(jsonObject.optString("nameAdminstrador"));

        if (administrador.getIdAdminstrador() == 0) {
            Toast.makeText(getApplicationContext(), "Incorrecto Correo o Email " , Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginAdministradorActivity.this, Home_AdminActivity.class);
            int idadministrador = administrador.getIdAdminstrador();
            String nameadministrador = administrador.getNameAdminstrador();
            intent.putExtra("idadministrador", idadministrador);
            intent.putExtra("nameadministrador", nameadministrador);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "json: " + json.length(), Toast.LENGTH_SHORT).show();
            System.out.println("Json: "+jsonObject);
        }
    }
}
