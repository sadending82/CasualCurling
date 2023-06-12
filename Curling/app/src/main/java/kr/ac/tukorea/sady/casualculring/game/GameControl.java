package kr.ac.tukorea.sady.casualculring.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.sady.casualculring.R;
import kr.ac.tukorea.sady.casualculring.framework.CollisionManager;
import kr.ac.tukorea.sady.casualculring.framework.IGameObject;
import kr.ac.tukorea.sady.casualculring.framework.PhysicsObject;
import kr.ac.tukorea.sady.casualculring.framework.Vector2;

public class GameControl implements IGameObject {
    protected boolean isRedTurn = false;

    boolean isWaitPlayerControl = false;

    private final CollisionManager m_cm;

    private PhysicsObject CurrentStone;

    private Vector2 StartPos;

    private Vector2 PosFromTouchEvent;

    private MainScene m_ms;

    private float IncreasedForce = 2.0f;

    public GameControl(CollisionManager cm, MainScene ms){
        m_cm = cm;
        m_ms = ms;
        StartPos = new Vector2(0.f, 0.f);
        PosFromTouchEvent = new Vector2(0.f, 0.f);
        CurrentStone = null;
    }

    public void SetCurrentStone(PhysicsObject phy) {
        CurrentStone = phy;
    }

    public PhysicsObject GetCurrentStone() {
        return CurrentStone;
    }

    public boolean IsStonesAllStop() {
        for(PhysicsObject phy : m_cm.GetHasCollideObjects())
        {
            if(phy.velocity.magnitude() > 0.f){
                return false;
            }
        }

        return true;
    }

    @Override
    public void update() {

        if(!isWaitPlayerControl) {
            if (IsStonesAllStop()) {
                int ret = TurnChange();
                if (ret == -1){
                    GameEnd();
                }
            }
        }

    }

    void GameEnd(){
        
    }

    public void SlideStart() {
        if(isWaitPlayerControl) {
            isWaitPlayerControl = false;
            CurrentStone.velocity.x = (StartPos.x - PosFromTouchEvent.x) * IncreasedForce;
            CurrentStone.velocity.y = (StartPos.y - PosFromTouchEvent.y) * IncreasedForce;
        }
    }

    public void SetTouchPos(float x, float y) {
        PosFromTouchEvent.x = x;
        PosFromTouchEvent.y = y;
    }

    public void SetStartPos(float x, float y) {
        StartPos.x = x;
        StartPos.y = y;
    }

    int TurnChange() {
        if(m_cm.GetHasCollideObjects().size() >= 18){
            return -1;
        }

        isRedTurn = !isRedTurn;
        isWaitPlayerControl = true;

        if(isRedTurn){
            Stone r_stone = new Stone(R.mipmap.stone_red, 4.5f, 13.0f);
            m_ms.add(r_stone);
            m_cm.InsertObject(r_stone);
            CurrentStone = r_stone;
        }
        else {
            Stone y_stone = new Stone(R.mipmap.stone_yellow, 4.5f, 13.0f);
            m_ms.add(y_stone);
            m_cm.InsertObject(y_stone);
            CurrentStone = y_stone;
        }

        return 0;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
