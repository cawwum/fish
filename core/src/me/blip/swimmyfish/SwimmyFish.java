package me.blip.swimmyfish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

// Brickfish / Swimmy Fish/ Hungry Fish
// Brickfish swings arms around wildly?
// emoji --> expression on fish LMAO
// fish swim away/towards you (a little bit randomly)
// smaller fish spawned consistently the same ratio to big fish, and the same amount every iteration
// knock fish out of the way if you land on them at the back? Phase through for now at least...

public class SwimmyFish extends ApplicationAdapter implements InputProcessor
{
    private OrthographicCamera cam;
    private ShapeRenderer shapes;
    private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
    private final float gravity = 1.5f;
    private final float swimHeight = 40f;
    private Player player;
    private final float stageHeight = 100f;
    private float stageWidth;

    @Override
    public void create()
    {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        stageWidth = stageHeight * width/height;

        cam = new OrthographicCamera(stageWidth, stageHeight);
        cam.position.set(cam.viewportWidth * 0.5f, cam.viewportHeight * 0.5f, 0);
        cam.update();

        player = new Player(10f,20f,10f);
        entities.add(player);

        shapes = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

        bigFish(1);
    }

    private void updateLogic(float dt)
    {
        if(player.yv > -60f)player.yv -= gravity;
        else player.yv = -60f;

        for(int i=0;i<entities.size();i++)
        {
            entities.get(i).move(dt);
        }

        if(player.yp < -player.height)player.yp = stageHeight;
        if(player.yp > stageHeight)player.yp = -player.height;
    }

    @Override
    public void render()
    {
        float dt = Gdx.graphics.getDeltaTime();

        updateLogic(dt);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapes.setProjectionMatrix(cam.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        for(int i=0;i<entities.size();i++)
        {
            entities.get(i).move(dt);
            entities.get(i).render(shapes);
        }
        shapes.end();

        cam.update();
    }

    @Override
    public void dispose()
    {
        shapes.dispose();
    }

    private void smallFish(int n)
    {
        for(int i=0;i<n;i++)
        {
            Enemy enemy = new Enemy(stageWidth,stageHeight*0.5f,player.kg*0.666f,stageWidth*0.1f);
            entities.add(enemy);
        }
    }

    private void bigFish(int n)
    {
        for(int i=0;i<n;i++)
        {
            Enemy enemy = new Enemy(stageWidth,stageHeight*0.5f,player.kg*1.333f,stageWidth*0.1f);
            entities.add(enemy);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        cam.viewportWidth = 100f * ((float)width)/height;
        cam.viewportHeight = 100f;
        cam.update();
    }

    @Override
    public void resume()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        player.yv = swimHeight;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
