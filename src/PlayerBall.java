import processing.core.PApplet;

public class PlayerBall {
    // Variables for ball:
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;

    // Constructor for class which sets default or initial values for the variables:
    public PlayerBall(int ballX, int ballY, PApplet c) {
        this.x = ballX;
        this.y = ballY;
        this.size = 20;
        canvas = c;
        this.speed = 5;
        this.color = canvas.color(255, 255, 0);
    }

    // Returns the position of the ball:
    public int[] getPosition() {
        int[] pos = { this.x, this.y };
        return pos;
    }

    // Check if the ball touches the exit (use of Chat Gpt here to help edit the
    // method):
    public boolean checkTouchOfExit(int exitX, int exitY, int exitWidth, int exitHeight) {
        // Finds the closest points of the exit to the ball:
        float closestX = canvas.constrain(x, exitX, exitX + exitWidth);
        float closestY = canvas.constrain(y, exitY, exitY + exitHeight);
        // Checks if the ball is in the exit box:
        if (canvas.dist(x, y, closestX, closestY) < 20 / 2) {
            return true;
        } else {
            return false;
        }
    }

    // Displays the ball on the canvas:
    public void display() {
        canvas.fill(color);
        canvas.circle(this.x, this.y, this.size);
    }

    // Moves the ball up based on it's speed:
    public void moveUp() {
        y -= speed;
    }

    // Moves the ball down based on it's speed:
    public void moveDown() {
        y += speed;
    }

    // Moves the ball right based on it's speed:
    public void moveRight() {
        x += speed;
    }

    // Moves the ball left based on it's speed:
    public void moveLeft() {
        x -= speed;
    }

    // Returns the ball's X position:
    public int getX() {
        return x;
    }

    // Returns the ball's Y position:
    public int getY() {
        return y;
    }

    // Checks if the ball hits the edge of the screen:
    public boolean hitsEdge() {
        if (x < 0 || x > canvas.width || y < 0 || y > canvas.height) {
            return true;
        } else {
            return false;
        }
    }

    // Returns the radius of the ball:
    public float getRadius() {
        return size;
    }
}
