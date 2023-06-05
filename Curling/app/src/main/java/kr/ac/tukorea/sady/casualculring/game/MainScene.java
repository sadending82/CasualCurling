package kr.ac.tukorea.sady.casualculring.game;

import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.BaseScene;
import kr.ac.tukorea.sady.casualculring.framework.CollisionManager;
import kr.ac.tukorea.sady.casualculring.framework.Metrics;

public class MainScene extends BaseScene {
    private BackGround bg;
    private BackGroundHouse bg_house;

    private CollisionManager cm;
    public MainScene() {
//        Metrics.setGameSize(10.0f, 10.0f);

        bg = new BackGround();
        add(bg);

        bg_house = new BackGroundHouse();
        add(bg_house);

        Stone r_stone = new Stone(R.mipmap.stone_red, 1.0f, 1.0f);
        add(r_stone);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                return true;
        }
        return super.onTouchEvent(event);
    }
}
