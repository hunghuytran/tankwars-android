package tankwarz;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Object
{

    int x, y, width, height, xspeed, yspeed;
    private RectF size;
    private GamePanel gp;
    private Paint color;



    public Object(int x, int y, GamePanel gp)
    {

        this.x = x;
        this.gp = gp;
        this.y = y;
        xspeed = yspeed;
        width = height = 20;//The shape of the object
        color = new Paint(); //Taken from the previous variables.
        color.setColor(Color.RED);
        size = new RectF (x, y, x+width, y+height); //Gives the RectF values from variables
    }



    public void move()
    {

        if (x >= gp.getTank().getX() && x <= (gp.getTank().getX()+gp.getTank().getWidth())
                && y >= gp.getTank().getY() && y <= (gp.getTank().getY()+gp.getTank().getHeight()))
    {
        System.out.println("Tank is hit");
    //System.exit(0);
    }
        if (x >= gp.getAitank().getX() && x <= (gp.getAitank().getX()+gp.getAitank().getWidth())
                && y >= gp.getAitank().getY() && y <= (gp.getAitank().getY()+gp.getAitank().getHeight()))
        {

            System.out.println("Enemy tank is hit");
     // System.exit(0);
        }

        x+=xspeed;
        y+=yspeed;
        size = new RectF (x, y, x+width, y+height);
    }



    public Paint getColor()
    {
        return color;
    }



    public RectF getSize()
    {
        return size;
    }



    public int getX()
    {
        return x;
    }



    public int getY()
    {
        return y;
    }



    public GamePanel getGamePanel()
    {
        return gp;
    }



    public int getXspeed()
    {
        return xspeed;
    }



    public int getYspeed()
    {
        return yspeed;
    }



    public int getWidth()
    {
        return width;
    }



    public int getHeight()
    {
        return height;
    }



    public void setSize(RectF size)
    {
        this.size = size;
    }



    public void setX(int x)
    {
        this.x = x;
    }



    public void setY(int y)
    {
        this.y = y;
    }



    public void setYspeed(int yspeed)
    {
        this.yspeed = yspeed;
    }



    public void setXspeed(int xspeed)
    {
        this.xspeed = xspeed;
    }



    public void setWidth(int width)
    {
        this.width = width;
    }



    public void setHeight(int height)
    {
        this.height = height;
    }



}