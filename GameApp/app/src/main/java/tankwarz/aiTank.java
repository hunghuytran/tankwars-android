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
    private boolean onRunLimit;
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
// Autoloop and moves if while flying == true but also if projectile of HUMAN TANK is near object AITANK
         if(getGamePanel().getTank().isWhileFlying() == true
                 && getGamePanel().getObject().get(0).getX() > getX()-300
                 && getGamePanel().getObject().get(0).getX() < getX()+300
                && getGamePanel().getObject().get(0).getY() < getY()+300
                 && getGamePanel().getObject().get(0).getY() > getY()-300)
        {
            setX(getX() + getXspeed());
        }
        // Sets xspeed to 0 if it's within a certain range
        if(getX() < 1000 || getX() > 1600)
        {
            setXspeed(0);
        }

//Shoots auto! + move

        if (onRun == true) {
            //sets onRun to false so that only one projectile can be shot at once.
            onRun = false;
            randomDirection = ThreadLocalRandom.current().nextInt(-100, -10);
            getGamePanel().getAiobject().add(new Canon(getX() - 10, getY() + 25, getGamePanel()));
            getGamePanel().getAiobject().get(0).setXspeed(randomDirection);
            getGamePanel().getAiobject().get(0).setYspeed(randomDirection);


            //Goes to a method that doesn't loop if MoveThatTank = true( gets activated when human release his projectile)
            if (getGamePanel().isMoveThatTank() == true) {
                moveTank();
                getGamePanel().setMoveThatTank(false);
            }

        }
    }

    //Returns an xspeed to the robot tank.
    public void moveTank()
    {
        randomTravel = ThreadLocalRandom.current().nextInt(-10, 10);


        //Sets the speed of moving
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



