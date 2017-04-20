package br.com.cep.cep;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editTextCEP;
    Button buttonCEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCEP = (EditText) findViewById(R.id.editTextCEP);
        buttonCEP = (Button) findViewById(R.id.buttonCEP);

        buttonCEP.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String cep = null;

                try {

                    cep = editTextCEP.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }
                NetworkCall myCall = new NetworkCall();

                myCall.execute("http://viacep.com.br/ws/"+cep+"/json/");
            }
        });
    }

    public class NetworkCall extends AsyncTask<String, Void, String> {

        // Esse é o método que executa a tarefa em segundo plano
        @Override
        protected String doInBackground(String... params) {
            try {
                // Cria o objeto de conexão
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();

                // Executa a requisição pegando os dados
                InputStream in = urlConnection.getInputStream();

                // Cria um leitor para ler a resposta
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder resultado = new StringBuilder();
                String linha = bufferedReader.readLine();

                // Lê linha a linha a resposta e armazena no StringBuilder
                while (linha != null) {
                    resultado.append(linha);
                    linha = bufferedReader.readLine();
                }

                // Transforma o StringBuilder em String, que contém a resposta final
                String respostaCompleta = resultado.toString();

                // Retorna a string final contendo a resposta retornada
                return respostaCompleta;

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Caso tenha dado algum erro, retorna null
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                // Cria um objeto JSON a partir da resposta
                JSONObject json = new JSONObject(result);

                // Verifica o tipo de operação e pega o dado correspondente do JSON
                String finalResult = "";

                //finalResult = json.getString("cep");


                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("cep", json.getString("cep"));

                intent.putExtra("log", json.getString("logradouro"));
                intent.putExtra("bai", json.getString("bairro"));
                intent.putExtra("loc", json.getString("localidade"));
                intent.putExtra("uf", json.getString("uf"));
                startActivity(intent);
                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}

