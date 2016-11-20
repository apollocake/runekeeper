/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrynerds.runekeeper.screens;

import com.angrynerds.runekeeper.HealthBar;
import com.angrynerds.runekeeper.sound.ButtonsJobs;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen extends RunekeeperScreen {

    HealthBar healthbar; 
    Skin skin;
    TextureRegion intro;
  //  SpriteBatch batch;   TT - not needed
    float time = 0;
    Stage stage;
    Game game;
    
    public static int GAME_RESUME1 = 0;
    

    public GameOverScreen(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void show() {
        intro = new TextureRegion(new Texture("gameover.png"), 0, 0, 480, 320);
    //    batch = new SpriteBatch();     TT -   not needed
    //  batch.getProjectionMatrix().setToOrtho2D(0, 0, 480, 320);
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        
         skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.RED);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        
     

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton endGameButton = new TextButton("EXIT GAME", textButtonStyle);
        final TextButton newGameButton = new TextButton("NEW GAME", textButtonStyle);
        final TextButton resumeGameButton = new TextButton("RESUME GAME FROM LAST SAVE", textButtonStyle);
       
         endGameButton.setPosition(100, 80);
         newGameButton.setPosition(100, 100);
         resumeGameButton.setPosition(100, 120);
        
         stage.addActor(endGameButton);
         stage.addActor(newGameButton);
         stage.addActor(resumeGameButton);
             
         endGameButton.addListener(new ButtonsJobs());
         newGameButton.addListener(new ButtonsJobs());
         resumeGameButton.addListener(new ButtonsJobs());
         
        
        resumeGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 GAME_RESUME1 = 1;
                 game.setScreen(new NewGameScreen(game));
            }
        }); 
         
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 game.setScreen(new SplashScreen(game));
            }
        });
         
        endGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // batch.begin();
   // batch.draw(intro, 0, 0);    TT - not needed
    // batch.end();
   stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
   stage.getBatch().begin();
   stage.getBatch().draw(intro, 0, 0,this.stage.getWidth(),this.stage.getHeight());
   
   stage.getBatch().end();
   stage.draw();
   
   
      

        time += delta;
        
        //TT -  commented out because all actions resulted in the game restarting.
/*
        if (time > 1) {
            if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
                this.game.setScreen(new SplashScreen(this.game));
            }
        }
*/

    }

    @Override
    public void hide() {
        stage.dispose();
        
        //TT - did not comment out, was previously commented out.
        //Gdx.app.debug("Runekeeper", "dispose intro");
        //batch.dispose();
        //intro.getTexture().dispose();
    }
}