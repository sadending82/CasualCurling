package kr.ac.tukorea.sady.casualculring.game;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.Metrics;
import kr.ac.tukorea.sady.casualculring.framework.PhysicsObject;
import kr.ac.tukorea.sady.casualculring.framework.Vector2;
public class Stone extends PhysicsObject {

    // 0 - 빨강
    // 1 - 노랑
    private int Color = 0;

    public Stone(int bitmapResId, float cx, float cy, int color) {
        super(bitmapResId, cx, cy, 0.8f, 0.8f);
        Color = color;
    }


    public boolean IsRed() {
        return Color == 0;
    }

    public float GetDistance(Vector2 v) {
        return (float)Math.sqrt((x - v.x)*(x - v.x) + (y - v.x) * (y - v.y));
    }

    @Override
    public void update() {
        super.update();
    }
}
