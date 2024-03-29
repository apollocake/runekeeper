package com.angrynerds.runekeeper.screens;

import com.angrynerds.runekeeper.BossDifficultyType;
import com.angrynerds.runekeeper.Enemy;
import com.angrynerds.runekeeper.Entity;
import com.angrynerds.runekeeper.EntityAnimation;
import com.angrynerds.runekeeper.Player;
import com.angrynerds.runekeeper.BoxPatrol;
import com.angrynerds.runekeeper.CrazyPatrol;
import com.angrynerds.runekeeper.MusicCollision;
import com.angrynerds.runekeeper.DifficultyType;
import com.angrynerds.runekeeper.EasyDifficultyType;
import com.angrynerds.runekeeper.sound.MusicManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.util.ArrayList;

public class NewGameScreen extends RunekeeperScreen {

    Stage stage;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    float time = 0;

    Color nullColor;

    Label livesLabel;
    BitmapFont font;
    LabelStyle textStyle;
    public Player player;

    ArrayList<Entity> entities;
    TextureRegion currentFrame;

    private Vector2 enemyPos = new Vector2();
    private Vector2 playerPos = new Vector2();
    private float enemyDistance = 0.0f;
    private boolean runMode = false;

    SaveDialog saveDia;
    public static final int GAME_RUNNING = 0;
    public static final int GAME_PAUSED = 1;
    public static int gamestatus;

    private final MusicCollision playerCollision;
    private final MusicManager musicManager;
    private final Skin skin;
    private boolean startedDying;

    public NewGameScreen(Game game) {
        super(game);
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("worldmap.tmx");
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
        playerCollision = new MusicCollision(collisionLayer);
        musicManager = new MusicManager(playerCollision);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Player(25, 25);
        player.addObserver(playerCollision);

        gamestatus = GAME_RUNNING;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        saveDia = new SaveDialog("Save Load Menu", skin);
        entities = new ArrayList<Entity>();

        DifficultyType easyDifficulty = new EasyDifficultyType(new Vector2(40, 40));
        DifficultyType bossDifficulty = new BossDifficultyType(new Vector2(100, 100));

        entities.add(new Enemy(new EntityAnimation(4, 1, 1, 0, 1, 4, 3, "demon.png"), "Demon", 350, 300, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 2, 2, "ghost.png"), "Ghost", 250, 200, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 11, 5, "goblin.png"), "Goblin", 150, 250, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(10, 1, 1, 0, 1, 10, 10, "orc.png"), "Orc", 150, 150, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(3, 1, 1, 2, 1, 3, 4, "snake.png"), "Snake", 100, 300, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(8, 1, 1, 0, 1, 8, 5, "wizard.png"), "Wizard", 275, 100, easyDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 10, 4, "ghostking.png"), "Ghost King", 550, 100, bossDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 10, 10, "goblinking.png"), "Goblin King", 420, 250, new BossDifficultyType(new Vector2(175, 175)), new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 4, 4, "snakeking.png"), "Snake King", 420, 350, bossDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 8, 8, "evilwizard.png"), "Evil Wizard", 220, 450, bossDifficulty, new BoxPatrol()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 3, 4, "meteorbeast.png"), "Meteor Beast", 180, 430, bossDifficulty, new BoxPatrol()));

    }

    public static class SaveDialog extends Dialog {

        public SaveDialog(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public SaveDialog(String title, Skin skin) {
            super(title, skin);
        }

        public SaveDialog(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }

        {
            text("Save or Load");
            button("Save", 1);
            button("Load", 1);
            button("Cancel", 3);
            key(Input.Keys.ESCAPE, false);
        }

        @Override
        protected void result(Object object) {
            //System.out.println(object);
            gamestatus = GAME_RUNNING;
        }

    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        nullColor = renderer.getBatch().getColor();

        font = new BitmapFont();
        textStyle = new LabelStyle();
        textStyle.font = font;
        livesLabel = new Label("Lives: " + player.getPlayerLives(), textStyle);
        livesLabel.setColor(Color.GREEN);
        livesLabel.setPosition(20, 430);
        stage.addActor(livesLabel);
        stage.addActor(player.getHealthBar().healthBar);
        camera.position.set(player.getX() + 350, player.getY() + 220, 0);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (player.state.equals("DEAD")) //check if healthbar is empty
        {
            this.entities = new ArrayList<Entity>();
            game.setScreen(new GameOverScreen(game)); //end game if player is dead
        }
        livesLabel.setText("Lives: " + player.getPlayerLives());

        renderer.setView(camera);
        renderer.getBatch().begin();
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("floor"));

        playerPos = player.getPosition();
        currentFrame = player.animation.getKeyFrame(player.stateTime, true);
        camera.position.set(player.getX() + player.playerAnimation.getWidth() / 2, player.getY() + player.playerAnimation.getHeight() / 2, 0);
        camera.update();
        renderer.getBatch().draw(currentFrame, player.pos.x, player.pos.y);

        if (gamestatus != GAME_PAUSED) {
            player.update(delta, (SpriteBatch) renderer.getBatch());
        }
        //check if any collisons between player and enemies    
        for (Entity entity : this.entities) {
            if (player.state.equals("ALIVE")) {
                if (gamestatus != GAME_PAUSED) {
                    if (player.bounds.overlaps(entity.getRec())) {
                        if (!player.attack.isEmpty()) {
                            System.out.println("You Hit The ENEMY");
                            renderer.getBatch().setColor(Color.RED);
                        } else {
                            renderer.getBatch().setColor(nullColor);
                        }
                        player.isHit();
                        player.damage(1); //subtract health from healthbar
                        renderer.getBatch().draw(entity.getAnimation().enemyAttack.getKeyFrame(delta, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);
                        renderer.getBatch().setColor(nullColor);
                    } else {
                        renderer.getBatch().setColor(nullColor);
                        renderer.getBatch().draw(entity.getAnimation().downIdling.getKeyFrame(delta, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);
                        entity.update();
                        enemyPos = entity.getPosition();
                        enemyDistance = enemyPos.dst(playerPos);

                        if (runMode) {

                            if (enemyDistance < 75.0f && entity.getAlert() == false) {
                                entity.setPatrol(new CrazyPatrol());
                                entity.setAlert(true);
                            }

                            if (enemyDistance > 300.0f && entity.getAlert() == true) {
                                entity.setPatrol(new BoxPatrol());
                                entity.setAlert(false);
                            }
                        }
                    }
                } else {
                    renderer.getBatch().draw(entity.getAnimation().downIdling.getKeyFrame(delta, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);
                }
            }
        }
        renderer.getBatch().end();
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        time += delta;
        if (time > 1) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
                //move to a different game screen
                if (gamestatus == GAME_PAUSED) {
                    gamestatus = GAME_RUNNING;
                    saveDia.hide();
                } else {
                    gamestatus = GAME_PAUSED;
                    saveDia.show(stage);
                    saveDia.toFront();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                //move to a different game screen
                game.setScreen(new GameOverScreen(game));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
                player.damage(50);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
                player.addhealth(50);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                //move to a different game screen
                runMode = !runMode;
                System.out.println("runMode changed to " + runMode);
            }
        }
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        skin.dispose();
        map.dispose();
        playerCollision.dispose();
    }

}
