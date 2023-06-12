package kr.ac.tukorea.sady.casualculring.game;

import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.BaseScene;
import kr.ac.tukorea.sady.casualculring.framework.CollisionManager;
import kr.ac.tukorea.sady.casualculring.framework.Metrics;

public class MainScene extends BaseScene {

    private GameControl gc;
    public MainScene() {
//        Metrics.setGameSize(10.0f, 10.0f);

        BackGround bg = new BackGround();
        add(bg);

        BackGroundHouse bg_house = new BackGroundHouse();
        add(bg_house);

        CollisionManager cm = new CollisionManager();
        add(cm);

        gc = new GameControl(cm, this);
        add(gc);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                gc.SetStartPos(x, y);
            }
            return true;
            case MotionEvent.ACTION_MOVE: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                gc.SetTouchPos(x, y);
            }
            return true;
            case MotionEvent.ACTION_UP: {
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                gc.SlideStart();
            }
            return true;

        }
        return super.onTouchEvent(event);
    }
}
