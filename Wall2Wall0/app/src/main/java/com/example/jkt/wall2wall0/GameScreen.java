package com.example.jkt.wall2wall0;

import android.graphics.*;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jkt.wall2wall0.impl.AndroidGame;
import com.example.jkt.wall2wall0.impl.AndroidMusic;
import com.example.jkt.wall2wall0.impl.AndroidSound;
import com.example.jkt.wall2wall0.math.Circle;
import com.example.jkt.wall2wall0.math.OverlapTester;
import com.example.jkt.wall2wall0.math.Rectangle;
import com.google.android.gms.games.Games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JDK on 3/30/2015.
 */
public class GameScreen extends Screen {

    private boolean music_started;
    private PlayerChar player1;
    private FallingEnemy enemy1;
    private boolean enemy1added;
    private final ArrayList<FallingEnemy> enemy_list;
    private final OverlapTester checkForOverlap;
    private final Runnable timerRunnable;
    private long delay_time;
    private int highScore;
    private int currentScore;
    private boolean newHighScore;
    private long menu_delay;
    private int top_walls_y_pos;
    private int bot_walls_y_pos;
    private int top_backg_y_pos;
    private int bot_backg_y_pos;
    private int bot_forestbgtree_y_pos;
    private int top_forestbgtree_y_pos;
    private float fpscounter;
    private float cam_scroll1;
    private float cam_scroll2;
    public boolean height_thresh1;
    public boolean height_thresh2;
    private SpawnTimer enemySpawnTimer;
    private ArrayList<SpawnEvent> enemySpawnEventArray;
    private int walls_blitted;
    private WallHazardHandler wallHazardHandler;
    private ArrayList<WallHazard> hazardBoundsArray;
    private int jump_type;

    public AndroidMusic game_music;
    public AndroidSound death_sound;
    private long pause_time;
    private boolean tutorial_time = true;
    private int current_level = 1;
    private int opacity_num = 5;
    private boolean reached_255_opacity = false;
    private boolean transition_incomplete = true;
    private long game_start_time;
    private long current_time;
    private int enemy_count = 0;
    private boolean enemyArrayParsed;

    private final float ENEMY_Y_SPAWN_POS = -110f;

    public float ENEMY_1_WIDTH = 60f;
    public float ENEMY_1_HEIGHT = 36f;
    public float ENEMY_2_WIDTH = 64f;
    public float ENEMY_2_HEIGHT = 96f;
    public float ENEMY_3_WIDTH = 47f;
    public float ENEMY_3_HEIGHT = 59f;
    public float ENEMY_4_WIDTH = 57f;
    public float ENEMY_4_HEIGHT = 100f;
    public float ENEMY_5_WIDTH = 72f;
    public float ENEMY_5_HEIGHT = 91f;
    public float ENEMY_6_WIDTH = 67f;
    public float ENEMY_6_HEIGHT = 60f;
    public float ENEMY_7_RADIUS = 40f;
    public float ENEMY_8_WIDTH = 62f;
    public float ENEMY_8_HEIGHT = 100f;
    public float ENEMY_9_WIDTH = 65f;
    public float ENEMY_9_HEIGHT = 60f;
    public float ENEMY_10_WIDTH = 95f;
    public float ENEMY_10_HEIGHT = 81f;

    private int SETTINGS_CHANGE_X_POSITION = 94;
    private int SETTINGS_CHANGE_Y_POSITION = 418 - 150;
    private int SETTINGS_CHANGE_WIDTH = 294;
    private int SETTINGS_CHANGE_HEIGHT = 68;
    private int SETTINGS_BACK_Y_POSITION = 522 - 150;
    private int SETTINGS_BACK_WIDTH = 88;
    private int SETTINGS_BACK_HEIGHT = 66;
    private int SETTINGS_WINDOW_X_POSITION = 50;
    private int SETTINGS_WINDOW_Y_POSITION = 388 - 150;

    private final int UI_WINDOW_X_POSITION = 50;
    private final int UI_WINDOW_Y_POSITION = 130;
    private boolean bot_left_wall_low_hazard = false;
    private boolean bot_left_wall_high_hazard = false;
    private boolean top_left_wall_low_hazard = false;
    private boolean top_left_wall_high_hazard = false;
    private boolean bot_right_wall_low_hazard = false;
    private boolean bot_right_wall_high_hazard = false;
    private boolean top_right_wall_low_hazard = false;
    private boolean top_right_wall_high_hazard = false;
    private boolean drawInGameSettings = false;
    private boolean player_holding = false;
    long hold_start_time;

    private int SETTINGS_BACK_X_POSITION = 192;

    private Image forest_background_img;
    private Image forest_tree_background_img;
    private Image forest_nohaz_left_wall_img;
    private Image forest_lowhaz_left_wall_img;
    private Image forest_highhaz_left_wall_img;
    private Image forest_nohaz_right_wall_img;
    private Image forest_lowhaz_right_wall_img;
    private Image forest_highhaz_right_wall_img;
    private Image factory_background_img;
    private Image factory_nohaz_left_wall_img;
    private Image factory_lowhaz_left_wall_img;
    private Image factory_highhaz_left_wall_img;
    private Image factory_nohaz_right_wall_img;
    private Image factory_lowhaz_right_wall_img;
    private Image factory_highhaz_right_wall_img;
    private Image log_enemy_img;
    private Image apple_enemy_img1;
    private Image apple_enemy_img2;
    private Image apple_enemy_img3;
    private Image bird_enemy_reverse_img;
    private Image bird_enemy_norm_img;
    private Image monkey_enemy_reverse_img;
    private Image monkey_enemy_norm_img;
    private Image crate_enemy_img;
    private Image wheel_enemy_img1;
    private Image wheel_enemy_img2;
    private Image wheel_enemy_img3;
    private Image wheel_enemy_img4;
    private Image hammer_enemy_norm_img;
    private Image hammer_enemy_reverse_img;
    private Image robothead_enemy_norm_img;
    private Image robothead_enemy_reverse_img;
    private Image drone_enemy_img;
    private Image pause_button_rdy_img;
    private Image UI_paused_window_img;
    private Image pause_button_pressed_img;
    private Image jump_meter_nofill_img;
    private Image jump_meter_fill1_img;
    private Image jump_meter_fill2_img;
    private Image jump_meter_fill3_img;

            
    private Image SPRITE_LEFT_WALL_HANG;
    private Image SPRITE_RIGHT_WALL_HANG;
    private Image SPRITE_RIGHT_DYING_EARLY;
    private Image SPRITE_LEFT_DYING_EARLY;
    private Image SPRITE_RIGHT_DYING_LATE;
    private Image SPRITE_LEFT_DYING_LATE;
    private Image SPRITE_RIGHT_3;
    private Image SPRITE_LEFT_3;
    private Image SPRITE_RIGHT_4;
    private Image SPRITE_LEFT_4;
    private Image SPRITE_RIGHT_5;
    private Image SPRITE_LEFT_5;
    private Image SPRITE_RIGHT_6;
    private Image SPRITE_LEFT_6;
    private Image SPRITE_RIGHT_7;
    private Image SPRITE_LEFT_7;
    private Image SPRITE_RIGHT_8;
    private Image SPRITE_LEFT_8;
    private Image SPRITE_RIGHT_10;
    private Image SPRITE_LEFT_10;
    private Image SPRITE_RIGHT_SLIDING1;
    private Image SPRITE_LEFT_SLIDING1;
    private Image SPRITE_RIGHT_SLIDING2;
    private Image SPRITE_LEFT_SLIDING2;
    private Image SPRITE_RIGHT_SLIDING3;
    private Image SPRITE_LEFT_SLIDING3;
    private Image ready_splash_image;

    private Image sound_enabled_img;
    private Image sound_disabled_img;


    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    Paint paint;
    Paint paint2;
    Paint paint3;
    Paint paint4;
    Paint paint5;


