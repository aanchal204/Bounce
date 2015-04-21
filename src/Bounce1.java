import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.Sphere;
import java.nio.FloatBuffer;
import java.util.Random;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Bounce1 {

    private final static float spSize = .25f;
    FloatBuffer red = BufferUtils.createFloatBuffer(4).put(new float[]{1,0,0,1});
    FloatBuffer blue = BufferUtils.createFloatBuffer(4).put(new float[]{0,1,0,1});
    FloatBuffer green = BufferUtils.createFloatBuffer(4).put(new float[]{0,0,1,1});
    FloatBuffer yellow = BufferUtils.createFloatBuffer(4).put(new float[]{1,1,0,1});

    private FloatBuffer lcolor1;
    int angle = 1,count = 0;
    int depth =0;
    int[] idx = new int[3];
    float xdisp = 0,ydisp = -1;
    Random rand = new Random();
    int boxCount = 1;
    float disp[] = {2,0,-2};
    boolean gameEnd = false;
    int gameScore=0;
    int FPS = 60;
    int scoreInc = 100;
    int coindx[] = new int[3];
    float coin[] = {2,0,-2};
    int coin_y;


    private void movingBox(float x,float y)
    {

        glPushMatrix();
        glTranslatef(x,y,depth);
        glBegin(GL_QUADS);

        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Bottom Left Of The Quad (Top)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Bottom Right Of The Quad (Top)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Top)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Top)

        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Top Right Of The Quad (Bottom)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Top Left Of The Quad (Bottom)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Bottom)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Bottom)


        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Back)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Back)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Bottom Right Of The Quad (Back)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Bottom Left Of The Quad (Back)

        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Front)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Front)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Front)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Front)

        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Right)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Right)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Right)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Bottom Right Of The Quad (Right)

        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Left)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Left)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Bottom Left Of The Quad (Left)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Left)

        glEnd();
        glPopMatrix();

    }

    private void movingCoin(float x,float y)
    {

        glPushMatrix();
        glTranslatef(x,y,depth);
        glBegin(GL_QUADS);

        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Bottom Left Of The Quad (Top)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Bottom Right Of The Quad (Top)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Top)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Top)

        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Top Right Of The Quad (Bottom)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Top Left Of The Quad (Bottom)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Bottom)
        glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Bottom)


        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Back)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Back)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Bottom Right Of The Quad (Back)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Bottom Left Of The Quad (Back)

        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Front)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Front)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Front)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Front)

        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f, 1.0f, 1.0f);          // Top Right Of The Quad (Right)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f(-1.0f, 1.0f,-1.0f);          // Top Left Of The Quad (Right)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f(-1.0f,-1.0f,-1.0f);          // Bottom Left Of The Quad (Right)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f(-1.0f,-1.0f, 1.0f);          // Bottom Right Of The Quad (Right)

        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f, 1.0f,-1.0f);          // Top Right Of The Quad (Left)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f( 1.0f, 1.0f, 1.0f);          // Top Left Of The Quad (Left)
        glColor3f(0.0f,0.0f,0.0f);          // Set The Color To Black
        glVertex3f( 1.0f,-1.0f, 1.0f);          // Bottom Left Of The Quad (Left)
        glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
        glVertex3f( 1.0f,-1.0f,-1.0f);          // Bottom Right Of The Quad (Left)

        glEnd();
        glPopMatrix();

    }

    private void drawBall()
    {
        FloatBuffer p0 = BufferUtils.createFloatBuffer(4).put(new float[] {0f,0,2f,1});
        p0.flip();
        FloatBuffer p1 = BufferUtils.createFloatBuffer(4).put(new float[] {0f,0f,-2f,1});
        p1.flip();
        FloatBuffer p2 = BufferUtils.createFloatBuffer(4).put(new float[] {0f,2f,0,1});
        p2.flip();
        FloatBuffer p3 = BufferUtils.createFloatBuffer(4).put(new float[] {0f,-2f,0,1});
        p3.flip();
        FloatBuffer p4 = BufferUtils.createFloatBuffer(4).put(new float[] {2f,0f,0,1});
        p4.flip();
        FloatBuffer p5 = BufferUtils.createFloatBuffer(4).put(new float[] {-2f,0f,0,1});
        p5.flip();

        glEnable(GL_LIGHTING);
        glPushMatrix();
        glTranslatef(xdisp,ydisp,0);
        glRotatef(angle+=5,1,0,0);

        glLight(GL_LIGHT0,GL_POSITION,p0);
        glLight(GL_LIGHT1,GL_POSITION,p1);
        glLight(GL_LIGHT2,GL_POSITION,p2);
        glLight(GL_LIGHT3,GL_POSITION,p3);

        glLight(GL_LIGHT0,GL_DIFFUSE,red);
        glLight(GL_LIGHT1,GL_DIFFUSE,blue);
        glLight(GL_LIGHT2,GL_DIFFUSE,green);
        glLight(GL_LIGHT3,GL_DIFFUSE,yellow);


        new Sphere().draw(1f,20,20);
        angle %= 360;

        glPopMatrix();
        glDisable(GL_LIGHTING);
    }

    private void drawTrackBorder()
    {
        glColor3f(.0f, 1, .2f);
        glBegin(GL_QUADS);
        glVertex3f(3.1f,-1f, -5);
        glVertex3f(3.5f,-1f,-5);
        glVertex3f(3.5f,-1f,100);
        glVertex3f(3.1f,-1f,100);
        glEnd();

        glColor3f(.0f,1,.2f);
        glBegin(GL_QUADS);
        glVertex3f(-3.1f,-1f, -5);
        glVertex3f(-3.5f,-1f,-5);
        glVertex3f(-3.5f,-1f,100);
        glVertex3f(-3.1f,-1f,100);
        glEnd();
    }

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,800));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here
        glMatrixMode(GL_PROJECTION);
        gluPerspective(30f,1,0.1f,100);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(0,5,-15,0,0,10,0,4,0);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHT0);
        glEnable(GL_LIGHT1);
        glEnable(GL_LIGHT2);
        glEnable(GL_LIGHT3);

        float [] color0 = {1.0f, 0.0f,0.0f,0.5f};

        FloatBuffer lcolor = BufferUtils.createFloatBuffer(4).put(color0);
        lcolor.flip();
        lcolor1 = BufferUtils.createFloatBuffer(4).put(0).put(0).put(1).put(1);
        lcolor1.flip();
        float [] pos = {1.0f, 0.0f,-0.5f,1.0f};
        FloatBuffer lpos = BufferUtils.createFloatBuffer(4).put(pos);
        lpos.flip();
        FloatBuffer lpos1 = BufferUtils.createFloatBuffer(4).put(0).put(2).put(5).put(1);
        lpos1.flip();

        red.flip(); blue.flip(); green.flip(); yellow.flip();

        while (!Display.isCloseRequested() && !gameEnd) {

            // render OpenGL here
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            pollInput();
            drawBall();
            drawTrackBorder();
            if(depth==0) {
                boxCount = rand.nextInt(2) + 1;
                idx[0] = rand.nextInt(3);
                if (boxCount > 1) {
                    idx[1] = rand.nextInt(3);
                    while (idx[0] == idx[1])
                        idx[1] = rand.nextInt(3);
                }
                if(gameScore!=0 && gameScore % 200 ==0) {
                    FPS += 15;
                    //scoreInc+=100;
                }
                if(FPS>=350)
                    FPS = 60;
                if(boxCount==1){
                    coindx[0] = rand.nextInt(3);
                    while (coindx[0] == idx[0])
                        coindx[0] = rand.nextInt(3);
                    coin_y = (rand.nextInt(2) == 1) ? 1 : -1;
                }
                depth = 80;
            }

            depth--;
            movingBox(disp[idx[0]],-1);
            if(boxCount==1)
                movingCoin(coin[coindx[0]],coin_y);

            if (boxCount > 1) {
                movingBox(disp[idx[1]],-1);
                movingBox(disp[idx[1]],1);
            }
            if(boxCount==1 && xdisp == coin[coindx[0]] && ydisp == coin_y && depth==5)
            {
                coin_y = -100;
                gameScore+=100;
            }
            if(xdisp == disp[idx[0]]&& ydisp == -1 && depth ==5) {
                gameEnd = true;
            }
            if((xdisp != disp[idx[0]]|| ydisp != -1) &&depth==0)
                gameScore +=scoreInc;
            if(boxCount > 1) {
                if (xdisp == disp[idx[1]] && (ydisp == -1||ydisp == 1) && depth == 5) {
                    gameEnd = true;
                }
            }
            Display.update();
            Display.sync(FPS);
        }
        Sys.alert("Game ended!","Your score:"+gameScore);
        Display.destroy();
    }
    public void pollInput() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
                    ydisp = 1;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ydisp = -1;
                        }
                    }).start();
                }

                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                    if(xdisp > -2.0)    xdisp -= 2;
                }

                if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                    if(xdisp < 2.0)    xdisp += 2;
                }

                if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
                    gameEnd = true;
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Bounce1 b = new Bounce1();
        b.start();
    }
}