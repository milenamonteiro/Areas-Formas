package com.example.calcularareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        handleSendText(intent);
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            TextView resultados = findViewById(R.id.textView);
            resultados.setText(sharedText);
        }
    }

    public void voltar(View view){
        Intent sendIntent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(sendIntent);
    }
}