    public GameScreen(Game game) {
        super(game);
        Log.i("GameScreen", "start");
        GameState state = GameState.Ready;

        // Initialize game objects

        this.player1 = new PlayerChar(80f, 576f, 76f, 102f);
        this.enemy1 = new LogEnemy(200f, -50f, ENEMY_1_WIDTH, ENEMY_1_HEIGHT, 1);
        this.enemy1added = false;
        Random randomGenerator = new Random();
        this.enemy_list = new ArrayList<FallingEnemy>(16);
        this.checkForOverlap = new OverlapTester();
        this.currentScore = 0;
        this.newHighScore = false;
        this.highScore = Settings.getHighScore();
        this.top_backg_y_pos = -1248; // FOR LEVEL 1 AND LEVEL 2
        this.bot_backg_y_pos = -224;
        this.top_walls_y_pos = -812;
        this.bot_walls_y_pos = -6;
        this.bot_forestbgtree_y_pos = -195;
        this.top_forestbgtree_y_pos = -1190;
        this.fpscounter = 0;
        this.delay_time = System.currentTimeMillis();
        this.cam_scroll1 = 0;
        this.cam_scroll2 = 0;
        this.enemySpawnTimer = new SpawnTimer();
        this.enemySpawnEventArray = new ArrayList<SpawnEvent>();
        this.enemyArrayParsed = false;
        this.walls_blitted = 2;
        this.wallHazardHandler = new WallHazardHandler();
        this.hazardBoundsArray = new ArrayList<>();
        this.jump_type = 0;
        this.hold_start_time = 0;
        this.player_holding = false;

        // timerRunnable is used to handle the spawning of all enemy objects
        this.timerRunnable = new Runnable() {

            @Override
            public void run() {
                Log.i("GameScreen", "ready to spawn");
                if (enemy_list.size() < 16) {


                    SpawnEvent current_spawn = enemySpawnEventArray.get(enemy_count);
                    Log.i("GameScreen", "Spawning enemy number " +
                            String.valueOf(current_spawn.enemy_type));
                    Log.i("GameScreen", String.valueOf(System.currentTimeMillis()));

                    if (current_spawn.enemy_type == 1) {
                        LogEnemy new_enemy = new LogEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_1_WIDTH, ENEMY_1_HEIGHT, 1);
                        enemy_list.add(new_enemy);
/*                    } else if (current_spawn.enemy_type == 2) {
                        BranchEnemy new_enemy = new BranchEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_2_WIDTH, ENEMY_2_HEIGHT, 2);
                        enemy_list.add(new_enemy);*/
                    } else if (current_spawn.enemy_type == 3) {
                        AppleEnemy new_enemy = new AppleEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_3_WIDTH, ENEMY_3_HEIGHT, 3);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 5) {
                        MonkeyEnemy new_enemy = new MonkeyEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_4_WIDTH, ENEMY_4_HEIGHT, 5);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 4) {
                        BirdEnemy new_enemy = new BirdEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_5_WIDTH, ENEMY_5_HEIGHT, 4);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 6) {
                        CrateEnemy new_enemy = new CrateEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_6_WIDTH, ENEMY_6_HEIGHT, 6);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 7) {
                        WheelEnemy new_enemy = new WheelEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_7_RADIUS * 2, ENEMY_7_RADIUS * 2, 7);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 8) {
                        HammerEnemy new_enemy = new HammerEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_8_WIDTH, ENEMY_8_HEIGHT, 8);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 9) {
                        RobotHeadEnemy new_enemy = new RobotHeadEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_9_WIDTH, ENEMY_9_HEIGHT, 9);
                        enemy_list.add(new_enemy);
                    } else if (current_spawn.enemy_type == 10) {
                        DroneEnemy new_enemy = new DroneEnemy(current_spawn.enemy_x_location, ENEMY_Y_SPAWN_POS, ENEMY_10_WIDTH, ENEMY_10_HEIGHT, 10);
                        enemy_list.add(new_enemy);
                    }
                    enemy_count += 1;
                }
            }
        };

        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(30);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.YELLOW);

        paint3 = new Paint();
        paint3.setTextSize(20);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.YELLOW);

        paint4 = new Paint();
        paint4.setTextSize(15);
        paint4.setTextAlign(Paint.Align.CENTER);
        paint4.setAntiAlias(true);
        paint4.setColor(Color.BLACK);

        paint5 = new Paint();
        paint5.setTextSize(30);
        paint5.setTextAlign(Paint.Align.CENTER);
        paint5.setAntiAlias(true);
        paint5.setColor(Color.RED);

        if (Settings.soundEnabled) {
            this.music_started = true;
            this.game_music = (AndroidMusic) game.getAudio().newMusic("179562__clinthammer__clinthammermusic-ts-bass.wav");
            this.game_music.play();
            this.game_music.setLooping(true);
            death_sound = (AndroidSound) game.getAudio().newSound("Realistic_Punch.wav");
        }
    }


    Graphics g = game.getGraphics();  //ONLY NEEDED IF DRAWING AT GAME START.

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        //Log.i("FPSCOUNTER", String.valueOf(((System.currentTimeMillis() - this.fpscounter) * (1.000 / 1.000)) * 100000));
        //this.fpscounter = System.currentTimeMillis();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different
        // update methods. Refer to Unit 3's code. We did a similar
        // thing without separating the update method.

        // Note that order is important here. If the game is
        // in Ready state, we should only go to Running.
        // From Running, we should only go to Paused or GameOver.

        if (state == GameState.Ready) {
            Log.i("GameScreen", "state is READY");
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            Log.i("GameScreen", "state is PAUSED");
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            Log.i("GameScreen", "state is GAME OVER");
            updateGameOver(touchEvents);
        }
    }


    private void updateReady(List<Input.TouchEvent> touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game
        // begins. state now becomes GameState.Running.
        // Now the updateRunning() method will be called!

        // Create all enemy arrays which hold the pattern of enemy spawns
        if (!this.enemyArrayParsed) {
            this.enemySpawnTimer.createEnemyArray();
            this.enemySpawnEventArray = enemySpawnTimer.parseEnemyArray();
            this.enemyArrayParsed = true;
            Log.i("GameScreen", "enemySpawnEventArray parsed");
            Log.i("GameScreenC", String.valueOf(this.hold_start_time));
        }

        ready_splash_image = g.newImage("SplashScreen_ScreenSize.png", Graphics.ImageFormat.ARGB8888);
        this.loadAssets();

        state = GameState.Running;
    }

    private void updateRunning(List<Input.TouchEvent> touchEvents, float deltaTime) {

        this.current_time = System.currentTimeMillis();
        //Log.i("GameScreenTIMER", String.valueOf(this.current_time));


        // 1. All touch input is handled here:
        //Log.i("GameScreen", "update1, updateRunning started");
        int touchEventsSize = touchEvents.size();
        for (int currentTouchEventIndex = 0; currentTouchEventIndex < touchEventsSize; currentTouchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(currentTouchEventIndex);

            // Handle TOUCH_DOWN
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                if (System.currentTimeMillis() - this.delay_time > 200) {
                    if (!tutorial_time) {
                        if (inBounds(currentEvent, 0, 180, 770, 860)) {
                            Log.i("GameScreen", "TOUCH_DOWN detected in bounds");
                            if (!this.player_holding) {
                                // Start timer for player_holding which will determing jump_type
                                this.player_holding = true;
                                this.hold_start_time = System.currentTimeMillis();
                            }
                        } else if (inBounds(currentEvent, 395, 15, 70, 70)) {
                            pause();
                        }
                    }
                }
            }

            // Handle TOUCH_UP
            if (currentEvent.type == Input.TouchEvent.TOUCH_UP) {
                // Screen pressed in game bounds
                if (inBounds(currentEvent, 0, 180, 770, 860)) {
                    if (System.currentTimeMillis() - this.delay_time > 500) {
                        if (tutorial_time) {
                            tutorial_time = false;
                            // TODO: Remove time alteration below
                            this.game_start_time = System.currentTimeMillis();// - (120 * 1000);
                            // First tap to remove tutorial starts a jump
                            this.player1.start_movement(0);
                        } else {
                            Log.i("GameScreenC", "TOUCH_UP detected in bounds");
                            if (this.player_holding) {
                                if ((this.current_time - this.hold_start_time) > (1000 * 1)) {
                                    this.jump_type = 2;
                                } else if ((this.current_time - this.hold_start_time) > (1000 * 0.5)) {
                                    this.jump_type = 1;
                                } else {
                                    this.jump_type = 0;
                                }
                            } else {
                                this.jump_type = 0;
                            }
                            this.player1.start_movement(this.jump_type);
                            this.delay_time = 0;
                            this.jump_type = 0;
                            this.player_holding = false;
                            this.hold_start_time = 0;
                            //Log.i("GameScreen", "player movement started");
                        }
                    }
                }
            }
        }
        // 2. Check miscellaneous events like death:
        if (this.player1.getY_pos() > 805) {
            if (Settings.soundEnabled) {
                death_sound.play(1.0f);
            }
            this.player1.dying = true;
            this.state = GameState.GameOver;
            Log.i("GameScreen", "player fell to DEATH");
            this.currentScore = (int) player1.player_score;

            // Update Leaderboards with newest score
            // TODO: Uncomment below
            //Games.Leaderboards.submitScore(game.getPlayServicesClient(), game.getAppContext().getString(R
            //        .string.leaderboard_high_score), this.currentScore);

            if (this.currentScore > this.highScore) {
                this.highScore = this.currentScore;
                this.newHighScore = true;
                Settings.setHighScore(this.highScore);
                Settings.save(game.getFileIO());
            }
        }

        // ADD FIRST ENEMY AT CERTAIN SPOT; KEEP OR REMOVE?
        if (player1.first_jump) {
            if (!enemy1added) {
                this.enemy_list.add(this.enemy1);
                Log.i("GameScreen", "first enemy spawned");
                this.enemy1added = true;
            }
        }

        // 3. Call individual update methods here.
        // This is where all the game updates happen.
        // For example, player1.update();
        this.player1.update_char();
        //ENEMY UPDATES ABOVE WITH ITER


        // Check player height and update appropriate variables
        if (this.player1.getY_pos() <= 526) { // Same y as in PlayerChar.class
            this.height_thresh1 = true;
            if (this.player1.getY_pos() <= 476) { // Same y as in PlayerChar.class
                this.height_thresh2 = true;
            } else if (this.player1.getY_pos() > 476 && this.height_thresh2) {
                this.height_thresh2 = false;
            }
        } else if (this.player1.getY_pos() > 526 && this.height_thresh1) {
            this.height_thresh1 = false;
        }

        // Check height variables and set falling speed variables
        if (this.height_thresh1) {
            if (this.height_thresh2) { // Same as in PlayerChar.class?
                this.cam_scroll2 = 2.0f;//4
            } else {
                this.cam_scroll2 = 0.0f;
            } // Same as in PlayerChar.class?
            this.cam_scroll1 = 1.0f;//2
        } else {
            this.cam_scroll1 = 0.0f;
        }

        // Update player_jumping for each enemy to cause realistic(?REEVAL) scrolling effect
        for (int i = 0; i < this.enemy_list.size(); i++) {
            // Speed up blocks when player jumps
            if (this.player1.jumped) {
                if (this.enemy_list.get(i).player_jumping == false) {
                    // TODO: Remove line below and associated code
                    //this.enemy_list.get(i).setPlayer_jumping(true);
                }
                //this.enemy_list.get(i).update_enemy();
                //Log.i("TESTING EN1", String.valueOf(this.enemy_list.get(i).getY_pos()));
            } else {
                if (this.enemy_list.get(i).player_jumping == true) {
                    // TODO: Remove line below and associated code
                    //this.enemy_list.get(i).setPlayer_jumping(false);
                }
                //this.enemy_list.get(i).update_enemy();
                //Log.i("TESTING EN2", String.valueOf(this.enemy_list.get(i).getY_pos()));
            }
            // Speed up blocks when player is too high
            if (this.height_thresh1) {
                this.enemy_list.get(i).setY_pos(this.enemy_list.get(i).getY_pos() + 1.0f);//2
                this.enemy_list.get(i).setY_height_thresh_change(1.0f);//2
                if (this.height_thresh2) {
                    this.enemy_list.get(i).setY_pos(this.enemy_list.get(i).getY_pos() + 2.0f);//4
                    this.enemy_list.get(i).setY_height_thresh_change(3f);//6
                }
            }
            this.enemy_list.get(i).update_enemy();
        }

        //Log.i("CAM", String.valueOf(this.cam_scroll1) + "," + String.valueOf(this.cam_scroll2));

        // Increase scroll speed of backgrounds if player is too high
        if (this.cam_scroll1 != 0 && this.cam_scroll2 != 0) {
            this.top_backg_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 1.0f);//2
            this.bot_backg_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 1.0f);//2
            if (this.current_level == 1) {
                this.bot_forestbgtree_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 1.0f);//2
                this.top_forestbgtree_y_pos += ((this.cam_scroll1 + this.cam_scroll2) - 1.0f);//2
            }
        }
        //Log.i("TESTING1A", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
        //Log.i("TESTING2A", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

        // Increase scroll speed of walls and hazard bounds if player is too high
        this.top_walls_y_pos += (this.cam_scroll1 + this.cam_scroll2);
        this.bot_walls_y_pos += (this.cam_scroll1 + this.cam_scroll2);
        for (int i = 0; i < this.hazardBoundsArray.size(); i++) {
            this.hazardBoundsArray.get(i).addY_pos((this.cam_scroll1 + this.cam_scroll2));
        }

        if (this.player1.jumped) {
            // Handle background scrolling as player is jumping
            this.top_backg_y_pos += 2.0f;//4
            this.bot_backg_y_pos += 2.0f;//4
            this.bot_forestbgtree_y_pos += 2.0f;//4
            this.top_forestbgtree_y_pos += 2.0f;//4

            // Handle walls and hazard bounds scrolling as player is jumping
            this.top_walls_y_pos += 4.0f;//7
            this.bot_walls_y_pos += 4.0f;//7
            for (int i = 0; i < this.hazardBoundsArray.size(); i++) {
                this.hazardBoundsArray.get(i).addY_pos(4f);//7
            }
        }

        // Redraw background and walls once they reach the bottom
        if (this.bot_backg_y_pos > 800) {
            //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
            //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
            this.bot_backg_y_pos -= (1024 * 2);
            //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
            //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
        } else if (this.top_backg_y_pos > 800) {
            //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
            //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
            this.top_backg_y_pos -= (1024 * 2);
            //Log.i("GameScreen", "bot_backg"+String.valueOf(this.bot_backg_y_pos));
            //Log.i("GameScreen", "top_backg"+String.valueOf(this.top_backg_y_pos));
        }

        if (this.current_level == 1) {
            if (this.bot_forestbgtree_y_pos > 800) {
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
                this.bot_forestbgtree_y_pos -= (995 * 2);
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
            } else if (this.top_forestbgtree_y_pos > 800) {
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
                this.top_forestbgtree_y_pos -= (995 * 2);
                //Log.i("GameScreen", "bot_tree"+String.valueOf(this.bot_forestbgtree_y_pos));
                //Log.i("GameScreen", "top_tree"+String.valueOf(this.top_forestbgtree_y_pos));
            }
        }

        // CHECK IF WALL REDRAW IS NEEDED, if so check for hazards present
        if (this.bot_walls_y_pos > 800) {
            Log.i("GameScreen", "bot_walls" + String.valueOf(this.bot_walls_y_pos));
            Log.i("GameScreen", "top_walls" + String.valueOf(this.top_walls_y_pos));
            this.walls_blitted += 1;

            this.bot_left_wall_low_hazard = false;
            this.bot_left_wall_high_hazard = false;
/*            if (this.wallHazardHandler.checkForLeftLowHazard(this.walls_blitted)) {
                this.bot_left_wall_low_hazard = true;
            } else if (this.wallHazardHandler.checkForLeftHighHazard(this.walls_blitted)) {
                this.bot_left_wall_high_hazard = true;
            }*/
            // Replacing above code:
            if (this.wallHazardHandler.checkForLeftLowHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.bot_left_wall_low_hazard = true;
            }
            if (this.wallHazardHandler.checkForLeftHighHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.bot_left_wall_high_hazard = true;
            }

            this.bot_right_wall_low_hazard = false;
            this.bot_right_wall_high_hazard = false;
/*            if (this.wallHazardHandler.checkForRightLowHazard(this.walls_blitted)) {
                this.bot_right_wall_low_hazard = true;
            } else if (this.wallHazardHandler.checkForRightHighHazard(this.walls_blitted)) {
                this.bot_right_wall_high_hazard = true;
            }*/
            // Replacing above code:
            if (!this.bot_left_wall_low_hazard && this.wallHazardHandler.checkForRightLowHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.bot_right_wall_low_hazard = true;
            }
            if (!this.bot_left_wall_high_hazard && this.wallHazardHandler.checkForRightHighHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.bot_right_wall_high_hazard = true;
            }
            

            // Add WallHazards to hazardBoundsArray if appropriate
            if (this.current_level == 1) {
                this.bot_walls_y_pos -= (806 * 2);
                if (this.bot_left_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(89, (this.bot_walls_y_pos + 397), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.bot_left_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(89, (this.bot_walls_y_pos + 4), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.bot_right_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(331, (this.bot_walls_y_pos + 397), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.bot_right_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(331, (this.bot_walls_y_pos + 4), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
            } else {
                this.bot_walls_y_pos -= (1099 * 2);
                if (this.bot_left_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(59, (this.bot_walls_y_pos + 907), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.bot_left_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(59, (this.bot_walls_y_pos + 279), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.bot_right_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(386, (this.bot_walls_y_pos + 907), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.bot_right_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(386, (this.bot_walls_y_pos + 279), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
            }

            //Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
            //Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));

        // Repeat above for top_walls
        } else if (this.top_walls_y_pos > 800) {
            //Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
            //Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));
            this.walls_blitted += 1;
            this.top_left_wall_low_hazard = false;
            this.top_left_wall_high_hazard = false;
/*            if (this.wallHazardHandler.checkForLeftLowHazard(this.walls_blitted)) {
                this.top_left_wall_low_hazard = true;
            } else if (this.wallHazardHandler.checkForLeftHighHazard(this.walls_blitted)) {
                this.top_left_wall_high_hazard = true;
            }*/
            // Replacing above code:
            if (this.wallHazardHandler.checkForLeftLowHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.top_left_wall_low_hazard = true;
            }
            if (this.wallHazardHandler.checkForLeftHighHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.top_left_wall_high_hazard = true;
            }

            this.top_right_wall_low_hazard = false;
            this.top_right_wall_high_hazard = false;
/*            if (this.wallHazardHandler.checkForRightLowHazard(this.walls_blitted)) {
                this.top_right_wall_low_hazard = true;
            } else if (this.wallHazardHandler.checkForRightHighHazard(this.walls_blitted)) {
                this.top_right_wall_high_hazard = true;
            }*/
            // Replacing above code:
            if (!this.top_left_wall_low_hazard && this.wallHazardHandler.checkForRightLowHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.top_right_wall_low_hazard = true;
            }
            if (!this.top_left_wall_high_hazard && this.wallHazardHandler.checkForRightHighHazard(System.currentTimeMillis() - this.game_start_time)) {
                this.top_right_wall_high_hazard = true;
            }

            if (this.current_level == 1) {
                this.top_walls_y_pos -= (806 * 2);
                if (this.top_left_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(89, (this.top_walls_y_pos + 397), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.top_left_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(89, (this.top_walls_y_pos + 4), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.top_right_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(331, (this.top_walls_y_pos + 397), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.top_right_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(331, (this.top_walls_y_pos + 4), 60, 52);
                    this.hazardBoundsArray.add(hazardRect);
                }
            } else {
                this.top_walls_y_pos -= (1099 * 2);
                if (this.top_left_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(59, (this.top_walls_y_pos + 907), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.top_left_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(59, (this.top_walls_y_pos + 279), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.top_right_wall_low_hazard) {
                    WallHazard hazardRect = new WallHazard(386, (this.top_walls_y_pos + 907), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
                if (this.top_right_wall_high_hazard) {
                    WallHazard hazardRect = new WallHazard(386, (this.top_walls_y_pos + 279), 35, 121);
                    this.hazardBoundsArray.add(hazardRect);
                }
            }
            //Log.i("GameScreen", "bot_walls"+String.valueOf(this.bot_walls_y_pos));
            //Log.i("GameScreen", "top_walls"+String.valueOf(this.top_walls_y_pos));
        }


        for (int i = 0; i < this.enemy_list.size(); i++) {
            Log.i("GameScreen", "Starting overlap checks");
            if (!this.player1.dying) {// == false) {//FIX FOR CHAR DEATH/!DEATH
                //Log.i("GameScreen", "Char still alive");
                // Handle CircleRect collisions separately from RectRect collisions
                if (this.enemy_list.get(i).getEnemy_num() == 7) {
                    //Log.i("GameScreen", "Checking circle enemy");
                    //Log.i("GameScreen", "Checking Enemy 7");
                    //Log.i("GS", String.valueOf(this.enemy_list.get(i).bounds.x));
                    //Log.i("GS", String.valueOf(this.enemy_list.get(i).bounds.y));
                    for (int z = 0; z < this.player1.getCurrentSpriteBounds().size(); z++) {
                        //Log.i("GameScreen", "Checking for Overlap1");
                        // Check for a collision between circle and all player_rects
                        if (OverlapTester.overlapCircleRectangle((Circle) this.enemy_list.get(i).bounds, this.player1.getCurrentSpriteBounds().get(z))) {
                            //this.player1.dying = true;
                            Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                            // CHANGE TO DIFF SOUND EFFECT
                            if (Settings.soundEnabled) {
                                death_sound.play(1f);
                            }
                            // Leave loop if collision found and death sequence initiated
                            break;
                        }
                    }
                } else {
                    //Log.i("GameScreen", "Checking non-circle enemies");
                    for (int z = 0; z < this.enemy_list.get(i).bounds_tsil.size(); z++) {
                        //Log.i("GameScreen", "Going through each enemy bounds rect");
                        for (int y = 0; y < this.player1.getCurrentSpriteBounds().size(); y++) {

                    /*            Log.i("GameScreen", "Checking for Overlap2, every player bounds rect");

                                Log.i("GameScreenPC1", String.valueOf(this.player1.x_pos+","+this.player1.y_pos));

                                Log.i("GameScreenPC2", String.valueOf(this.player1.getCurrentSpriteBounds().get(y).getLowerLeft().getX()+","+
                                        this.player1.getCurrentSpriteBounds().get(y).getLowerLeft().getY()+"..."+
                                this.player1.getCurrentSpriteBounds().get(y).width)+","+
                                this.player1.getCurrentSpriteBounds().get(y).height);*/

                            Rectangle curr_rect = (Rectangle) this.enemy_list.get(i).bounds_tsil.get(z);
                            //Log.i("GameScreenEnemy", String.valueOf(curr_rect.getLowerLeft()
                                    //.getX() + "," + curr_rect.getLowerLeft().getY() + "..." +
                                    //curr_rect.width + "," + curr_rect.height));
                            // Check for collisions between all enemy_rects and all player_rects
                            if (OverlapTester.overlapRectangles(this.player1.getCurrentSpriteBounds().get(y), (Rectangle) this.enemy_list.get(i).bounds_tsil.get(z))) {
                                // TODO: Uncomment below
                                //this.player1.dying = true;
                                Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                                // CHANGE TO DIFF SOUND EFFECT
                                if (Settings.soundEnabled) {
                                    death_sound.play(1f);
                                }
                                // Leave loop if collision found and death sequence initiated
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < this.hazardBoundsArray.size(); i++) {
            if (!this.player1.dying) {// == false) {
                for (int z = 0; z < this.player1.getCurrentSpriteBounds().size(); z++) {
                    //Log.i("GameScreen", "Checking for Overlap3");
                    //Log.i("GameScreenPC1", String.valueOf(this.player1.x_pos + "," + this
                            //.player1.y_pos));
                    //Log.i("GameScreenPC2", String.valueOf(this.player1.getCurrentSpriteBounds()
                            //.get(z).getLowerLeft().getX() + "," +
                            //this.player1.getCurrentSpriteBounds().get(z).getLowerLeft().getY() +
                            //"..." + this.player1.getCurrentSpriteBounds().get(z).width + "," +
                            // this.player1.getCurrentSpriteBounds().get(z).height));
                    //Log.i("GameScreenHazard", String.valueOf(this.hazardBoundsArray.get(i)
                            //.getLowerLeft().getX() + "," +
                            //this.hazardBoundsArray.get(i).getLowerLeft().getY() + "..." + this
                            //.hazardBoundsArray.get(i).width + "," +
                            //this.hazardBoundsArray.get(i).height));
                    // Check for collisions between all hazard_rects and all player_rects
                    if (OverlapTester.overlapRectangles(this.player1.getCurrentSpriteBounds().get(z), this.hazardBoundsArray.get(i))) {
                        // TODO: Uncomment below
                        //this.player1.dying = true;
                        Log.i("OVERLAP FOUND", String.valueOf(this.player1.getX_pos()));
                        // CHANGE TO DIFF SOUND EFFECT
                        if (Settings.soundEnabled) {
                            death_sound.play(1f);
                        }
                        // Leave loop if collision found and death sequence initiated
                        break;
                    }
                }
            }
        }

        //Log.i("GameScreen", "update2, char and enemy");

        // Check if it is time to spawn a new enemy
        if (player1.first_jump) {
            try {
                if ((System.currentTimeMillis() - this.game_start_time) >  // Switched out current_time for System.currentTime
                        (enemySpawnEventArray.get(enemy_count).enemy_spawn_time) * 1000) {
                    this.timerRunnable.run();
                }
            // If end of enemySpawnEventArray reached, an IndexOutOfBoundsException will occur
            } catch (IndexOutOfBoundsException e) {
                Log.i("GameScreen", "NO MORE ENEMIES");
            }
        }

        // Clean up enemies off screen
        for (int i = 0; i < this.enemy_list.size(); i++) {
            if ((this.enemy_list.get(i).getY_pos() > 820)) {
                this.enemy_list.remove(i);
            }
        }
        // Clean up hazards off screen
        for (int i = 0; i < this.hazardBoundsArray.size(); i++) {
            if (this.hazardBoundsArray.get(i).getY_pos() > 800) {
                this.hazardBoundsArray.remove(i);;
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    private void updatePaused(List<Input.TouchEvent> touchEvents) {
        int touchEventsSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents.get(touchEventIndex);
            // The user picks up their finger
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                // If Resume Button Pressed
/*                int SETTINGS_CHANGE_X_POSITION = 94;
                int SETTINGS_CHANGE_Y_POSITION = 418 - 150;
                int SETTINGS_CHANGE_WIDTH = 294;
                int SETTINGS_CHANGE_HEIGHT = 68;
                int SETTINGS_BACK_Y_POSITION = 522 - 150;
                int SETTINGS_BACK_WIDTH = 88;
                int SETTINGS_BACK_HEIGHT = 66;*/
                if (inBounds(currentEvent, UI_WINDOW_X_POSITION + 33, UI_WINDOW_Y_POSITION + 46, 293, 70) && !drawInGameSettings) {
                    resume();
                } else if (inBounds(currentEvent, UI_WINDOW_X_POSITION + 33, UI_WINDOW_Y_POSITION + 146, 293, 70) && !drawInGameSettings) {
                    drawInGameSettings = true;
                } else if (inBounds(currentEvent, UI_WINDOW_X_POSITION + 33, UI_WINDOW_Y_POSITION + 244, 323, 70) && !drawInGameSettings) {
                    dispose();
                    nullify();
                    goToMenu();
                    return;
                } else if (drawInGameSettings && Settings.soundEnabled && inBounds(currentEvent, SETTINGS_CHANGE_X_POSITION, SETTINGS_CHANGE_Y_POSITION, SETTINGS_CHANGE_WIDTH, SETTINGS_CHANGE_HEIGHT)) {
                Settings.soundEnabled = false;
                } else if (drawInGameSettings && !Settings.soundEnabled && inBounds(currentEvent, SETTINGS_CHANGE_X_POSITION, SETTINGS_CHANGE_Y_POSITION, SETTINGS_CHANGE_WIDTH, SETTINGS_CHANGE_HEIGHT)) {
                Settings.soundEnabled = true;
                // If Settings window open, check for back button press or SETTINGS button press to
                // update appropriate variable and save Settings file
                } else if (drawInGameSettings && inBounds(currentEvent, SETTINGS_BACK_X_POSITION, SETTINGS_BACK_Y_POSITION, SETTINGS_BACK_WIDTH, SETTINGS_BACK_HEIGHT) || inBounds(currentEvent, 95, 680, 290, 76)) {
                drawInGameSettings = false;
                Settings.save(game.getFileIO());
                }
            }
        }
    }

    // UPDATE FOR GAME OVER IMPLEMENTATION
    private void updateGameOver(List<Input.TouchEvent> touchEvents) {
        int touchEventsSize = touchEvents.size();
        for (int touchEventIndex = 0; touchEventIndex < touchEventsSize; touchEventIndex++) {
            Input.TouchEvent currentEvent = (Input.TouchEvent) touchEvents
                    .get(touchEventIndex);
            // If the user touches anywhere in the screen
            // while the game over screen is shown,
            // clean up and return to the menu (on a new game.)
            if (currentEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                if (inBounds(currentEvent, 0, 80, 800, 400)) {
                    dispose();
                    nullify();
                    game.setScreen(new GameScreen(game));  // CREATES NEW GAMESCREEN EVERY GAME
                    state = GameState.Ready;
                    //delay_time = System.currentTimeMillis();
                    return;
                }
            }
        }

    }

    //@Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);

        if (current_level == 1) {
            // Now game elements:
            // Draw two sets of scrolling backgrounds
            g.drawImage(forest_background_img, 0, this.top_backg_y_pos);
            g.drawImage(forest_background_img, 0, this.bot_backg_y_pos);

            // Draw two sets of scrolling trees
            g.drawImage(forest_tree_background_img, 170, this.top_forestbgtree_y_pos);
            g.drawImage(forest_tree_background_img, 170, this.bot_forestbgtree_y_pos);

            //Log.i("TESTING1", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            //Log.i("TESTING2", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

            // Draw two sets of left walls, take into account possibility of WallHazards
            int FOREST_LEFT_WALL_X_POSITION = 0;
            if (this.top_left_wall_low_hazard) {
                g.drawImage(forest_lowhaz_left_wall_img, FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_left_wall_high_hazard) {
                g.drawImage(forest_highhaz_left_wall_img, FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(forest_nohaz_left_wall_img, FOREST_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_left_wall_low_hazard) {
                g.drawImage(forest_lowhaz_left_wall_img, FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_left_wall_high_hazard) {
                g.drawImage(forest_highhaz_left_wall_img, FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(forest_nohaz_left_wall_img, FOREST_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            }

            // Draw two sets of right walls, take into account possibility of WallHazards
            int FOREST_RIGHT_WALL_X_POSITION = 390;
            int FOREST_RIGHT_WALL_HAZARD_X_POSITION = 300;
            if (this.top_right_wall_low_hazard) {
                g.drawImage(forest_lowhaz_right_wall_img, FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_right_wall_high_hazard) {
                g.drawImage(forest_highhaz_right_wall_img, FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(forest_nohaz_right_wall_img, FOREST_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_right_wall_low_hazard) {
                g.drawImage(forest_lowhaz_right_wall_img, FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_right_wall_high_hazard) {
                g.drawImage(forest_highhaz_right_wall_img, FOREST_RIGHT_WALL_HAZARD_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(forest_nohaz_right_wall_img, FOREST_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);
            }
            
        } else if (current_level == 2) {
            // Now game elements:
            // Draw two sets of scrolling backgrounds
            g.drawImage(factory_background_img, 0, this.top_backg_y_pos);
            g.drawImage(factory_background_img, 0, this.bot_backg_y_pos);
            //Log.i("TESTING1", String.valueOf(this.top_backg_y_pos) + "," + String.valueOf(this.bot_backg_y_pos));
            //Log.i("TESTING2", String.valueOf(this.top_walls_y_pos) + "," + String.valueOf(this.bot_walls_y_pos));

            // Draw two sets of left walls, take into account possibility of WallHazards
            int FACTORY_LEFT_WALL_X_POSITION = 0;
            if (this.top_left_wall_low_hazard) {
                g.drawImage(factory_lowhaz_left_wall_img, FACTORY_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_left_wall_high_hazard) {
                g.drawImage(factory_highhaz_left_wall_img, FACTORY_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(factory_nohaz_left_wall_img, FACTORY_LEFT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_left_wall_low_hazard) {
                g.drawImage(factory_lowhaz_left_wall_img, FACTORY_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_left_wall_high_hazard) {
                g.drawImage(factory_highhaz_left_wall_img, FACTORY_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(factory_nohaz_left_wall_img, FACTORY_LEFT_WALL_X_POSITION, this.bot_walls_y_pos);
            }

            // Draw two sets of right walls, take into account possibility of WallHazards
            int FACTORY_RIGHT_WALL_X_POSITION = 386;
            int FACTORY_RIGHT_WALL_HAZARD_X_POSITION = 260;
            if (this.top_right_wall_low_hazard) {
                g.drawImage(factory_lowhaz_right_wall_img, FACTORY_RIGHT_WALL_HAZARD_X_POSITION, this.top_walls_y_pos);
            } else if (this.top_right_wall_high_hazard) {
                g.drawImage(factory_highhaz_right_wall_img, FACTORY_RIGHT_WALL_HAZARD_X_POSITION, this.top_walls_y_pos);
            } else {
                g.drawImage(factory_nohaz_right_wall_img, FACTORY_RIGHT_WALL_X_POSITION, this.top_walls_y_pos);
            }
            if (this.bot_right_wall_low_hazard) {
                g.drawImage(factory_lowhaz_right_wall_img, FACTORY_RIGHT_WALL_HAZARD_X_POSITION, this.bot_walls_y_pos);
            } else if (this.bot_right_wall_high_hazard) {
                g.drawImage(factory_highhaz_right_wall_img, FACTORY_RIGHT_WALL_HAZARD_X_POSITION, this.bot_walls_y_pos);
            } else {
                g.drawImage(factory_nohaz_right_wall_img, FACTORY_RIGHT_WALL_X_POSITION, this.bot_walls_y_pos);
            }

        }
        Log.i("GameScreenWB", String.valueOf(this.walls_blitted));

        // Draw all enemy images to screen based on objects in enemy_list
        for (int i = 0; i < this.enemy_list.size(); i++) {
            FallingEnemy current_enemy_draw = (FallingEnemy) this.enemy_list.get(i);
            if (current_enemy_draw.getEnemy_num() == 1) {
                g.drawImage(log_enemy_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
/*            } else if (current_enemy_draw.getEnemy_num() == 2) {
                g.drawImage(g.newImage("enemy_image2.png", Graphics.ImageFormat.ARGB8888), (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());*/
            } else if (current_enemy_draw.getEnemy_num() == 3) {
                if (current_enemy_draw.getImageName() == 0) {
                    g.drawImage(apple_enemy_img1, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                } else if (current_enemy_draw.getImageName() == 1) {
                    g.drawImage(apple_enemy_img2, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                } else {
                    g.drawImage(apple_enemy_img3, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                }
            } else if (current_enemy_draw.getEnemy_num() == 4) {
                if (current_enemy_draw.getImageName() == 0) {
                    g.drawImage(bird_enemy_reverse_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                } else {
                    g.drawImage(bird_enemy_norm_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                }
            } else if (current_enemy_draw.getEnemy_num() == 5) {
                if (current_enemy_draw.getImageName() == 0) {
                    g.drawImage(monkey_enemy_reverse_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                } else {
                    g.drawImage(monkey_enemy_norm_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                }
            } else if (current_enemy_draw.getEnemy_num() == 6) {
                g.drawImage(crate_enemy_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            } else if (current_enemy_draw.getEnemy_num() == 7) {
                if (current_enemy_draw.getImageName() == 0) {
                    g.drawImage(wheel_enemy_img1, (int) (this.enemy_list.get(i).getX_pos() - 40f), (int) (this.enemy_list.get(i).getY_pos() - 40f));
                } else if (current_enemy_draw.getImageName() == 1) {
                    g.drawImage(wheel_enemy_img2, (int) (this.enemy_list.get(i).getX_pos() - 40f), (int) (this.enemy_list.get(i).getY_pos() - 40f));
                } else if (current_enemy_draw.getImageName() == 2) {
                    g.drawImage(wheel_enemy_img3, (int) (this.enemy_list.get(i).getX_pos() - 40f), (int) (this.enemy_list.get(i).getY_pos() - 40f));
                } else {
                    g.drawImage(wheel_enemy_img4, (int) (this.enemy_list.get(i).getX_pos() - 40f), (int) (this.enemy_list.get(i).getY_pos() - 40f));
                }
            } else if (current_enemy_draw.getEnemy_num() == 8) {
                if (current_enemy_draw.getImageName() == 0) {
                    g.drawImage(hammer_enemy_norm_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                } else {
                    g.drawImage(hammer_enemy_reverse_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                }
            } else if (current_enemy_draw.getEnemy_num() == 9) {
                if (current_enemy_draw.getImageName() == 0) {
                    g.drawImage(robothead_enemy_norm_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                } else {
                    g.drawImage(robothead_enemy_reverse_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
                }
            } else {
                g.drawImage(drone_enemy_img, (int) this.enemy_list.get(i).getX_pos(), (int) this.enemy_list.get(i).getY_pos());
            }
        }

        // Draw player1 to screen, use getSpriteImage to blit correct sprite
        g.drawImage(this.getSpriteImage(), (int) this.player1.getX_pos(), (int) this.player1.getY_pos());

        // And now, we overlay the UI:
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }
    

    @Override
    public void dispose() {
        if (this.music_started) {
            this.game_music.dispose();
            this.death_sound.dispose();
        }
        Log.i("GameScreen", "dispose");

    }

    @Override
    public void resume() {
        // Keep time paused from affecting game_time by adding time_paused to game_start_time
        long time_paused = System.currentTimeMillis() - this.pause_time;
        // Keep music in sync despite pause
        if (Settings.soundEnabled) {
            if (this.music_started) {
                this.game_music.play();//was seekBegin()
            } else {
                this.music_started = true;
                this.game_music = (AndroidMusic) game.getAudio().newMusic("179562__clinthammer__clinthammermusic-ts-bass.wav");
                this.game_music.play();
                this.game_music.setLooping(true);
                this.death_sound = (AndroidSound) game.getAudio().newSound("Realistic_Punch.wav");
            }
        }
        this.game_start_time += time_paused;
        // Update GameState to Running to ensure updateRunning is being called
        this.state = GameState.Running;
        this.delay_time = System.currentTimeMillis();
    }


    @Override
    public void pause() {
        this.pause_time = System.currentTimeMillis();
        if (Settings.soundEnabled) {
            this.game_music.pause();
        }
        Settings.save(game.getFileIO());
        // Update GameState to Paused to ensure updateRunning stops being called
        this.state = GameState.Paused;
    }

    private void nullify() {
        // Set all variables to null. We'll recreate
        // them in the constructor.
        //UPDATE TO PROPERLY CLEAN UP VARIABLES
        Log.i("GameScreen", "nullify");

        paint = null;
        paint2 = null;
        paint3 = null;
        paint4 = null;
        paint5 = null;

        this.player1 = null;
        this.wallHazardHandler = null;

        this.enemy_list.clear();
        this.hazardBoundsArray.clear();
        this.enemySpawnEventArray.clear();

        this.apple_enemy_img1 = null;
        this.forest_background_img = null;
        this.forest_tree_background_img = null;
        this.forest_nohaz_left_wall_img = null;
        this.forest_lowhaz_left_wall_img = null;
        this.forest_highhaz_left_wall_img = null;
        this.forest_nohaz_right_wall_img = null;
        this.forest_lowhaz_right_wall_img = null;
        this.forest_highhaz_right_wall_img = null;
        this.factory_background_img = null;
        this.factory_nohaz_left_wall_img = null;
        this.factory_lowhaz_left_wall_img = null;
        this.factory_highhaz_left_wall_img = null;
        this.factory_nohaz_right_wall_img = null;
        this.factory_lowhaz_right_wall_img = null;
        this.factory_highhaz_right_wall_img = null;
        this.log_enemy_img = null;
        this.apple_enemy_img1 = null;
        this.apple_enemy_img2 = null;
        this.apple_enemy_img3 = null;
        this.bird_enemy_reverse_img = null;
        this.bird_enemy_norm_img = null;
        this.monkey_enemy_reverse_img = null;
        this.monkey_enemy_norm_img = null;
        this.crate_enemy_img = null;
        this.wheel_enemy_img1 = null;
        this.wheel_enemy_img2 = null;
        this.wheel_enemy_img3 = null;
        this.wheel_enemy_img4 = null;
        this.hammer_enemy_norm_img = null;
        this.hammer_enemy_reverse_img = null;
        this.robothead_enemy_norm_img = null;
        this.robothead_enemy_reverse_img = null;
        this.drone_enemy_img = null;

        this.pause_button_rdy_img = null;
        this.UI_paused_window_img = null;
        this.pause_button_pressed_img = null;
        sound_enabled_img = null;
        sound_disabled_img = null;

        this.SPRITE_LEFT_WALL_HANG = null;
        this.SPRITE_RIGHT_WALL_HANG = null;
        this.SPRITE_RIGHT_DYING_EARLY = null;
        this.SPRITE_LEFT_DYING_EARLY = null;
        this.SPRITE_RIGHT_DYING_LATE = null;
        this.SPRITE_LEFT_DYING_LATE = null;
        this.SPRITE_RIGHT_3 = null;
        this.SPRITE_LEFT_3 = null;
        this.SPRITE_RIGHT_4 = null;
        this.SPRITE_LEFT_4 = null;
        this.SPRITE_RIGHT_5 = null;
        this.SPRITE_LEFT_5 = null;
        this.SPRITE_RIGHT_6 = null;
        this.SPRITE_LEFT_6 = null;
        this.SPRITE_RIGHT_7 = null;
        this.SPRITE_LEFT_7 = null;
        this.SPRITE_RIGHT_8 = null;
        this.SPRITE_LEFT_8 = null;
        this.SPRITE_RIGHT_10 = null;
        this.SPRITE_LEFT_10 = null;
        this.SPRITE_RIGHT_SLIDING1 = null;
        this.SPRITE_LEFT_SLIDING1 = null;
        this.SPRITE_RIGHT_SLIDING2 = null;
        this.SPRITE_LEFT_SLIDING2 = null;
        this.SPRITE_RIGHT_SLIDING3 = null;
        this.SPRITE_LEFT_SLIDING3 = null;
        this.ready_splash_image = null;

        this.game_music = null;
        this.death_sound = null;

        // Call the garbage collector to clean up our memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawImage(ready_splash_image, 0, 0);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        // ADD RUNNING UI
        if (this.player1.player_score > 1000) {
            g.drawString((String.valueOf((int) this.player1.player_score) + "m"), 40, 40, paint3);
        } else {
            g.drawString((String.valueOf((int) this.player1.player_score) + "m"), 40, 40, paint2);
        }

        // UPDATE WHEN JUMP METER CREATED
        if (this.player_holding) {
            if ((this.current_time - this.hold_start_time) > (1000 * 1)) {
                g.drawImage(jump_meter_fill3_img, 15, 725);
            } else if ((this.current_time - this.hold_start_time) > (1000 * 0.5)) {
                g.drawImage(jump_meter_fill2_img, 15, 725);
            } else {
                g.drawImage(jump_meter_fill1_img, 15, 725);
            }
        } else {
            g.drawImage(jump_meter_nofill_img, 15, 725);
        }

        if (!tutorial_time) {
            g.drawImage(pause_button_rdy_img, 410, 20);
        }

/*        Log.i("CURRENT TIME", String.valueOf(current_time));
        Log.i("START TIME", String.valueOf(game_start_time));
        Log.i("TIME DIFF", String.valueOf(current_time - game_start_time));*/

        // Handle transition from level 1 to level 2
        if (((this.current_time - this.game_start_time) > (140 * 1000)) && transition_incomplete && !tutorial_time) {
            Log.i("STARTING", String.valueOf(this.opacity_num));

            // Draw an overlay that becomes completely white then completely transparent
            g.drawARGB(this.opacity_num, 255, 255, 255);
            if (this.opacity_num < 255 && !this.reached_255_opacity) {
                // Gradually increase image brightness until white
                this.opacity_num += 25;
            } else if (this.opacity_num == 255) {
                // Screen completely white, blit with level 2 images
                this.opacity_num -= 25;
                this.reached_255_opacity = true;
                this.current_level = 2;
                this.bot_backg_y_pos = -224;
                this.top_backg_y_pos = -1248;
                this.bot_walls_y_pos = -299;
                this.top_walls_y_pos = -1398;
                this.player1.player_score += 15f;
                // Clear hazards present from level 1, in both bounds and blits
                this.hazardBoundsArray.clear();
                this.top_left_wall_high_hazard = false;
                this.top_left_wall_low_hazard = false;
                this.top_right_wall_high_hazard = false;
                this.top_right_wall_low_hazard = false;
                this.bot_left_wall_high_hazard = false;
                this.bot_left_wall_low_hazard = false;
                this.bot_right_wall_high_hazard = false;
                this.bot_right_wall_low_hazard = false;

            } else if (this.opacity_num < 255 && this.reached_255_opacity) {
                // Gradually decrease image brightness until transparent
                this.opacity_num -= 25;
            }
            if (this.opacity_num < 0) {
                transition_incomplete = false;
                Log.i("ENDING", "TRANSITION");
            }
        }

        // Draw tutorial info at the start of new games
        if (tutorial_time) {
            g.drawString("Tap anywhere to jump", 240, 350, paint5);
            g.drawString("Avoid the obstacles", 240, 400, paint5);
            g.drawString("falling from above!", 240, 430, paint5);
        }

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the screen to display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        if (drawInGameSettings) {
/*            int SETTINGS_WINDOW_X_POSITION = 50;
            int SETTINGS_WINDOW_Y_POSITION = 388 - 150;*/
            if (Settings.soundEnabled) {
                g.drawImage(this.sound_enabled_img, SETTINGS_WINDOW_X_POSITION, SETTINGS_WINDOW_Y_POSITION);
            } else if (!Settings.soundEnabled) {
                g.drawImage(this.sound_disabled_img, SETTINGS_WINDOW_X_POSITION, SETTINGS_WINDOW_Y_POSITION);
            }
        } else {
            g.drawImage(UI_paused_window_img, UI_WINDOW_X_POSITION, UI_WINDOW_Y_POSITION);
        }
        g.drawImage(pause_button_pressed_img, 410, 20);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        //g.drawRect(0, 0, 482, 802, Color.BLACK);
        g.drawString("You Lose!", 240, 200, paint);
        // SHOW CURRENT AND HIGH SCORES
        g.drawString("Tap to Try Again!", 240, 320, paint);
        g.drawString("Score: " + String.valueOf(this.currentScore) + "m", 240, 450, paint);
        if (newHighScore) {
            g.drawString("NEW HIGH SCORE!", 240, 550, paint2);
        }
    }

    private void goToMenu() {
        game.setScreen(new MainMenuScreen(game));
    }

    // Load all image assets needed for game, called during GameState.READY
    private void loadAssets() {
/*        AssetWorkerTask assetWorkerTask = new AssetWorkerTask();
        assetWorkerTask.execute(game);*/

        Graphics g = game.getGraphics();
        Log.i("GameScreen", "Loading Assets...");

        forest_background_img = g.newImage("Forest_Backgroundhighres.png", Graphics.ImageFormat
                .ARGB8888);
        forest_tree_background_img = g.newImage("Forest_TreeandBrancheshighres.png", Graphics
                .ImageFormat.ARGB8888);
        forest_nohaz_left_wall_img = g.newImage("Forest_Left_Wallhighres-90px.png", Graphics
                .ImageFormat.ARGB8888);
        forest_lowhaz_left_wall_img = g.newImage("Forest_Left_Wallhighres-lowhazard,180px.png",
                Graphics.ImageFormat.ARGB8888);
        forest_highhaz_left_wall_img = g.newImage("Forest_Left_Wallhighres-highhazard,180px.png",
                Graphics.ImageFormat.ARGB8888);
        forest_nohaz_right_wall_img = g.newImage("Forest_Right_Wallhighres-90px.png", Graphics
                .ImageFormat.ARGB8888);
        forest_lowhaz_right_wall_img = g.newImage("Forest_Right_Wallhighres-lowhazard,180px.png",
                Graphics.ImageFormat.ARGB8888);
        forest_highhaz_right_wall_img = g.newImage("Forest_Right_Wallhighres-highhazard,180px.png",
                Graphics.ImageFormat.ARGB8888);
        
        factory_background_img = g.newImage("RobotFactoryBackgroundhighres_lightson.png", Graphics.ImageFormat.ARGB8888);
        factory_nohaz_left_wall_img = g.newImage("FactoryLeft_Wallhighres-94px.png", Graphics.ImageFormat.ARGB8888);
        factory_lowhaz_left_wall_img = g.newImage("FactoryLeft_Wallhighres-lowhazard, 220px, glow.png", Graphics.ImageFormat.ARGB8888);
        factory_highhaz_left_wall_img = g.newImage("FactoryLeft_Wallhighres-highhazard, 220px, glow.png", Graphics.ImageFormat.ARGB8888);
        factory_nohaz_right_wall_img = g.newImage("FactoryRight_Wallhighres-94px.png", Graphics.ImageFormat.ARGB8888);
        factory_lowhaz_right_wall_img = g.newImage("FactoryRight_Wallhighres-lowhazard, 220px, glow.png", Graphics.ImageFormat.ARGB8888);
        factory_highhaz_right_wall_img = g.newImage("FactoryRight_Wallhighres-highhazard, 220px, glow.png", Graphics.ImageFormat.ARGB8888);
        
        log_enemy_img = g.newImage("LogEnemyhighres-100px,80perc.png", Graphics.ImageFormat
                .ARGB8888);
        apple_enemy_img1 = g.newImage("AppleEnemyhighres_75perc.png", Graphics.ImageFormat
                .ARGB8888);
        apple_enemy_img2 = g.newImage("AppleEnemyhighres_flat,75perc.png", Graphics.ImageFormat
                .ARGB8888);
        apple_enemy_img3 = g.newImage("AppleEnemyhighres_reverse,75perc.png", Graphics.ImageFormat
                .ARGB8888);
        bird_enemy_reverse_img = g.newImage("BirdEnemyhighres_reverse.png", Graphics.ImageFormat.ARGB8888);
        bird_enemy_norm_img = g.newImage("BirdEnemyhighres.png", Graphics.ImageFormat.ARGB8888);
        monkey_enemy_reverse_img = g.newImage("MonkeyEnemyhighres_reverse-90px,80perc.png", Graphics
                .ImageFormat.ARGB8888);
        monkey_enemy_norm_img = g.newImage("MonkeyEnemyhighres-90px,80perc.png", Graphics
                .ImageFormat.ARGB8888);
        crate_enemy_img = g.newImage("Factory_Cratehighres-95px,70perc.png", Graphics.ImageFormat
                .ARGB8888);
        wheel_enemy_img1 = g.newImage("Factory_Wheelhighres_square-80px.png", Graphics.ImageFormat.ARGB8888);
        wheel_enemy_img2 = g.newImage("Factory_Wheelhighres_square_90deg-80px.png", Graphics.ImageFormat.ARGB8888);
        wheel_enemy_img3 = g.newImage("Factory_Wheelhighres_square_180deg-80px.png", Graphics.ImageFormat.ARGB8888);
        wheel_enemy_img4 = g.newImage("Factory_Wheelhighres_square_270deg-80px.png", Graphics.ImageFormat.ARGB8888);
        hammer_enemy_norm_img = g.newImage("Factory_Hammerhighres-100pxh.png", Graphics.ImageFormat.ARGB8888);
        hammer_enemy_reverse_img = g.newImage("Factory_Hammerhighres_reverse-100pxh.png", Graphics.ImageFormat.ARGB8888);
        robothead_enemy_norm_img = g.newImage("Factory_RobotHeadhighres-60px.png", Graphics.ImageFormat.ARGB8888);
        robothead_enemy_reverse_img = g.newImage("Factory_RobotHeadhighres_reverse-60px.png", Graphics.ImageFormat.ARGB8888);
        drone_enemy_img = g.newImage("Factory_Dronehighres-95px.png", Graphics.ImageFormat.ARGB8888);

        pause_button_rdy_img = g.newImage("Pause.png", Graphics.ImageFormat.ARGB8888);
        UI_paused_window_img = g.newImage("UI-Windowfilledhighres.png", Graphics.ImageFormat.ARGB8888);
        pause_button_pressed_img = g.newImage("Pause_Active.png", Graphics.ImageFormat.ARGB8888);
        jump_meter_nofill_img = g.newImage("JumpMeterhighres_nofill.png", Graphics.ImageFormat.ARGB8888);
        jump_meter_fill1_img = g.newImage("JumpMeterhighres_fill1.png", Graphics.ImageFormat.ARGB8888);
        jump_meter_fill2_img = g.newImage("JumpMeterhighres_fill2.png", Graphics.ImageFormat.ARGB8888);
        jump_meter_fill3_img = g.newImage("JumpMeterhighres_fill3.png", Graphics.ImageFormat.ARGB8888);


        SPRITE_LEFT_WALL_HANG = g.newImage("Sprite2highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_WALL_HANG = g.newImage("Sprite2highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_DYING_EARLY = g.newImage("Sprite13highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_DYING_EARLY = g.newImage("Sprite13highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_DYING_LATE = g.newImage("Sprite14highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_DYING_LATE = g.newImage("Sprite14highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_3 = g.newImage("Sprite3highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_3 = g.newImage("Sprite3highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_4 = g.newImage("Sprite4highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_4 = g.newImage("Sprite4highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_5 = g.newImage("Sprite5highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_5 = g.newImage("Sprite5highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_6 = g.newImage("Sprite6highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_6 = g.newImage("Sprite6highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_7 = g.newImage("Sprite7highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_7 = g.newImage("Sprite7highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_8 = g.newImage("Sprite8highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_8 = g.newImage("Sprite8highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        //SPRITE_RIGHT_9 = g.newImage("Sprite9highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        //SPRITE_LEFT_9 = g.newImage("Sprite9highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_10 = g.newImage("Sprite10highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_10 = g.newImage("Sprite10highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        //SPRITE_RIGHT_11 = g.newImage("Sprite11highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        //SPRITE_LEFT_11 = g.newImage("Sprite11highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_SLIDING1 = g.newImage("Sprite16highres_reverse-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_SLIDING1 = g.newImage("Sprite16highres-6perc.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_SLIDING2 = g.newImage("Sprite16highres_reverse-6perc2.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_SLIDING2 = g.newImage("Sprite16highres-6perc2.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_RIGHT_SLIDING3 = g.newImage("Sprite16highres_reverse-6perc3.png", Graphics.ImageFormat.ARGB8888);
        SPRITE_LEFT_SLIDING3 = g.newImage("Sprite16highres-6perc3.png", Graphics.ImageFormat.ARGB8888);

        sound_enabled_img = g.newImage("UI-SettingsEnabledWindowhighres.png", Graphics.ImageFormat.ARGB8888);
        sound_disabled_img = g.newImage("UI-SettingsDisabledWindowhighres.png", Graphics.ImageFormat.ARGB8888);
        
    }

    // Based on PlayerChar class getSpriteName method, return appropriate image to blit to screen
    private Image getSpriteImage() {
        Log.i("GameScreen", String.valueOf(this.player1.getSpriteName()));
        switch (this.player1.getSpriteName()) {
            case 0: {
                return SPRITE_LEFT_WALL_HANG;
            }
            case 1: {
                return SPRITE_RIGHT_WALL_HANG;
            }
            case 2: {
                return SPRITE_RIGHT_DYING_EARLY;
            }
            case 3: {
                return SPRITE_LEFT_DYING_EARLY;
            }
            case 4: {
                return SPRITE_RIGHT_DYING_LATE;
            }
            case 5: {
                return SPRITE_LEFT_DYING_LATE;
            }
            case 6: {
                return SPRITE_RIGHT_3;
            }
            case 7: {
                return SPRITE_LEFT_3;
            }
            case 8: {
                return SPRITE_RIGHT_4;
            }
            case 9: {
                return SPRITE_LEFT_4;
            }
            case 10: {
                return SPRITE_RIGHT_5;
            }
            case 11: {
                return SPRITE_LEFT_5;
            }
            case 12: {
                return SPRITE_RIGHT_6;
            }
            case 13: {
                return SPRITE_LEFT_6;
            }
            case 14: {
                return SPRITE_RIGHT_7;
            }
            case 15: {
                return SPRITE_LEFT_7;
            }
            case 16: {
                return SPRITE_RIGHT_8;
            }
            case 17: {
                return SPRITE_LEFT_8;
            }
/*            case 18: {
                return SPRITE_RIGHT_9 = 18;
            }
            case 19: {
                return SPRITE_LEFT_9 = 19;
            }*/
            case 20: {
                return SPRITE_RIGHT_10;
            }
            case 21: {
                return SPRITE_LEFT_10;
            }
/*            case 22: {
                return SPRITE_RIGHT_11 = 22;
            }
            case 23: {
                return SPRITE_LEFT_11 = 23;
            }*/
            case 24: {
                return SPRITE_RIGHT_SLIDING1;
            }
            case 25: {
                return SPRITE_LEFT_SLIDING1;
            }
            case 26: {
                return SPRITE_RIGHT_SLIDING2;
            }
            case 27: {
                return SPRITE_LEFT_SLIDING2;
            }
            case 28: {
                return SPRITE_RIGHT_SLIDING3;
            }
            case 29: {
                return SPRITE_LEFT_SLIDING3;
            }
        }

        Log.i("GameScreen", "getSpriteImage FAILED, RETURNING NULL");
        return null;
    }
}