package br.com.cep.cep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText editCep;
    EditText editLog;
    EditText editBai;
    EditText editLoc;
    EditText editUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        editCep = (EditText) findViewById(R.id.editCep);
        editLog = (EditText) findViewById(R.id.editLog);
        editBai = (EditText) findViewById(R.id.editBai);
        editLoc = (EditText) findViewById(R.id.editLoc);
        editUf = (EditText) findViewById(R.id.editUf);

        editCep.setText(i.getExtras().getString("cep"));
        editLog.setText(i.getExtras().getString("log"));
        editBai.setText(i.getExtras().getString("bai"));
        editLoc.setText(i.getExtras().getString("loc"));
        editUf.setText(i.getExtras().getString("uf"));

    }
}
