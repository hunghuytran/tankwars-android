package tankwarz;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.Vector;


public class GamePanel extends View implements Runnable
{

    //Creates objects for later use
    private Tank tank;
    private aiTank aitank;
    private Vector<Canon> object = new Vector();
    private Vector<Canon> aiobject = new Vector();
    private boolean moveThatTank;


    public GamePanel(Context context)
    {
        super(context);
        tank = new Tank(300,600, this);
        aitank = new aiTank(1150,600, this);


//Start a thread
        Thread thread = new Thread(this);
        thread.start();
    }



    public Tank getTank()
    {
        return tank;
    }


    public aiTank getAitank()
    {
        return aitank;
    }


    //Create objects with canvas
    public void onDraw(Canvas canvas)
    {
//Shoot if the value whileflying in tank class is TRUE

        if(tank.isWhileFlying() == true)
        {
            for(int i = 0; i < object.size(); i++) {

            //Here the object is given value. That means for all object Objects.
            canvas.drawOval(object.get(i).getSize(), object.get(i).getColor());
            object.get(i).setXspeed((int) getTank().getCalcDistanceX());
            object.get(i).setYspeed(object.get(i).getYspeed() + 10);
            object.get(i).move();

            //Remove objects from the vector list and sets whileflying to FALSE
            if (1920 < object.get(i).getX() || 0 > object.get(i).getX()
                    || object.get(i).getY() > 1080) {
                object.remove(i);
                tank.setWhileFlying(false);
            }
        }
        }

        //Object created from the robot tank
        for(int i = 0; i < aiobject.size(); i++) {
            //Here the object is given value. That means for all object Objects.
            canvas.drawOval(aiobject.get(i).getSize(), aiobject.get(i).getColor());
            aiobject.get(i).setYspeed(aiobject.get(i).getYspeed() + 10);
            aiobject.get(i).move();


            if (1920 < aiobject.get(i).getX() || 0 > aiobject.get(i).getX()
                    || aiobject.get(i).getY() > 1080)
            {
                aiobject.remove(i);
                aitank.setOnrun(true);

            }
        }

        //draw the pictures that are given in resources
        canvas.drawBitmap(tank.getTankImg(), null, tank.getSize(), tank.getColor());
        tank.move();
        canvas.drawBitmap(aitank.getTankImg(), null, aitank.getSize(), aitank.getColor());
        aitank.move();
    }


//Gameloop
    public void run()
    {

        while(true)
        {

            postInvalidate();

            try
            {
                Thread.sleep(100);
            }

            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }


    public Vector<Canon> getObject()
    {
        return object;
    }


    public Vector<Canon> getAiobject() {
        return aiobject;
    }


    public boolean isMoveThatTank() {
        return moveThatTank;
    }

    public void setMoveThatTank(boolean moveThatTank) {
        this.moveThatTank = moveThatTank;
    }
}
