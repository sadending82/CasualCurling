package kr.ac.tukorea.sady.casualculring.framework;

public class PhysicsObject extends Sprite {

    public Vector2 velocity;
    public float friction; // 마찰력
    public float elasticity; // 탄성력

    public float mass = 1.0f;

    protected float size = 1.0f;

    public PhysicsObject(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        velocity = new Vector2(0.f, 0.f);
        friction = 0.01f;
        elasticity = 0.0f;
    }

    @Override
    public void update() {
        x += velocity.x;
        y += velocity.y;

        Vector2 frictionVector = new Vector2(velocity.x, velocity.y);
        frictionVector.normalize();

        velocity.x -= frictionVector.x * friction;
        velocity.y -= frictionVector.y * friction;
    }

    public void Collision(PhysicsObject phy) {

        // 두 물체 사이의 상대적인 위치를 구합니다.
        float dx = phy.x - x;
        float dy = phy.y - y;

        // 벡터의 길이를 구합니다.
        float dist = (float)Math.sqrt(dx * dx + dy + dy);

        // 상대 위치를 길이로 나누어 정규화된 노말 벡터를 구합니다.
        float nx = dx / dist;
        float ny = dy / dist;

        // 두 물체의 상대적인 속도를 구합니다.
        float kx = phy.velocity.x - velocity.x;
        float ky = phy.velocity.y - velocity.y;

        // 속도와 노말벡터를 바탕으로 탄성 충돌 공식을 활용하여 충력량을 구합니다.
        float p = 2 * (kx * nx + ky * ny) / (mass * 2);

        // 노말벡터에 충격량을 곱하여 속도를 재설정 해줍니다.
        velocity.x = -1 * p * mass * nx;
        velocity.y = -1 * p * mass * ny;

        phy.velocity.x = p * mass * nx;
        phy.velocity.y = p * mass * ny;
    }

}
