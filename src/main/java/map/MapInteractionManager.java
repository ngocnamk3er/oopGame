package map;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Button;
import entities.Coin;
import entities.Door;
import entities.Entity;
import entities.Fire;
import entities.Heart;
import entities.Platform;
import entities.Player;
import entities.Stone;
import entities.StrangeDoor;
import entities.Joystick;
import entities.WoodyBox;
import entities.enemy.Enemy;
import entities.enemy.Enemy1;
import entities.enemy.Enemy2;
import entities.enemy.Enemy3;
import entities.enemy.Enemy4;
import entities.trap.CeilingTrap;
import entities.trap.LightningTrap;
import entities.trap.SandwormTrap;
import entities.trap.ShurikenTrap;
import entities.trap.SmallSpike;
import entities.trap.SpearTrap;
import entities.trap.Trap;
import static help.Constant.*;
import inputs.SetKeyBoardInputs;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import main.GameScene;

public class MapInteractionManager {
    private GameScene gameScene;
    private ArrayList<Coin> coins;
    private ArrayList<Stone> stones;
    private ArrayList<Button> buttons;
    private ArrayList<Enemy> enemies;
    private ArrayList<Fire> fires;
    private ArrayList<Trap> traps;
    private ArrayList<Platform> platforms;
    private ArrayList<Joystick> joysticks;
    private ArrayList<WoodyBox> woodyBoxs;
    private ArrayList<Heart> hearts;
    private Player player;
    private Door door;
    private StrangeDoor strangeDoor;
    private GraphicsContext gc;
    private int[][] mapData;
    private Image[][] animationImagesPlayer;
    private Image[][] animationImagesCoin;
    private Image animationImageStone;
    private Image[][] animationImagesDoor;
    private Image[] animationImagesButton;
    private BufferedImage bufferedImage;
    private Image animationImageStrangeDoor;
    private int levelValue;
    private Image[][] animationImagesEnimy1;
    private Image[][] animationImagesEnimy2;
    private Image[][] animationImagesEnimy3;
    private Image[][] animationImagesEnimy4;
    private Image[][] animationImagesFire;
    private Image[] animationImagesLightningTrap;
    private Image[] animationImagesShurikenTrap;
    private Image[] animationImagesSandWormTrap;
    private Image[] animationImagesCeilingTrap;
    private Image[] animationImagesSpearTrap;
    private Image animationImageSmallSpike;
    private Image[][] animationImagesPlatform;
    private Image[] animationImagesWoodyBox;
    private Image[] animationImagesJoystick;
    private Image[] animationImagesHeart;

    private void loadAnimations() {
        loadAnimationsPlayer();
        loadAnimationsCoins();
        loadAnimationsStone();
        loadAnimationsDoor();
        loadAnimationsButton();
        loadAnimationsStrangDoor();
        loadAnimationsPlatform();
        loadAnimationImagesWoodyBox();
        loadAnimationImagesJoystick();
        // --------------
        loadAnimationsEnimy1();
        loadAnimationsEnimy2();
        loadAnimationsEnimy3();
        loadAnimationsEnimy4();
        // ---------------
        loadAnimationsFire();
        loadAnimationsLightningTrap();
        loadAnimationsShurikenTrap();
        loadAnimationsSandWormTrap();
        loadAnimationsCeilingTrap();
        loadAnimationsSpearTrap();
        loadAnimationsSmallSpike();
        loadAnimationsHeart();
    }

    public MapInteractionManager(GraphicsContext gc, int[][] mapData, GameScene gameScene) {
        loadAnimations();
        this.gameScene = gameScene;
        this.gc = gc;
        this.mapData = mapData;
    }

    public void setInitialState(int levelValue) {
        this.levelValue = levelValue;
        loadDataMapInteraction(levelValue);
        new SetKeyBoardInputs(this);
    }

