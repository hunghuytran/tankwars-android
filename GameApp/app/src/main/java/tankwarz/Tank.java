package tankwarz;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.tranhung.gameapp.R;

public class Tank extends Object {

    private Bitmap tankImg;
    private Handler handler = new Handler();
    private int tankMoveDirection = 0;
    private float calcDistanceX;
    private float calcDistanceY;
    private float startTouchX;
    private float releaseTouchX;
    private float startTouchY;
    private float releaseTouchY;
    private boolean whileFlying;
    private boolean onTouch;



    //Handles the loop of the touch down.
    Runnable runnable = new Runnable() {

        public void run()
        {

                setX(getX() + tankMoveDirection);
                handler.postDelayed(this, 100);

                if(getX() >= 500 || getX() <= 50)
                {
                    handler.removeCallbacks(runnable);
                }

        }
    };



    public Tank(int x, int y, GamePanel gp)
    {

        super(x, y, gp);
        setHeight(100);
        setWidth(getHeight()+50);
        tankImg = BitmapFactory.decodeResource(gp.getResources(), R.drawable.tankpixel);
    }



    public Bitmap getTankImg()
    {
        return tankImg;
    }



    public void move()
    {
        // Draw out the tank figure, giving it values like x, y, width, height.
        setSize(new RectF(getX(), getY(), getX() + getWidth(), getY() + getHeight()));

        getGamePanel().setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View view, MotionEvent event)
            {


//If the finger is pressed down.
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {



                    //Move the tank to right.
                    if (event.getX() > getX()+150 && event.getY() > 600 && event.getY() < 700
                                && getX() <= 500)
                    {
                        tankMoveDirection = 6;
                        handler.postDelayed(runnable, 100);
                        return true;

                    }

                    //Move the tank to left.
                    if (event.getX() < getX() && event.getY() > 600 && event.getY() < 700
                                && getX() >= 50)
                    {
                        tankMoveDirection = -6;
                        handler.postDelayed(runnable , 100);
                        return true;
                    }



                    //This statement can only be accessed if you press on the tank and when boolean is false
                    //The reason why the boolean is false, is because to check if a projectile is already launched
                    //From the human tank.

                    if (event.getX() > getX() && event.getX() < getX()+150
                            && whileFlying == false)
                    {

                        //Identify the first cordinations of x and y of touch point.
                        startTouchX = event.getX();
                        startTouchY = event.getY();


                        //Sets boolean value to true, to be able to access the touch release statement.
                        onTouch = true;
                        return true;
                    }

                }
                // The touch release statement that can only be accessed by previous statement from touch down.
                // Can't be accessed if whileFlying is true (when the projectile is flying)
                if (event.getAction() == MotionEvent.ACTION_UP && whileFlying == false
                        && onTouch == true)

                {

                    //Sets boolean value to true to access the method in gamepanel,
                    //Which creates the projectile
                    whileFlying = true;

                    //Boolean that activates the movement of the artificial tank.
                    getGamePanel().setMoveThatTank(true);

                    //Touch release point cordinations.
                    releaseTouchY = event.getY();
                    releaseTouchX = event.getX();

                    // Calculates the distance between touch and release point
                    calcDistanceY = startTouchY - releaseTouchY;
                    calcDistanceX = startTouchX - releaseTouchX;

                   //Creates the canon object and in game panel, the characteristics are applied.
                    getGamePanel().getObject().add(new Canon(getX() + 155, getY() + 25, getGamePanel()));
                    getGamePanel().getObject().get(0).setYspeed((int) calcDistanceY);

                    //Sets onTouch to false, so that we lock ourselves out of this method.

                    onTouch = false;
                }


                //Cancel automatic tank move if touch is released
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    handler.removeCallbacks(runnable);
                }
                return false;
            }
        });
    }



    public float getCalcDistanceX()
    {
        return calcDistanceX;
    }


    public void setWhileFlying(boolean whileFlying)
    {
        this.whileFlying = whileFlying;
    }



    public boolean isWhileFlying()
    {
        return whileFlying;
    }


}
