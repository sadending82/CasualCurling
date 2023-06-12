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

    Vector2 Center = new Vector2(4.5f, 2.4f);
    float HouseSizeRadius = 2.4f;

    public int RedScore = 0;
    public int YellowScore = 0;

    public int CurRedScore = 0;
    public int CurYellowScore = 0;

    ArrayList<Stone> ExistStones = new ArrayList<>();

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
            if(phy.GetX() < 9.0f - phy.GetSize() && phy.GetY() < 16.0 - phy.GetSize() &&
            phy.GetX() > 0.0f + phy.GetSize() && phy.GetY() > 0.0f + phy.GetSize()) {
                if (phy.velocity.magnitude() > 0.f) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void update() {

        if(!isWaitPlayerControl) {
            if (IsStonesAllStop()) {
                if(CurrentStone != null) {
                    // 현재 스코어를 계산한다.
                    CalcScore();
                }
                int ret = TurnChange();
                if (ret == -1){ // -1이 전달되면 더이상 넣을 수 없는 것이다.
                    GameEnd();
                }
            }
        }

    }

    void CalcScore() {

        ExistStones.sort((a, b) -> {
            return Float.compare(a.GetDistance(Center), b.GetDistance(Center));
        });

        if(ExistStones.get(0).GetDistance(Center) < HouseSizeRadius + ExistStones.get(0).GetSize()) {
            if (ExistStones.get(0).IsRed()) {
                CurRedScore = 1;
                if (ExistStones.size() > 1) {
                    for (int i = 1; ExistStones.get(i).IsRed(); i++) {
                        if(ExistStones.get(i).GetDistance(Center) < HouseSizeRadius + ExistStones.get(i).GetSize()) {
                            CurRedScore++;
                        }
                    }
                }
            } else {
                CurYellowScore = 1;
                if (ExistStones.size() > 1) {
                    for (int i = 1; !ExistStones.get(i).IsRed(); i++) {
                        if(ExistStones.get(i).GetDistance(Center) < HouseSizeRadius + ExistStones.get(i).GetSize()) {
                            CurYellowScore++;
                        }
                    }
                }
            }
        }

    }

    void GameEnd(){
        RedScore += CurRedScore;
        YellowScore += CurYellowScore;

        ResetGame();
    }

    void ResetGame() {
        CurrentStone = null;

       for(Stone st : ExistStones) {
           m_cm.RemoveObject(st);
           m_ms.remove(st);
       }

       ExistStones.clear();
    }

    public void SlideStart() {
        if(isWaitPlayerControl) {
            if(StartPos.distance(PosFromTouchEvent) > 0.7f) {
                isWaitPlayerControl = false;
                CurrentStone.velocity.x = (StartPos.x - PosFromTouchEvent.x) * IncreasedForce;
                CurrentStone.velocity.y = (StartPos.y - PosFromTouchEvent.y) * IncreasedForce;
            }
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
        if(m_cm.GetHasCollideObjects().size() >= 16){
            return -1;
        }

        isRedTurn = !isRedTurn;
        isWaitPlayerControl = true;

        if(isRedTurn){
            Stone r_stone = new Stone(R.mipmap.stone_red, 4.5f, 13.0f, 0);
            m_ms.add(r_stone);
            m_cm.InsertObject(r_stone);
            CurrentStone = r_stone;
            ExistStones.add(r_stone);
        }
        else {
            Stone y_stone = new Stone(R.mipmap.stone_yellow, 4.5f, 13.0f, 1);
            m_ms.add(y_stone);
            m_cm.InsertObject(y_stone);
            CurrentStone = y_stone;
            ExistStones.add(y_stone);
        }

        return 0;
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
