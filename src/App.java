import processing.core.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class App extends PApplet {
    float x1, y1, x2, y2;
    boolean clicked;
    float ballX, ballY;
    int ballSize = 20;
    float collided;
    ArrayList<Wall> walls = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        ballX = 775;
        ballY = 100;

        // wall1 = new Wall(30, 50, 50, 5, this);
        // wall2 = new Wall(20, 10, 60, 5, this);
        // walls.add(wall1);
        // walls.add(wall2);

        // width = 5;
        // if(width < hieght){
        // width = 5;
        // }
        // else if (width > hieght){
        // width = hieght;
        // }

        loadFile();

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(100);

        for (Wall w : walls) {
            w.display();
        }
        float wallWidth = abs(x1 - x2);
        float wallHeight = abs(y1 - y2);

        if (wallWidth < wallHeight) {
            wallWidth = 20;
        }

        if (wallHeight < wallWidth) {
            wallHeight = 20;
        }
        rect(min(x1, x2), min(y1, y2), wallWidth, wallHeight);

        fill(255, 0, 0);
        ellipse(ballX, ballY, ballSize, ballSize);
    }

    public void keyPressed() {
        if (key == 's') {
            saveFile();
        }
        if (keyCode == UP) {
            ballY -= 15;
        }
        if (keyCode == DOWN) {
            ballY += 15;
        }
        if (keyCode == RIGHT) {
            ballX += 15;
        }
        if (keyCode == LEFT) {
            ballX -= 15;
        }

        // setup();
        // String input = reader.nextLine();
        // String[] parts = input.split(",");
        // Wall wall = new Wall(x, y, Integer.valueOf(parts[0]),
        // Integer.valueOf(parts[1]), this);
        // // Wall wall = new Wall( 100, 5, this);
        // walls.add(wall);

    }

    public void saveFile() {
        String filePath = "saveWall.txt"; // Path to the text file

        try (PrintWriter writer = new PrintWriter(filePath)) {

            for (Wall w : walls) {
                writer.println(w.saveInfo());
            }
            writer.close(); // Closes the writer and saves the file
            System.out.println("Integer saved to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void loadFile() {
        String filePath = "saveWall.txt"; // Path to the text file

        try (Scanner scanner = new Scanner(Paths.get(filePath))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                Wall temp = new Wall(row, this);
                walls.add(temp);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");

        }
    }

    public void mousePressed() {
        clicked = true;
        x1 = mouseX;
        y1 = mouseY;
        // if (key == ' '){
        // setup();
        // System.out.println("get input");
        // String input = reader.nextLine();
        // String[] parts = input.split(",");
        // Wall wall = new Wall(x, y, Integer.valueOf(parts[0]),
        // Integer.valueOf(parts[1]), this);
        // // Wall wall = new Wall( 100, 5, this);
        // walls.add(wall);
    }

    public void mouseDragged() {
        if (clicked) {
            x2 = mouseX;
            y2 = mouseY;
        }
    }

    public void mouseReleased() {
        float wallWidth = abs(x1 - x2);
        float wallHeight = abs(y1 - y2);

        if (wallWidth < wallHeight) {
            wallWidth = 20;
        }

        if (wallHeight < wallWidth) {
            wallHeight = 20;
        }
        Wall w = new Wall(min(x1, x2), min(y1, y2), wallWidth, wallHeight, this);
        walls.add(w);

    }

}
