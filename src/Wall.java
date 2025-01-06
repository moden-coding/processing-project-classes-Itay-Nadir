import processing.core.PApplet;

public class Wall {
    // Variables for the wall:
    private float x;
    private float y;
    private float width;
    private float height;
    private PApplet canvas;
    private int color;

    // Constructor for class which sets default or initial values for the variables:
    public Wall(float xPos, float yPos, float w, float h, PApplet draw) {
        this.x = xPos;
        this.y = yPos;
        canvas = draw;
        this.color = canvas.color(0, 0, 0);
        this.width = w;
        this.height = h;
    }

    // Second constructor to create a wall from the saved data:
    public Wall(String savedData, PApplet draw) {
        String[] data = savedData.split(","); // Splits the saved data into it's own values:
        this.x = Float.valueOf(data[0]);
        this.y = Float.valueOf(data[1]);
        this.width = Float.valueOf(data[2]);
        this.height = Float.valueOf(data[3]);

        canvas = draw;
    }

    // Displays the wall on the canvas:
    public void display() {
        canvas.fill(color);
        canvas.rect(this.x, this.y, this.width, this.height);

    }

    // Checks if the ball is touching the wall ( use of Chat Gpt to help edit the
    // method):
    public boolean checkTouch(PlayerBall p) {
        float closestX = canvas.constrain(p.getX(), this.x, this.x + this.width);
        float closestY = canvas.constrain(p.getY(), this.y, this.y + this.height);

        if (canvas.dist(p.getX(), p.getY(), closestX, closestY) < p.getRadius()) {
            return true;
        } else {
            return false;
        }
    }

    // Saves the wall's variables into a specific order:
    public String saveInfo() {
        return "" + x + "," + y + "," + width + "," + height;
    }
}
