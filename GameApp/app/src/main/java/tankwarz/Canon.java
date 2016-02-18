package tankwarz;


public class Canon extends Object {

    private float gravity;


    public Canon(int x, int y, GamePanel gp) {
        super(x, y, gp);
        gravity = (float)9.8;
    }


    public void move() {
        super.move();
    }

}