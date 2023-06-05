package kr.ac.tukorea.sady.casualculring.framework;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollisionManager implements IGameObject {

    ArrayList<PhysicsObject> HasCollideObjects;
    HashMap<Integer, Pair> OnCollisionObjects;

    CollisionManager() {
        OnCollisionObjects = new HashMap<>(140);
    }

    @Override
    public void update() {
        // 일단은 충돌이 된 객체를 찾기.
        for(int i = 0; i < HasCollideObjects.size(); i++){
            for (int j = 0; j < HasCollideObjects.size(); j++){
                // 이미 충돌이 되고 있는 객체라면 넘기기
                if(OnCollisionObjects.containsKey(i*j)) continue;

                // 그 외에 충돌이 되었는지 확인
                if(HasCollideObjects.get(i).isCollide(HasCollideObjects.get(j))) {
                    // 충돌이 되었으면 두 객체의 충돌처리를 진행한 뒤, 충돌 중인 객체에 추가하기
                    HasCollideObjects.get(i).Collision(HasCollideObjects.get(j));
                    OnCollisionObjects.put(i*j, new Pair(HasCollideObjects.get(i), HasCollideObjects.get(j)));
                }
            }
        }

        // 충돌이 끝난 객체를 찾기.

        // 충돌 중인게 없으면 끝
        if(OnCollisionObjects.isEmpty()) return;

        // 충돌 중인 모든 객체들이 여전히 충돌 중인지 확인하기.
        for (Map.Entry<Integer, Pair> entrySet : OnCollisionObjects.entrySet()) {
            if(!entrySet.getValue().first.isCollide(entrySet.getValue().second)){

                // 충돌 중이 아니라면 충돌이 끝났다는 것을 알려주고 충돌 중인 객체에서 제외
                entrySet.getValue().first.CollisionExit(entrySet.getValue().second);
                entrySet.getValue().second.CollisionExit(entrySet.getValue().first);

                OnCollisionObjects.remove(entrySet.getKey());
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void InsertObject(PhysicsObject phy) {
        HasCollideObjects.add(phy);
    }
}
