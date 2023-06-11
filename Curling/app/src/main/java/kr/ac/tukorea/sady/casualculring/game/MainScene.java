package kr.ac.tukorea.sady.casualculring.game;

import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.BaseScene;
import kr.ac.tukorea.sady.casualculring.framework.CollisionManager;
import kr.ac.tukorea.sady.casualculring.framework.Metrics;

public class MainScene extends BaseScene {

    public MainScene() {
//        Metrics.setGameSize(10.0f, 10.0f);

        BackGround bg = new BackGround();
        add(bg);

        BackGroundHouse bg_house = new BackGroundHouse();
        add(bg_house);

        CollisionManager cm = new CollisionManager();
        add(cm);

        Stone r_stone1 = new Stone(R.mipmap.stone_red, 3f, 3.0f);
        add(r_stone1);

        Stone r_stone2 = new Stone(R.mipmap.stone_red, 3.8f, 3.0f);
        add(r_stone2);
       // r_stone.velocity.y = 2.0f;
        Stone y_stone1 = new Stone(R.mipmap.stone_yellow, 1.0f, 9.0f);
        add(y_stone1);
        y_stone1.velocity.x = 1.8f;
        y_stone1.velocity.y = -4f;
        cm.InsertObject(r_stone1);
        cm.InsertObject(r_stone2);
        cm.InsertObject(y_stone1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
            }
            case MotionEvent.ACTION_MOVE: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
            }
            case MotionEvent.ACTION_UP: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
            }
                return true;
        }
        return super.onTouchEvent(event);
    }
}
