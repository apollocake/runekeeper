
package com.angrynerds.runekeeper.screens;

import com.angrynerds.runekeeper.BossDifficultyType;
import com.angrynerds.runekeeper.Enemy;
import com.angrynerds.runekeeper.Entity;
import com.angrynerds.runekeeper.EntityAnimation;
import com.angrynerds.runekeeper.HealthBar;
import com.angrynerds.runekeeper.Player;
import com.angrynerds.runekeeper.BoxPatrol;
import com.angrynerds.runekeeper.CrazyPatrol;
import com.angrynerds.runekeeper.DifficultyType;
import com.angrynerds.runekeeper.EasyDifficultyType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import java.util.ArrayList;


public class NewGameScreen extends RunekeeperScreen {

          HealthBar healthbar = new HealthBar();

    Stage stage;
    SpriteBatch batch;
    Skin skin;
    float time = 0;

    
     public Player player = new Player(25,25);

     ArrayList<Entity> entities;
    TextureRegion currentFrame;
    TextureRegion currentFrameAddRune;
    float stateTime;
    
    public NewGameScreen(Game game) {
        super(game);

        entities = new ArrayList<Entity>();

        DifficultyType easyDifficulty = new EasyDifficultyType(new Vector2(40, 40));
        DifficultyType bossDifficulty = new BossDifficultyType(new Vector2(100, 100));

        entities.add(new Enemy(new EntityAnimation(4, 1, 1, 0, 1, 4, 3, "demon.png"), "Demon", 350, 300, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 2, 2, "ghost.png"), "Ghost", 250, 200, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 11, 5, "goblin.png"), "Goblin", 150, 250, easyDifficulty, new CrazyPatrol()));
        entities.add(new Enemy(new EntityAnimation(10, 1, 1, 0, 1, 10, 10, "orc.png"), "Orc", 150, 150, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(3, 1, 1, 2, 1, 3, 4, "snake.png"), "Snake", 100, 300, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(8, 1, 1, 0, 1, 8, 5, "wizard.png"), "Wizard", 275, 100, easyDifficulty, new CrazyPatrol()));

        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 10, 4, "ghostking.png"), "Ghost King", 550, 100, bossDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 10, 10, "goblinking.png"), "Goblin King", 420, 250, new BossDifficultyType(new Vector2(175, 175)), new CrazyPatrol()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 4, 4, "snakeking.png"), "Snake King", 420, 350, bossDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 8, 8, "evilwizard.png"), "Evil Wizard", 220, 450, bossDifficulty, new CrazyPatrol()));
        entities.add(new Enemy(new EntityAnimation(10, 0, 0, 0, 0, 3, 4, "meteorbeast.png"), "Meteor Beast", 180, 430, bossDifficulty, new BoxPatrol()));
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        skin.add("default", new BitmapFont());
        
        stage.addActor(healthbar.health);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


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
        batch.begin();
        batch.draw(currentFrame, player.pos.x, player.pos.y);
        
        //todo
        //batch.draw();
        player.update(batch);

        for (Entity entity : this.entities) {
            batch.draw(entity.getAnimation().downIdling.getKeyFrame(stateTime, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);
            entity.update();
        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
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
    }
    
}
