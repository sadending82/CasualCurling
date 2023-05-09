package kr.ac.tukorea.sady.casualculring.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.game.MainScene;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainScene().pushScene();
    }
}