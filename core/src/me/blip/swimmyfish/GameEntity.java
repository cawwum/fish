package me.blip.swimmyfish;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GameEntity
{
    // ....Think of triangles.... //
    protected static float heightPerKg = 1f;
    protected final static float widthPerHeight = 1.5f;

    public float xp;
    public float yp;
    public float xv = 0;
    public float yv = 0;
    public float width;
    public float height;

    public void move(float dt)
    {
        xp += xv*dt;
        yp += yv*dt;
    }

    public abstract void render(ShapeRenderer shapes);
}