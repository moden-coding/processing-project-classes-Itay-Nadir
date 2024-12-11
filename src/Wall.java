import processing.core.PApplet;

public class Wall {
    private float x;
    private float y;
    private float width;
    private float height;
    private int size;
    private PApplet canvas;
    private int color;

    public Wall(float xPos, float yPos, float h, float w, PApplet draw) {
        this.x = xPos;
        this.y = yPos;
        canvas = draw;
        this.color = canvas.color(0, 0, 0);
        this.width = w;
        this.height = h;
    }

    public Wall(String line, PApplet draw){
        String[] parts = line.split(",");
        this.x = Float.valueOf(parts[0]);
        this.y = Float.valueOf(parts[1]);
        this.height = Float.valueOf(parts[2]);

        canvas = draw;
    }

    public void display() {
        canvas.fill(color);
        canvas.rect(this.x, this.y, this.width, this.height, this.size);
        
    }

    public boolean checkTouch(PlayerBall p) {
        float distanceFromWall = canvas.dist(p.getX(), this.x, p.getY(), this.y);
        if (distanceFromWall < size / 2) {
            return true;
        } else {
            return false;
        }
    }
    public String saveInfo(){
        return "" + x + "," + y + "," + width + ","+ height; 
    }
}
