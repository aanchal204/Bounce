import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.util.glu.Sphere;

import java.nio.FloatBuffer;

public class Bounce {
    static int width = 800;
    static int height = 800;
    static float aspect = 1.0f*width/height;
    float y=-1f;
    float x = 0;
    //width and height made equal for a proper sphere to be displayed


    private final static float spSize = .25f;
    private FloatBuffer lcolor1;
    int angle = 1;

    private void drawBall()
    {
        /*glPushMatrix();

        //glTranslatef() redraws rhe object at a new position since here we supply constants as parameters
        //it is redrawn at the same position
        //thus to give a translatory motion effect we need to change the parameters everytime
        //glTranslatef(0,y+=0.002,5+5*y);
        glTranslatef(0,-1,5);
        glRotatef(angle++,1,0,0);
        new Sphere().draw(0.25f,30, 30);
        angle %= 180;

        glPopMatrix();*/



        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            glPushMatrix();

            glTranslatef(0, y += 0.008, 5);
            new Sphere().draw(0.25f, 30, 30);

            glPopMatrix();
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            glPushMatrix();

            glTranslatef(x-=0.008,-1,5);
            new Sphere().draw(0.25f,30, 30);

            glPopMatrix();
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            glPushMatrix();

            glTranslatef(x+=0.008,-1,5);
            new Sphere().draw(0.25f,30, 30);

            glPopMatrix();
        }
        else{
            y=-1f;
            x=0f;
            glPushMatrix();

            glTranslatef(0,-1,5);
            glRotatef(angle++,1,0,0);
            new Sphere().draw(0.25f,30, 30);
            angle %= 180;

            glPopMatrix();

        }
    }

    private void drawTrackBorder()
    {

        glColor3f(.0f,1,.2f);
        glBegin(GL_QUADS);
        glVertex3f(.76f,-.25f, -5);
        glVertex3f(.8f,-.25f,-5);
        glVertex3f(.8f,-.25f,100);
        glVertex3f(.76f,-.25f,100);
        glEnd();

        glColor3f(.0f,1,.2f);
        glBegin(GL_QUADS);
        glVertex3f(-.76f,-.25f, -5);
        glVertex3f(-.8f,-.25f,-5);
        glVertex3f(-.8f,-.25f,100);
        glVertex3f(-.76f,-.25f,100);
        glEnd();


    }
    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(width,height));
            Display.setTitle("Bounce");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here
        //since we are working with 3D objects Orthogonal Projection wont be needed for us
        glMatrixMode(GL_PROJECTION);
        gluPerspective(30f,aspect,0.1f,100); //sets up perspective projection matrix

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(0,0,-1,0,0,0,0,1,0);

        // glViewport(0,0,Display.getWidth(),Display.getHeight());

        glEnable(GL_DEPTH_TEST);
        //glEnable(GL_DEPTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_LIGHT1);

        //glEnable(GL_3D);
        float [] color0 = {1.0f, 0.0f,0.0f,0.5f};

        FloatBuffer lcolor = BufferUtils.createFloatBuffer(4).put(color0);
        lcolor.flip();
        lcolor1 = BufferUtils.createFloatBuffer(4).put(0).put(0).put(1).put(1);
        lcolor1.flip();
        float [] pos = {1.0f, 1.0f,-0.5f,1.0f};
        FloatBuffer lpos = BufferUtils.createFloatBuffer(4).put(pos);
        lpos.flip();
        glLight(GL_LIGHT0,GL_POSITION,lpos);
        glLight(GL_LIGHT0,GL_DIFFUSE,lcolor);
        glLight(GL_LIGHT1,GL_AMBIENT,lcolor1);

        //


        while (!Display.isCloseRequested()) {

            // render OpenGL here
            //glLightModel(GL_LIGHT0,pos);

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            drawBall();
            drawTrackBorder();


            Display.update();
        }

        Display.destroy();
    }
    public static void main(String[] args)
    {
        Bounce b = new Bounce();
        b.start();
    }
}