    private void loadDataMapInteraction(int levelValue) {
        coins = new ArrayList<>();
        stones = new ArrayList<>();
        enemies = new ArrayList<>();
        platforms = new ArrayList<>();
        buttons = new ArrayList<>();
        fires = new ArrayList<>();
        traps = new ArrayList<>();
        joysticks = new ArrayList<>();
        woodyBoxs = new ArrayList<>();
        hearts = new ArrayList<>();
        player = new Player();
        door = new Door();
        strangeDoor = new StrangeDoor();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 21; j++) {
                if (MAP_INTERAC_DATA[levelValue][i][j] == 'c') {
                    Coin coin = new Coin(j * 64, i * 64, this, animationImagesCoin);
                    coins.add(coin);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'p') {
                    player.setProperties(j * 64, i * 64, animationImagesPlayer, this);

                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 's') {
                    Stone stone = new Stone(j * 64, i * 64, animationImageStone, this);
                    stones.add(stone);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'b') {
                    Button button = new Button(j * 64, i * 64, animationImagesButton, this);
                    buttons.add(button);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'd') {
                    door.setProperties(j * 64, i * 64, animationImagesDoor, this);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'D') {
                    strangeDoor.setProperties(j * 64, i * 64, gc, animationImageStrangeDoor);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == '1') {
                    Enemy1 enimy1 = new Enemy1(j * 64, i * 64, animationImagesEnimy1, this);
                    enemies.add(enimy1);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == '2') {
                    Enemy2 enimy2 = new Enemy2(j * 64, i * 64, animationImagesEnimy2, this);
                    enemies.add(enimy2);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == '3') {
                    Enemy3 enimy3 = new Enemy3(j * 64, i * 64, animationImagesEnimy3, this);
                    enemies.add(enimy3);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == '4') {
                    Enemy4 enimy4 = new Enemy4(j * 64, i * 64, animationImagesEnimy4, this);
                    enemies.add(enimy4);
                    enimy4 = new Enemy4(j * 64, i * 64 - 32, animationImagesEnimy4, this);
                    enemies.add(enimy4);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'f') {
                    Fire fire = new Fire(j * 64, i * 64, animationImagesFire, this);
                    fires.add(fire);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'l') {
                    LightningTrap lightningTrap = new LightningTrap(j * 64, i * 64, animationImagesLightningTrap, this);
                    traps.add(lightningTrap);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'S') {
                    ShurikenTrap shurikenTrap = new ShurikenTrap(j * 64, i * 64, animationImagesShurikenTrap, this);
                    traps.add(shurikenTrap);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'w') {
                    SandwormTrap sandwormTrap = new SandwormTrap(j * 64, i * 64, animationImagesSandWormTrap, this);
                    traps.add(sandwormTrap);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'C') {
                    CeilingTrap ceilingTrap = new CeilingTrap(j * 64, i * 64, animationImagesCeilingTrap, this);
                    traps.add(ceilingTrap);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'P') {
                    SpearTrap spearTrap = new SpearTrap(j * 64, i * 64, animationImagesSpearTrap, this);
                    traps.add(spearTrap);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'm') {
                    SmallSpike smallSpike = new SmallSpike(j * 64, i * 64, animationImageSmallSpike, this);
                    traps.add(smallSpike);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'F') {
                    Platform platform = new Platform(j * 64, i * 64, animationImagesPlatform, this);
                    platforms.add(platform);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'B') {
                    WoodyBox woodyBox = new WoodyBox(j * 64, i * 64, animationImagesWoodyBox, this);
                    woodyBox.setBigSize(true);
                    woodyBoxs.add(woodyBox);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'N') {
                    WoodyBox woodyBox = new WoodyBox(j * 64, i * 64, animationImagesWoodyBox, this);
                    woodyBox.setBigSize(false);
                    woodyBoxs.add(woodyBox);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'W') {
                    Joystick Switch = new Joystick(j * 64, i * 64, animationImagesJoystick, this);
                    joysticks.add(Switch);
                } else if (MAP_INTERAC_DATA[levelValue][i][j] == 'h') {
                    Heart heart = new Heart(j * 64, i * 64, animationImagesHeart, this);
                    hearts.add(heart);
                }
            }
        }
    }

    public void update() {
        player.update();
        for (Stone stone : stones) {
            stone.update();
        }

        for (Coin coin : coins) {
            coin.update();
        }

        for (Button button : buttons) {
            button.update();
        }

        for (Enemy enemy : enemies) {
            enemy.update();
        }

        for (Fire fire : fires) {
            fire.update();
        }

        for (Trap trap : traps) {
            trap.update();
        }

        for (Joystick joystick : joysticks) {
            joystick.update();
        }

        for (WoodyBox woodyBox : woodyBoxs) {
            woodyBox.update();
        }

        for (Platform platform : platforms) {
            platform.update();
        }
        for (Heart heart : hearts) {
            heart.update();
        }

        door.update();

    }

    public void render() {
        try {
            gc.clearRect(0, 0, 21 * 64, 12 * 64);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        strangeDoor.render();

        for (Stone stone : stones) {
            stone.render();
        }

        for (Coin coin : coins) {
            coin.render();
        }

        for (Button button : buttons) {
            button.render();
        }

        for (Enemy enemy : enemies) {
            enemy.render();
        }

        for (Fire fire : fires) {
            fire.render();
        }

        for (Trap trap : traps) {
            trap.render();
        }

        for (Joystick joystick : joysticks) {
            joystick.render();
        }

        for (WoodyBox woodyBox : woodyBoxs) {
            woodyBox.render();
        }

        for (Platform platform : platforms) {
            platform.render();
        }
        for (Heart heart : hearts) {
            heart.render();
        }
        door.render();
        player.render();
    }

    private void loadAnimationsHeart() {
        animationImagesHeart = new Image[3];
        try {
            bufferedImage = ImageIO.read(Heart.class.getResourceAsStream("heart.png"));
            for (int j = 0; j < 3; j++) {
                animationImagesHeart[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimationImagesJoystick() {
        animationImagesJoystick = new Image[2];
        try {
            animationImagesJoystick[0] = new Image(Joystick.class.getResourceAsStream("switchLeft.png"));
            animationImagesJoystick[1] = new Image(Joystick.class.getResourceAsStream("switchRight.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAnimationImagesWoodyBox() {
        animationImagesWoodyBox = new Image[2];
        try {
            bufferedImage = ImageIO.read(WoodyBox.class.getResourceAsStream("WoodyBox.png"));
            for (int j = 0; j < 2; j++) {
                animationImagesWoodyBox[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 64, 0, 64, 64), null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimationsPlatform() {
        animationImagesPlatform = new Image[2][4];
        for (int i = 0; i < 2; i++) {
            if (i == Platform.RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Platform.class.getResourceAsStream("PlatformR.png"));
                    int AmountSprites = Platform.getAmountSpritesOfPlatformAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlatform[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 32, 0, 32, 10), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (i == Platform.RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Platform.class.getResourceAsStream("PlatformL.png"));
                    int AmountSprites = Platform.getAmountSpritesOfPlatformAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlatform[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 32, 0, 32, 10), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void loadAnimationsSmallSpike() {
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("smallSpike.png"));
            animationImageSmallSpike = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsSpearTrap() {
        int AmountSprites = 13;
        animationImagesSpearTrap = new Image[AmountSprites];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("SpearTrap.png"));
            for (int j = 0; j < AmountSprites; j++) {
                animationImagesSpearTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(4 + j * 16, 0, 9, 64),
                        null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsCeilingTrap() {
        int AmountSprites = 15;
        animationImagesCeilingTrap = new Image[AmountSprites];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("CeilingTrap.png"));
            for (int j = 0; j < AmountSprites; j++) {
                animationImagesCeilingTrap[j] = SwingFXUtils
                        .toFXImage(bufferedImage.getSubimage(16 + j * 64, 0, 32, 64), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsSandWormTrap() {
        animationImagesSandWormTrap = new Image[11];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("SandWorm.png"));
            int AmountSprites = 11;
            for (int j = 0; j < AmountSprites; j++) {
                animationImagesSandWormTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 32, 0, 32, 32),
                        null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsShurikenTrap() {
        animationImagesShurikenTrap = new Image[8];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("Suriken.png"));
            int AmountSprites = 8;
            for (int j = 0; j < AmountSprites; j++) {
                animationImagesShurikenTrap[j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 32, 0, 32, 32),
                        null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsLightningTrap() {
        animationImagesLightningTrap = new Image[22];
        try {
            bufferedImage = ImageIO.read(Trap.class.getResourceAsStream("LightningTrap.png"));
            int AmountSprites = 22;
            for (int j = 0; j < AmountSprites; j++) {
                animationImagesLightningTrap[j] = SwingFXUtils
                        .toFXImage(bufferedImage.getSubimage(24 + j * 96, 0, 48, 96), null);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsFire() {
        animationImagesFire = new Image[2][4];
        for (int i = 0; i < 2; i++) {
            if (i == Fire.ON) {
                try {
                    bufferedImage = ImageIO.read(Fire.class.getResourceAsStream("fireOn.png"));
                    int AmountSprites = Fire.getAmountSpritesOfFireAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesFire[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 32),
                                null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (i == Fire.OFF) {
                try {
                    bufferedImage = ImageIO.read(Fire.class.getResourceAsStream("fireOff.png"));
                    int AmountSprites = Fire.getAmountSpritesOfFireAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesFire[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 32),
                                null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void loadAnimationsEnimy4() {
        animationImagesEnimy4 = new Image[2][6];
        for (int i = 0; i < 2; i++) {
            if (i == Enemy4.IDLE) {
                try {
                    bufferedImage = ImageIO.read(Enemy4.class.getResourceAsStream("mushroomIdle.png"));
                    int AmountSprites = Enemy4.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy4[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (i == Enemy4.DEATH) {
                try {
                    bufferedImage = ImageIO.read(Enemy4.class.getResourceAsStream("mushroomDeathR.png"));
                    int AmountSprites = Enemy4.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy4[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void loadAnimationsEnimy3() {
        animationImagesEnimy3 = new Image[6][8];
        for (int i = 0; i < 6; i++) {
            if (i == Enemy3.RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomRunL.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy3[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy3.DEATH_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomDeathL.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy3[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy3.HIT_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomHitL.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy3[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy3.RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomRunR.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy3[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy3.DEATH_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomDeathR.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy3[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy3.HIT_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("mushroomHitR.png"));
                    int AmountSprites = Enemy3.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy3[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            }
        }

    }

    private void loadAnimationsEnimy2() {
        animationImagesEnimy2 = new Image[3][6];
        for (int i = 0; i < 3; i++) {
            if (i == Enemy2.RUN) {
                try {
                    bufferedImage = ImageIO.read(Enemy2.class.getResourceAsStream("wormRun.png"));
                    int AmountSprites = Enemy2.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy2[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 8), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy2.HIT) {
                try {
                    bufferedImage = ImageIO.read(Enemy2.class.getResourceAsStream("wormHit.png"));
                    int AmountSprites = Enemy2.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy2[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 8), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy2.DEATH) {
                try {
                    bufferedImage = ImageIO.read(Enemy2.class.getResourceAsStream("wormDeath.png"));
                    int AmountSprites = Enemy2.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy2[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 8), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            }
        }
    }

    private void loadAnimationsEnimy1() {
        animationImagesEnimy1 = new Image[8][15];
        for (int i = 0; i < 8; i++) {
            if (i == Enemy1.IDLE_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeIdleR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeRunR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 24), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.DEATH_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeDeathR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.HIT_R) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeHitR.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.IDLE_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeIdleL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeRunL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 24), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.DEATH_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeDeathL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Enemy1.HIT_L) {
                try {
                    bufferedImage = ImageIO.read(Enemy1.class.getResourceAsStream("slimeHitL.png"));
                    int AmountSprites = Enemy1.getAmountSpritesOfEnimyAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesEnimy1[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            }
        }
    }

    private void loadAnimationsStrangDoor() {
        try {
            animationImageStrangeDoor = new Image(Player.class.getResourceAsStream("StrangeDoor.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsStone() {
        try {
            animationImageStone = new Image(Stone.class.getResourceAsStream("stone.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationsCoins() {
        animationImagesCoin = new Image[2][6];
        try {
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin.png"));
            for (int i = 0; i < 6; i++) {
                try {
                    animationImagesCoin[0][i] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(i * 8, 0, 8, 8), null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            bufferedImage = ImageIO.read(Coin.class.getResourceAsStream("coin_pickup.png"));
            for (int i = 0; i < 6; i++) {
                try {
                    animationImagesCoin[1][i] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(i * 8, 0, 8, 16),
                            null);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void loadAnimationsDoor() {
        animationImagesDoor = new Image[2][15];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < Door.getAmountSpritesOfDoor(i); j++) {
                if (i == Door.CLOSED) {
                    try {
                        bufferedImage = ImageIO.read(Door.class.getResourceAsStream("door_closed.png"));
                        animationImagesDoor[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 48),
                                null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        bufferedImage = ImageIO.read(Door.class.getResourceAsStream("door_openning.png"));
                        animationImagesDoor[i][j] = SwingFXUtils.toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 48),
                                null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void loadAnimationsButton() {
        animationImagesButton = new Image[2];
        for (int i = 0; i < 2; i++) {
            try {
                animationImagesButton[i] = new Image(Button.class.getResourceAsStream("button" + i + ".png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected void loadAnimationsPlayer() {
        animationImagesPlayer = new Image[16][8];
        for (int i = 0; i < 16; i++) {
            if (i == Player.RUN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.RUN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroRunR.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.IDLE_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.IDLE_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroIdleR.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.ATTACK1_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1L.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.ATTACK1_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack1R.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.PUSH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.PUSH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroPushingR.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.JUMPUP_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.JUMPUP_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpUpR.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.JUMPDOWN_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.JUMPDOWN_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroJumpDownR.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.ATTACK2_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2L.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.ATTACK2_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroAttack2R.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 32, 0, 32, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.DEATH_L) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathL.png"));
                    int AmountSprites = Player.getAmountSpritesOfPlayerAction(i);
                    for (int j = 0; j < AmountSprites; j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage((AmountSprites - 1 - j) * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            } else if (i == Player.DEATH_R) {
                try {
                    bufferedImage = ImageIO.read(Player.class.getResourceAsStream("HeroDeathR.png"));
                    for (int j = 0; j < Player.getAmountSpritesOfPlayerAction(i); j++) {
                        animationImagesPlayer[i][j] = SwingFXUtils
                                .toFXImage(bufferedImage.getSubimage(j * 16, 0, 16, 16), null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "[" + i + "]");
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    public int[][] getMapData() {
        return mapData;
    }

    public void setMapData(int[][] mapData) {
        this.mapData = mapData;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene GameScene) {
        this.gameScene = GameScene;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(int levelValue) {
        this.levelValue = levelValue;
    }

    public StrangeDoor getStrangeDoor() {
        return strangeDoor;
    }

    public void setStrangeDoor(StrangeDoor strangeDoor) {
        this.strangeDoor = strangeDoor;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Fire> getFires() {
        return fires;
    }

    public void setFires(ArrayList<Fire> fires) {
        this.fires = fires;
    }

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public void setTraps(ArrayList<Trap> traps) {
        this.traps = traps;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }

    public ArrayList<WoodyBox> getWoodyBoxs() {
        return woodyBoxs;
    }

    public void setWoodyBoxs(ArrayList<WoodyBox> woodyBoxs) {
        this.woodyBoxs = woodyBoxs;
    }

    public ArrayList<Joystick> getJoysticks() {
        return joysticks;
    }

    public void setJoysticks(ArrayList<Joystick> joysticks) {
        this.joysticks = joysticks;
    }

    public ArrayList<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(ArrayList<Heart> hearts) {
        this.hearts = hearts;
    }

}
