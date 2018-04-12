package me.blip.swimmyfish;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Enemy extends GameEntity
{
    private float kg;

    public Enemy(float xp, float yp,float kg,float speed)
    {
        this.xp = xp;
        this.yp = yp;
        this.kg = kg;
        this.height = kg*heightPerKg;
        this.width = height*widthPerHeight;
        this.xv = -speed;
    }

    @Override
    public void render(ShapeRenderer shapes)
    {
        shapes.setColor(Color.FIREBRICK);
        shapes.rect(xp,yp,width,height);
    }
}
