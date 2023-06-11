package kr.ac.tukorea.sady.casualculring.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.sady.casualculring.framework.CollisionManager;
import kr.ac.tukorea.sady.casualculring.framework.IGameObject;
import kr.ac.tukorea.sady.casualculring.framework.PhysicsObject;

public class GameControl implements IGameObject {
    protected boolean isRedTurn = true;

    private final CollisionManager m_cm;

    private PhysicsObject CurrentStone;

    private MainScene m_ms;

    public GameControl(CollisionManager cm, MainScene ms){
        m_cm = cm;
        m_ms = ms;
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

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
