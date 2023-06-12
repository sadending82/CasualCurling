package kr.ac.tukorea.sady.casualculring.framework;

public class PhysicsObject extends Sprite {

    public Vector2 velocity;
    public float friction; // 마찰력
    public float elasticity; // 탄성력

    public float mass = 1.0f;

    protected float size = 0.4f;

    public PhysicsObject(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        velocity = new Vector2(0.f, 0.f);
        friction = 1.0f;
        elasticity = 0.0f;
    }

    @Override
    public void update() {
        if(velocity.magnitude() != 0f) {
            dstRect.offset(velocity.x * BaseScene.frameTime, velocity.y * BaseScene.frameTime);
            x += velocity.x * BaseScene.frameTime;
            y += velocity.y * BaseScene.frameTime;


            Vector2 frictionVector = new Vector2(velocity.x, velocity.y);
            frictionVector.normalize();

            Vector2 normalVel = new Vector2(velocity.x, velocity.y);
            normalVel.normalize();

            // 속도가 양수 -> 음수 또는 음수 -> 양수로 변하는 경우, 마찰력의 방향이 반대가 되는 것이므로
            // 이 부분은 수정이 필요하다.

            if(velocity.x > 0 && velocity.x - normalVel.x * friction * BaseScene.frameTime < 0f){
                    velocity.x = 0f;
                    velocity.y = 0f;
            }
            else if(velocity.x < 0 && velocity.x - normalVel.x * friction * BaseScene.frameTime > 0f) {
                    velocity.x = 0f;
                    velocity.y = 0f;
            }
            else if(velocity.y > 0 && velocity.y - normalVel.x * friction * BaseScene.frameTime < 0f){
                velocity.x = 0f;
                velocity.y = 0f;
            }
            else if(velocity.y < 0 && velocity.y - normalVel.x * friction * BaseScene.frameTime > 0f) {
                velocity.x = 0f;
                velocity.y = 0f;
            }
            else {
                velocity.x -= normalVel.x * friction * BaseScene.frameTime;
                velocity.y -= normalVel.y * friction * BaseScene.frameTime;
            }

            if (velocity.magnitude() < 0.005f) {
                velocity.x = 0f;
                velocity.y = 0f;
            }
        }
    }

    public boolean isCollide(PhysicsObject phy) {
        float dist = (float)Math.sqrt( Math.pow(phy.x - x, 2) + Math.pow(phy.y - y, 2) );

        return dist <= size + phy.size;
    }

    public void CollisionExit(PhysicsObject phy) {

    }

    public void Collision(PhysicsObject phy) {

        // 두 물체 각각 컨택트 포인트로 향하는 벡터 (정규화 된 것)을 구한다
        // 이는 충돌 벡터라고 명명.
        Vector2 PhyCenterToContact = new Vector2();
        PhyCenterToContact.x = x - phy.x;
        PhyCenterToContact.y = y - phy.y;
        PhyCenterToContact.normalize();

        Vector2 ThisCenterToContact = new Vector2();
        ThisCenterToContact.x = phy.x - x;
        ThisCenterToContact.y = phy.y - y;
        ThisCenterToContact.normalize();

        // 노말 벡터를 구한다(이는 그저 90도를 회전시킨 벡터임)
        Vector2 PhyNormal = new Vector2(-PhyCenterToContact.y, PhyCenterToContact.x);
        Vector2 ThisNormal = new Vector2(-ThisCenterToContact.y, ThisCenterToContact.x);

        // 속도 벡터를 충돌 벡터와 노말 벡터 각각에 투영 시킨다.
        // 이렇게 되면 충돌 벡터에 투영된 것은 부딪힌 속도가 되고,
        // 노말벡터에 투영된 것은 그와 완전 수직인 속도가 된다.

        Vector2 PhyVelocity = phy.velocity;
        Vector2 ThisVelocity = velocity;

        // 받아온 거에 대한 계산
        // 충돌 벡터
        // 투영된 벡터의 길이
        float PhyColProjLength = PhyVelocity.dot(PhyCenterToContact);

        // 투영이 되었으므로 방향은 충돌 벡터 방향임.
        Vector2 PhyColProj = new Vector2(PhyCenterToContact.x * PhyColProjLength,
                PhyCenterToContact.y * PhyColProjLength);

        // 노말 벡터
        float PhyNormProjLength = PhyVelocity.dot(PhyNormal);

        Vector2 PhyNormProj = new Vector2(PhyNormal.x * PhyNormProjLength,
                PhyNormal.y * PhyNormProjLength);

        // 이 오브젝트에 대한 계산
        // 충돌 벡터
        float ThisColProjLength = ThisVelocity.dot(ThisCenterToContact);

        Vector2 ThisColProj = new Vector2(ThisCenterToContact.x * ThisColProjLength,
                ThisCenterToContact.y * ThisColProjLength);

        // 노말 벡터
        float ThisNormProjLength = ThisVelocity.dot(ThisNormal);

        Vector2 ThisNormProj = new Vector2(ThisNormal.x * ThisNormProjLength,
                ThisNormal.y * ThisNormProjLength);

        // 충돌 벡터에 대해서는 교환해 주고, 노말 벡터는 그대로 둔 뒤, 더 해주면 된다.

        // 받아 온 것
        // 현재 오브젝트의 충돌 벡터 + 받아 온 것의 노말 벡터
        Vector2 NewPhyVel = new Vector2(ThisColProj.x + PhyNormProj.x,
                ThisColProj.y + PhyNormProj.y);

        // 현재 오브젝트
        // 받아 온 것의 충돌 벡터 + 받아 온 것의 노말 벡터
        Vector2 NewThisVel = new Vector2(PhyColProj.x + ThisNormProj.x,
                PhyColProj.y + ThisNormProj.y);

        phy.velocity = NewPhyVel;
        velocity = NewThisVel;

    }

    public float GetSize(){
        return size;
    }


}
