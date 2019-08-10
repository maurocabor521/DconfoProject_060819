package com.example.asus.dconfo_app.presentation.view.activity.docente.reportes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.dconfo_app.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ReportsDocActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_doc);
        pdfView=findViewById(R.id.pdfView);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            //obtiene direccion
            file=new File(bundle.getString("path",""));
        }
        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();

    }
}
