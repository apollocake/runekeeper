
package com.angrynerds.runekeeper.screens;

import com.angrynerds.runekeeper.Enemy;
import com.angrynerds.runekeeper.Entity;
import com.angrynerds.runekeeper.EntityAnimation;
import com.angrynerds.runekeeper.HealthBar;
import com.angrynerds.runekeeper.Player;
import com.angrynerds.runekeeper.PlayerAnimation;
import com.angrynerds.runekeeper.BoxPatrol;
import com.angrynerds.runekeeper.CrazyPatrol;
import com.angrynerds.runekeeper.MusicCollision;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import java.util.ArrayList;


public class NewGameScreen extends RunekeeperScreen {

          HealthBar healthbar = new HealthBar();

    Stage stage;
    SpriteBatch batch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    float time = 0;
    public Player player;
    ArrayList<Entity> entities;
    TextureRegion currentFrame;  
    float stateTime;
   
    
    public NewGameScreen(Game game) {
        super(game);
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("worldmap.tmx");
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
         MusicCollision playerCollision = new MusicCollision(collisionLayer);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Player(25,25);
        player.addObserver(playerCollision);
        
        //TBshived
        player.setPosition(25 * collisionLayer.getTileWidth(), 25 * collisionLayer.getTileHeight());
        //Gdx.input.setInputProcessor(player);
        
        
        
        
        entities = new ArrayList<Entity>();
        entities.add(new Enemy(new EntityAnimation(4, 1, 1, 0, 1, 4, 3, "demon.png"), "Demon", 350, 300, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 2, 2, "ghost.png"), "Ghost", 250, 200, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 11, 5, "goblin.png"), "Goblin", 150, 250, new CrazyPatrol()));
        entities.add(new Enemy(new EntityAnimation(10, 1, 1, 0, 1, 10, 10, "orc.png"), "Orc", 150, 150, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(3, 1, 1, 2, 1, 3, 4, "snake.png"), "Snake", 100, 300, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(8, 1, 1, 0, 1, 8, 5, "wizard.png"), "Wizard", 275, 100, new CrazyPatrol()));
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        stage.addActor(healthbar.health);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));



        time += delta;
        if (time > 1) {

            if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
                //move to a different game screen
                game.setScreen(new SaveScreen(game));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.G)) {
                //move to a different game screen
                game.setScreen(new GameOverScreen(game));
            }
            if (Gdx.input.justTouched()) {
                healthbar.addhealth(5);
            }

        }

        
        stateTime += Gdx.graphics.getDeltaTime();  
        currentFrame  = player.animation.getKeyFrame(stateTime, true);
        camera.position.set(player.getX()+ player.playerAnimation.getWidth() / 2, player.getY() + player.playerAnimation.getHeight() / 2, 0);
        camera.update();

        renderer.setView(camera);
        renderer.getBatch().begin();

        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("floor"));
        renderer.getBatch().draw(currentFrame, player.pos.x, player.pos.y);        
        //renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("foreground"));
        player.update();

        for(int i = 0; i < this.entities.size(); i++) {
            renderer.getBatch().draw(this.entities.get(i).getAnimation().downIdling.getKeyFrame(stateTime, true), this.entities.get(i).getPosition().x, this.entities.get(i).getPosition().y);
            this.entities.get(i).update();
        }
                stage.draw();
        renderer.getBatch().end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 1.6f;
        camera.viewportHeight = height / 1.6f;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
    
}
