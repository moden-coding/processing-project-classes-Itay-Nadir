import processing.core.PApplet;

public class PlayerBall {

    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;
    private int position;

    public PlayerBall(int ballX, int ballY, PApplet c, int ballPosition){
        this.x = ballX;
        this.y = ballY;
        this.size = 50;
        canvas = c;
        this.color = canvas.color(255,255,0);
    }
    public int[] getPosition(){
        int[]pos = {this.x,this.y}; 
        return pos;
    }
    public void checkTouchOfExit(){

    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    }

