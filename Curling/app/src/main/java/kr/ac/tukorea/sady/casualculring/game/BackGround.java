package kr.ac.tukorea.sady.casualculring.game;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.Metrics;
import kr.ac.tukorea.sady.casualculring.framework.Sprite;

public class BackGround extends Sprite {


    public BackGround() {
        super(R.mipmap.curling_map_ground, Metrics.game_width/2f, Metrics.game_height/2f, Metrics.game_height*2 , Metrics.game_height *2);
    }
}
