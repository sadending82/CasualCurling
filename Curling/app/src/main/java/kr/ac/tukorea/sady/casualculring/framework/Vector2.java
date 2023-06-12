package kr.ac.tukorea.sady.casualculring.framework;

import java.lang.Math;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        x = 0.f;
        y = 0.f;
    }

    public Vector2(float ix, float iy) {
        x = ix;
        y = iy;
    };

    public float dot(Vector2 v) { return x * v.x + y * v.y; };

    public float cross(Vector2 v) { return x * v.y - y * v.x; };

    public void normalize() {
        float length = (float)Math.sqrt(x * x + y * y);
        x = x / length;
        y = y / length;
    }

    public float distance(Vector2 v) {
       return (float)Math.sqrt((x - v.x)*(x - v.x) + (y - v.x) * (y - v.y));
    }

    public float magnitude() {
        return (float)Math.sqrt(x * x + y * y);
    }
}
