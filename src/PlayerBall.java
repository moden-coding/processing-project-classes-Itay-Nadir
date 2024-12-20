import processing.core.PApplet;

public class PlayerBall {

    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;

    public PlayerBall(int ballX, int ballY, PApplet c){
        this.x = ballX;
        this.y = ballY;
        this.size = 20;
        canvas = c;
        this.speed = 5;
        this.color = canvas.color(255,255,0);
    }
    public int[] getPosition(){
        int[]pos = {this.x,this.y}; 
        return pos;
    }
    public boolean checkTouchOfExit(int exitX, int exitY, int exitWidth, int exitHeight){
        float closestX = canvas.constrain(x, exitX, exitX + exitWidth);
        float closestY = canvas.constrain(y, exitY, exitY + exitHeight);

        if(canvas.dist(x,y, closestX, closestY) <20/2){
            return true;
        }else{
            return false;
        }
    }
    public void display(){
        canvas.fill(color);
        canvas.circle(this.x, this.y, this.size);
    }

    public void moveUp(){
        y -= speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveLeft(){
        x -= speed;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean hitsEdge(){
        if(x < 0 || x > canvas.width || y < 0 || y > canvas.height){
            return true;    
        }else{
            return false;
        }
    }

    public float getRadius(){
        return size;
    }
    }

