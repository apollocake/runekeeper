package com.angrynerds.runekeeper.screens;

import com.angrynerds.runekeeper.AttackPatrol;
import com.angrynerds.runekeeper.BossDifficultyType;
import com.angrynerds.runekeeper.Enemy;
import com.angrynerds.runekeeper.Entity;
import com.angrynerds.runekeeper.EntityAnimation;
import com.angrynerds.runekeeper.Player;
import com.angrynerds.runekeeper.BoxPatrol;
import com.angrynerds.runekeeper.BuffAgainstFire;
import com.angrynerds.runekeeper.BuffAgainstGrass;
import com.angrynerds.runekeeper.BuffAgainstOre;
import com.angrynerds.runekeeper.BuffAgainstWater;
import com.angrynerds.runekeeper.CrazyPatrol;
import com.angrynerds.runekeeper.MusicCollision;
import com.angrynerds.runekeeper.DifficultyType;
import com.angrynerds.runekeeper.DoorCollision;
import com.angrynerds.runekeeper.EasyDifficultyType;
import com.angrynerds.runekeeper.FireEnemyType;
import com.angrynerds.runekeeper.GameStates;
import com.angrynerds.runekeeper.GrassEnemyType;
import com.angrynerds.runekeeper.sound.EnemyPainSfx;
import com.angrynerds.runekeeper.sound.MusicManager;
import com.angrynerds.runekeeper.HealTotem;
import com.angrynerds.runekeeper.HitBoxRenderer;
import com.angrynerds.runekeeper.OreEnemyType;
import com.angrynerds.runekeeper.WallCollision;
import com.angrynerds.runekeeper.WaterEnemyType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import static com.badlogic.gdx.graphics.TextureData.TextureDataType.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.angrynerds.runekeeper.sound.EnemyAttackSound;
import static com.angrynerds.runekeeper.screens.MenuScreen.GAME_RESUME;
import static com.angrynerds.runekeeper.screens.GameOverScreen.GAME_RESUME1;
import com.angrynerds.runekeeper.sound.DyingSound;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import static java.lang.Math.abs;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewGameScreen extends RunekeeperScreen {

    Stage stage;
    private static int skillsAdded;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    float time = 0;

    Color nullColor;

    Label livesLabel, attackLabel, attackBuffLabel, buffLabel, posLabel;
    BitmapFont font;
    LabelStyle textStyle;
    public Player player;

    ArrayList<Entity> entities;
    TextureRegion currentFrame;
    TextureRegion currentFrameForMagic;

    private Vector2 enemyPos = new Vector2();
    private Vector2 playerPos = new Vector2();
    private float enemyDistance = 0.0f;
    private boolean runMode = false;
    public HealTotem healTotem;

    SaveDialog saveDia;
    public static final int GAME_RUNNING = 0;
    public static final int GAME_PAUSED = 1;
    public static int gamestatus;

    SkillsDialog skillsDia;

    private final MusicCollision musicCollision;
    private final WallCollision wallCollision;
    private final DoorCollision doorCollision;
    private final EnemyPainSfx enemyPainSfx;
    private final DyingSound dyingSound;
    public static MusicManager musicManager;
    private final Skin skin;
    private boolean startedDying;
    private int buffPower;

    private final EnemyAttackSound enemyAttackSound;

    private final HitBoxRenderer hitboxRenderer;

    public NewGameScreen(Game game) {
        super(game);
        skillsAdded = 0;
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("worldmap.tmx");
        TiledMapTileLayer musicCollisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
        TiledMapTileLayer totemLayer = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer wallCollisionLayer = (TiledMapTileLayer) map.getLayers().get(2);
        TiledMapTileLayer doorCollisionLayer = (TiledMapTileLayer) map.getLayers().get(3);
        float tileWidth = musicCollisionLayer.getTileWidth();
        float tileHeight = musicCollisionLayer.getTileHeight();

        buffPower = 0;
        musicCollision = new MusicCollision(musicCollisionLayer); //should rename to musicLevelCollision
        wallCollision = new WallCollision(wallCollisionLayer);
        doorCollision = new DoorCollision(doorCollisionLayer);
        musicManager = new MusicManager(musicCollision);
        enemyPainSfx = new EnemyPainSfx();
        dyingSound = new DyingSound();
        renderer = new OrthogonalTiledMapRenderer(map);
        hitboxRenderer = new HitBoxRenderer();
        camera = new OrthographicCamera();
        player = new Player(25 * tileWidth, 25 * tileHeight);
        player.addObserver(musicCollision);
        player.addObserver(wallCollision);
        player.addObserver(doorCollision);
        enemyAttackSound = new EnemyAttackSound();

        gamestatus = GAME_RUNNING;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        saveDia = new SaveDialog("Save Load Menu", skin);
        skillsDia = new SkillsDialog("Skills Points", skin);
        entities = new ArrayList<Entity>();

        GameStates.gsEnemyAnimation = entities;

        DifficultyType easyDifficulty = new EasyDifficultyType(new Vector2(40, 40));
        DifficultyType bossDifficulty = new BossDifficultyType(new Vector2(100, 100));

        entities.add(new Enemy(new EntityAnimation(4, 1, 0, 2, 2, 4, 3, "demon.png"), "Demon", tileWidth * 5, tileHeight * 4, easyDifficulty, new BoxPatrol(), new FireEnemyType()));

        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 2, 2, "ghost.png"), "Ghost", tileWidth * 6, tileHeight * 13, easyDifficulty, new BoxPatrol(), new OreEnemyType()));
        entities.add(new Enemy(new EntityAnimation(4, 1, 1, 0, 1, 4, 3, "demon.png"), "Demon", tileWidth * 14, tileHeight * 11, easyDifficulty, new BoxPatrol(), new FireEnemyType()));
        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 2, 2, "ghost.png"), "Ghost", tileWidth * 33, tileHeight * 14, easyDifficulty, new BoxPatrol(), new OreEnemyType()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 11, 5, "goblin.png"), "Goblin", tileWidth * 34, tileHeight * 6, easyDifficulty, new BoxPatrol(), new GrassEnemyType()));
        entities.add(new Enemy(new EntityAnimation(10, 1, 1, 0, 1, 10, 10, "orc.png"), "Orc", tileWidth * 43, tileHeight * 5, easyDifficulty, new BoxPatrol(), new GrassEnemyType()));
        entities.add(new Enemy(new EntityAnimation(3, 1, 1, 2, 1, 3, 4, "snake.png"), "Snake", tileWidth * 43, tileHeight * 45, easyDifficulty, new BoxPatrol(), new GrassEnemyType()));
        entities.add(new Enemy(new EntityAnimation(8, 1, 1, 0, 1, 8, 5, "wizard.png"), "Wizard", tileWidth * 31, tileHeight * 38, easyDifficulty, new BoxPatrol(), new WaterEnemyType()));
        entities.add(new Enemy(new EntityAnimation(2, 1, 1, 0, 1, 10, 4, "ghostking.png"), "Ghost King", tileWidth * 12, tileHeight * 39, bossDifficulty, new BoxPatrol(), new OreEnemyType()));
        entities.add(new Enemy(new EntityAnimation(11, 1, 1, 0, 1, 10, 10, "goblinking.png"), "Goblin King", tileWidth * 42, tileHeight * 39, new BossDifficultyType(new Vector2(175, 175)), new BoxPatrol(), new GrassEnemyType()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 4, 4, "snakeking.png"), "Snake King", tileWidth * 8, tileHeight * 28, bossDifficulty, new BoxPatrol(), new GrassEnemyType()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 8, 8, "evilwizard.png"), "Evil Wizard", tileWidth * 31, tileHeight * 38, bossDifficulty, new BoxPatrol(), new WaterEnemyType()));
        entities.add(new Enemy(new EntityAnimation(1, 0, 0, 0, 0, 3, 4, "meteorbeast.png"), "Meteor Beast", tileWidth * 4, tileHeight * 46, bossDifficulty, new BoxPatrol(), new FireEnemyType()));

        entities.add(new Enemy(new EntityAnimation(1, 0, 3, 2, 1, 3, 4, "troll.png"), "Troll", tileWidth * 23, tileHeight * 35, easyDifficulty, new BoxPatrol(), new FireEnemyType()));

        healTotem = new HealTotem("totem01", totemLayer);
        player.addObserver(healTotem);
        //musicManager.pause();
    }

    public static class SaveDialog extends Dialog {

        private Player player;

        public SaveDialog(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
            initPlayer();
        }

        public SaveDialog(String title, Skin skin) {
            super(title, skin);
            initPlayer();
        }

        public SaveDialog(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
            initPlayer();
        }

        private void initPlayer() {
            this.player = new Player(0, 0);
        }

        private void setPlayer(Player newPlayer) {
            this.player = newPlayer;
        }

        {
            text("Save or Load");
            button("Save", "save");
            button("Load", "load");
            button("Cancel", "cancel");
            key(Input.Keys.ESCAPE, false);
        }

        @Override
        protected void result(Object object) {

            if (object == "save") {
                GameStates.gsExport(this.player);
            } else if (object == "load") {
                try {
                    GameStates.gsImport(this.player);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NewGameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            gamestatus = GAME_RUNNING;
            musicManager.play();
        }

    }

    public static class SkillsDialog extends Dialog {

        private Player player;
        private Label valueLabel;
        private int value;

        public SkillsDialog(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
            initPlayer();
        }

        public SkillsDialog(String title, Skin skin) {
            super(title, skin);
            initPlayer();
        }

        public SkillsDialog(String title, Window.WindowStyle windowStyle) {
            super(title, windowStyle);
            initPlayer();
        }

        private void initPlayer() {
            this.player = new Player(0, 0);
        }

        private void setPlayer(Player newPlayer) {
            this.player = newPlayer;
        }

        {

            /*    try {
             text(Integer.toString(player.getAttackPower()));
             } catch(Exception e) {
             text(".....................");
             }
             */
//        x = player.getAttackPower();
            //    valueLabel = new Label("Damage: " + Integer.toString(x), new Skin(Gdx.files.internal("uiskin.json")));
            //    text(valueLabel);
            text("Damage: ");

            Button addButton = new TextButton("+", new Skin(Gdx.files.internal("uiskin.json")));
            Button minusButton = new TextButton("-", new Skin(Gdx.files.internal("uiskin.json")));

            button(addButton, this);
            button(minusButton, this);

            addButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent ie, float x, float y) {
                    System.out.println("Add Button clicked.");
                    player.setAttackPower(player.getAttackPower() + 1);
                    value = player.getAttackPower();
                    //text(Integer.toString(value));
                }

            });

            minusButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent ie, float x, float y) {
                    System.out.println("Minus Button clicked.");
                    if (player.getAttackPower() > 0) {
                        player.setAttackPower(player.getAttackPower() - 1);
                        value = player.getAttackPower();
                        //text(Integer.toString(value));
                    }
                }
            });
            key(Input.Keys.ESCAPE, false);
        }

        @Override
        protected void result(Object object) {
            gamestatus = GAME_RUNNING;
            musicManager.play();
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

        attackLabel = new Label("Attack Power: " + player.getAttackPower(), textStyle);
        attackLabel.setColor(Color.GREEN);
        attackLabel.setPosition(20, 410);

        attackBuffLabel = new Label("Attack Power with Buff: " + player.getAttackPower(), textStyle);
        attackBuffLabel.setColor(Color.GREEN);
        attackBuffLabel.setPosition(20, 390);

        buffLabel = new Label("dy Buff: " + player.getCurrentBuff().getName(), textStyle);
        buffLabel.setColor(Color.GREEN);
        buffLabel.setPosition(20, 370);

        posLabel = new Label("Position: " + player.getPosition().x + " " + player.getPosition().y, textStyle);
        posLabel.setColor(Color.GREEN);
        posLabel.setPosition(20, 350);

        stage.addActor(livesLabel);
        stage.addActor(attackLabel);
        stage.addActor(attackBuffLabel);
        stage.addActor(buffLabel);
        stage.addActor(posLabel);
        stage.addActor(player.getHealthBar().healthBar);
        camera.position.set(player.getX() + 350, player.getY() + 220, 0);
        //musicManager.pause();
    }

    @Override
    public void render(float delta) {
        //musicManager.pause();
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (player.state.equals("DEAD")) //check if healthbar is empty
        {
            this.entities = new ArrayList<Entity>();
            game.setScreen(new GameOverScreen(game)); //end game if player is dead
        }
        livesLabel.setText("Lives: " + player.getPlayerLives());
        attackLabel.setText("Attack Power: " + player.getAttackPower());
        attackBuffLabel.setText("Attack Power with Buff: " + buffPower);
        buffLabel.setText("Current Buff: " + player.getCurrentBuff().getName());
        posLabel.setText("Position: " + player.getPosition().x + " " + player.getPosition().y);

        renderer.setView(camera);
        renderer.getBatch().begin();
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(0));
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(1));
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(2));
        renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(3));

        playerPos = player.getPosition();
        currentFrame = player.animation.getKeyFrame(player.stateTime, true);
        //
        // currentFrameForMagic = player.gloveRuneAnimation.getKeyFrame(player.stateTime, false);
        camera.position.set(player.getX() + player.playerAnimation.getWidth() / 2, player.getY() + player.playerAnimation.getHeight() / 2, 0);
        camera.update();
        renderer.getBatch().draw(currentFrame, player.pos.x, player.pos.y);
        //renderer.getBatch().draw(currentFrameForMagic, player.pos.x + 10, player.pos.y);

        if (gamestatus != GAME_PAUSED) {
            player.update(delta, (SpriteBatch) renderer.getBatch());
        }
        ArrayList<Entity> toRemove = new ArrayList<Entity>();
        //check if any collisons between player and enemies

        if (this.entities.size() <= 0) {
            game.setScreen(new GameOverScreen(game));
            return;
        }
        for (Entity entity : this.entities) {
            if (player.state.equals("ALIVE")) {
                if (gamestatus != GAME_PAUSED) {
                    if (player.bounds.overlaps(new Rectangle(entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y))) {
                        if (!player.attack.isEmpty()) {
                            System.out.println("You Hit The ENEMY");
                            enemyPainSfx.play(entity.getName(), delta);
                            //renderer.getBatch().setColor(Color.RED);
                            entity.damage(25); //damage the enemy. called 4 times every time spacebar is hit, cannot fogure out why.

                            if (!entity.isAlive()) //check if enemy is still alive
                            {
                                renderer.getBatch().setColor(nullColor);      //change to nullColor or else entire screen turns red when enemy is killed
                                renderer.getBatch().draw(entity.getAnimation().dyingAnimation.getKeyFrame(2, true), entity.getPosition().x - 50, entity.getPosition().y - 50, entity.getDimensions().x + 150, entity.getDimensions().y + 150);
                                toRemove.add(entity);
                            }

                        } else {
                            renderer.getBatch().setColor(nullColor);
                            enemyAttackSound.play(entity.getName(), delta);
                        }
                        player.isHit();
                        player.damage(1); //subtract health from healthbar

                        renderer.getBatch().draw(entity.getAnimation().enemyAttack.getKeyFrame(delta, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);

                        renderer.getBatch().setColor(nullColor);

                        buffPower = player.getCurrentBuff().buffEntity(((Enemy) entity).getEnemyType(), this.player.getAttackPower());
                        entity.damage(buffPower);
                        entity.update();
                    } else {
                        renderer.getBatch().setColor(((Enemy) entity).getHue().colorizeImage());
                        renderer.getBatch().draw(entity.getAnimation().downIdling.getKeyFrame(delta, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);
                        renderer.getBatch().setColor(nullColor);
                        entity.update();
                        enemyPos = entity.getPosition();
                        GameStates.gsEnemyPos = enemyPos;
                        enemyDistance = enemyPos.dst(playerPos);
                        //    System.out.println(enemyDistance);

                        GameStates.gsEnemyDist = enemyDistance;
                        //    if (runMode) {

                        if (enemyDistance < 120.0f && entity.getAlert() == false) {
                            //    entity.setPatrol(new CrazyPatrol());
                            entity.setPatrol(new AttackPatrol(player));
                            entity.setAlert(true);
                            //    System.out.println("AttackPatrol");
                        }

                        /*    if (enemyDistance > 300.0f && entity.getAlert() == true) {
                         entity.setPatrol(new BoxPatrol());
                         entity.setAlert(false);
                         }
                         */
                        //   }
                    }
                } else {
                    renderer.getBatch().draw(entity.getAnimation().downIdling.getKeyFrame(delta, true), entity.getPosition().x, entity.getPosition().y, entity.getDimensions().x, entity.getDimensions().y);
                }
            }
            renderer.getBatch().end();
            hitboxRenderer.setProjectionMatrix(camera.combined);
            //get dimensions needs to be used because sprite is resized after setting getRec width/height
            hitboxRenderer.drawBox(entity.getRec(), entity.getDimensions());
            renderer.getBatch().begin();
        }

        this.entities.removeAll(toRemove);

        toRemove.clear();

        renderer.getBatch().end();
        hitboxRenderer.setProjectionMatrix(camera.combined);
        hitboxRenderer.drawBox(player.bounds);
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        // resume game from save for the MenuScreen
        if (GAME_RESUME == 1) {
            try {
                GameStates.gsImport(this.player);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewGameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            GAME_RESUME = 0;
        }

        // resume game from save for the GameOverScreen
        if (GAME_RESUME1 == 1) {
            try {
                GameStates.gsImport(this.player);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewGameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            GAME_RESUME1 = 0;
        }

        time += delta;
        if (time > 1) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
                //move to a different game screen
                if (gamestatus == GAME_PAUSED) {
                    musicManager.play();
                    gamestatus = GAME_RUNNING;
                    saveDia.hide();
                } else {
                    saveDia.setPlayer(this.player);
                    musicManager.pause();
                    gamestatus = GAME_PAUSED;
                    saveDia.show(stage);
                    saveDia.toFront();
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
                //move to a different game screen
                if (gamestatus == GAME_PAUSED) {
                    musicManager.play();
                    gamestatus = GAME_RUNNING;
                    skillsDia.hide();
                } else {
                    skillsDia.setPlayer(this.player);
                    musicManager.pause();
                    gamestatus = GAME_PAUSED;
                    skillsDia.show(stage);
                    skillsDia.toFront();
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

            if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                player.setCurrentBuff(new BuffAgainstGrass());
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
                player.setCurrentBuff(new BuffAgainstWater());
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
                player.setCurrentBuff(new BuffAgainstFire());
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                player.setCurrentBuff(new BuffAgainstOre());
            }

            /* if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
             //move to a different game screen
             runMode = !runMode;
             System.out.println("runMode changed to " + runMode);
             }
             */
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
        musicCollision.dispose();
    }

}
