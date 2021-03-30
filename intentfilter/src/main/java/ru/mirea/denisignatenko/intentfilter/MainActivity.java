package ru.mirea.denisignatenko.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonBrowser;
    private View shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBrowser = findViewById(R.id.buttonBrowser);
        shareButton = findViewById(R.id.shareButton);

        buttonBrowser.setOnClickListener(this::buttonBrowser);
        shareButton.setOnClickListener(this::shareButton);

    }

    protected void buttonBrowser(View v){
        Uri address = Uri.parse("https://www.mirea.ru/");
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        if (openLinkIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openLinkIntent);
        } else {
            Log.d("Intent", "Не получается обработать намерение!");
        }
    }

    protected void shareButton(View v){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "ФАМИЛИЯ ИМЯ ОТЧЕСТВО");
        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
    }
}