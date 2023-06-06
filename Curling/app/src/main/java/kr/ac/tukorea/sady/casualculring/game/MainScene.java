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

        cm = new CollisionManager();
        add(cm);

        Stone r_stone = new Stone(R.mipmap.stone_red, 1.2f, 1.0f);
        add(r_stone);
        r_stone.velocity.y = 2.0f;
        Stone y_stone = new Stone(R.mipmap.stone_yellow, 1.0f, 6.0f);
        add(y_stone);
//        y_stone.velocity.y = -1.0f;
        cm.InsertObject(r_stone);
        cm.InsertObject(y_stone);
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
