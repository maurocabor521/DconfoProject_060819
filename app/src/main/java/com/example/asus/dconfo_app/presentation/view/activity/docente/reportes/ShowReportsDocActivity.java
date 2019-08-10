package com.example.asus.dconfo_app.presentation.view.activity.docente.reportes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.dconfo_app.R;
import com.example.asus.dconfo_app.domain.model.TemplateReportePDF;
import com.example.asus.dconfo_app.presentation.view.contract.CategoriaEjerciciosContract;

import java.util.ArrayList;

public class ShowReportsDocActivity extends AppCompatActivity {

    private String[] header = {"Id", "Nombre", "Apellido"};
    private String shortText = "Hola";
    private String longText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
            "when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
    private TemplateReportePDF templateReportePDF;
    private Button btn_pdf;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL=1;
    EditText edt_pdf_doc;
    String nameArchivo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reports_doc);

        btn_pdf = findViewById(R.id.btn_pdf_doc);
        edt_pdf_doc = findViewById(R.id.edt_pdf_doc);
        nameArchivo=edt_pdf_doc.getText().toString();

        checkPermission();



    }

    private void crearTemplete() {
        if (nameArchivo!=""){
            templateReportePDF = new TemplateReportePDF(getApplicationContext());
            templateReportePDF.openDocument(nameArchivo);
            templateReportePDF.addMetaData("Cursos", "Informes", "Juan Valdez");
            templateReportePDF.addTitle("Curso 1-A", "Asignatura", "10/08/19");
            templateReportePDF.addParagraph(shortText);
            templateReportePDF.addParagraph(longText);
            templateReportePDF.createTable(header, getClients());
            templateReportePDF.closeDocument();
        }
    }

    //verificar permisos lectura y escritura Sd
    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(ShowReportsDocActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ShowReportsDocActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getApplicationContext(), "Permiso escritura en memoria SD", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(ShowReportsDocActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL);
            }
        }
    }

    public void pdfview(View view) {
        crearTemplete();
        templateReportePDF.viewPdf();
    }

    private ArrayList<String[]> getClients() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1", "Pedro", "Lopez"});
        rows.add(new String[]{"2", "David", "Amaral"});
        rows.add(new String[]{"3", "Lina", "Aldana"});
        rows.add(new String[]{"4", "Sebastian", "Arias"});
        return rows;
    }


}
