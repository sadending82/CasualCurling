package kr.ac.tukorea.sady.casualculring.game;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.BaseScene;
import kr.ac.tukorea.sady.casualculring.framework.Metrics;
import kr.ac.tukorea.sady.casualculring.framework.Sprite;

public class Ball extends Sprite {
    private float dx, dy;

    public Ball(float dx, float dy) {
        super(R.mipmap.stone_red, 2.0f, 2.0f, 0.5f, 0.5f);
        this.dx = dx;
        this.dy = dy;

        fixDstRect();
    }

    @Override
    public void update() {
        dstRect.offset(dx * BaseScene.frameTime, dy * BaseScene.frameTime);
        if (dx > 0) {
            if (dstRect.right > Metrics.game_width) {
                dx = -dx;
            }
        } else {
            if (dstRect.left < 0) {
                dx = -dx;
            }
        }
        if (dy > 0) {
            if (dstRect.bottom > Metrics.game_height) {
                dy = -dy;
            }
        } else {
            if (dstRect.top < 0) {
                dy = -dy;
            }
        }
    }
}
