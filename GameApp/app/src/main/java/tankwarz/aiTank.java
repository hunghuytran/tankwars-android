package tankwarz;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import com.example.tranhung.gameapp.R;

import java.util.concurrent.ThreadLocalRandom;

public class aiTank extends Object {

    private Bitmap tankImg;
    private int randomDirection;
    private boolean onRun = true;
    private int randomTravel;






    public aiTank(int x, int y, GamePanel gp) {

        super(x, y, gp);
        setHeight(100);
        setWidth(getHeight() + 50);
        tankImg = BitmapFactory.decodeResource(gp.getResources(), R.drawable.aitank);
    }


    public Bitmap getTankImg() {
        return tankImg;
    }


    public void move() {

        setSize(new RectF(getX(), getY(), getX() + getWidth(), getY() + getHeight()));

// If human projectile travels within an area of the artificial tank then move it at random direction.

         if(getGamePanel().getTank().isWhileFlying() == true
                 && getGamePanel().getObject().get(0).getX() > getX()-300
                 && getGamePanel().getObject().get(0).getX() < getX()+300
                && getGamePanel().getObject().get(0).getY() < getY()+300
                 && getGamePanel().getObject().get(0).getY() > getY()-300)
        {
            setX(getX() + getXspeed());
        }

//Sets the x speed to 0 if artificial tank goes out range, x = 1000 or x = 1600.

        if(getX() < 1000 || getX() > 1600)
        {
            setXspeed(0);
        }

//Makes a loop as long as the boolean value is true. Projectile shoots automatic.

        if (onRun == true) {

            //Sets onRun to false so that artificial tank can't access the statement before the projectile is gone.
            onRun = false;
            randomDirection = ThreadLocalRandom.current().nextInt(-100, -10);
            getGamePanel().getAiobject().add(new Canon(getX() - 10, getY() + 25, getGamePanel()));
            getGamePanel().getAiobject().get(0).setXspeed(randomDirection);
            getGamePanel().getAiobject().get(0).setYspeed(randomDirection);


            //If a human projectile is launched then the artificial tank will move to a method outside of a loop
            if (getGamePanel().isMoveThatTank() == true) {
                moveTank();
                getGamePanel().setMoveThatTank(false);
            }

        }
    }


    //The method to decide which direction the artificial tank will move.
    public void moveTank()
    {
        //Random number from -10 to 10.
        randomTravel = ThreadLocalRandom.current().nextInt(-10, 10);

        //If randomTravel is bigger than 0 then move to right, else if less than 0 move to the left.
        if(randomTravel > 0)
        {
            randomTravel = 6;
        }

        if(randomTravel < 0)
        {
            randomTravel = -6;
        }
        setXspeed(randomTravel);
    }



    public void setOnrun(boolean onRun)
    {
        this.onRun = onRun;
    }

}



