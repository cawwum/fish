package me.blip.swimmyfish;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends GameEntity
{
    public float kg = 0.5f;

    public Player(float xp,float yp,float height)
    {
        this.xp = xp;
        this.yp = yp;
        this.height = height;
        this.width = height*widthPerHeight;

        heightPerKg = height/kg;
    }

    @Override
    public void render(ShapeRenderer shapes)
    {
        shapes.setColor(Color.LIME);
        shapes.rect(xp,yp,width,height);
    }
}
