import processing.core.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class App extends PApplet {
    float x1, y1, x2, y2;
    boolean clicked;
    // float ballX, ballY;
    int exitX = 205;
    int exitY = 497;
    int exitWidth = 75;
    int exitHeight = 60;
    // int ballSize = 20;
    float collided;
    boolean gameScreen = true;
    boolean endScreen = false;
    ArrayList<Wall> walls = new ArrayList<>();
    PlayerBall ball;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        // ballX = 775;
        // ballY = 100;

        loadFile();

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {

        if(scene == 1){

        }else if(scene == 2){

        }
        if(gameScreen){
            if (ball.hitsEdge()){
                //gamover stuff (end screen)
            }
            //make something that if ball touches exit box then end screen is true.
        }

        if(ball.checkTouchOfExit()){

        }
        background(100);

        ball.display();
        for (Wall w : walls) {
            w.display();
            if(w.checkTouch(ball)){
                //game over stuff 
            }
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

        ball.display();

        fill(255, 0, 0);
        if(ball.checkTouchOfExit(exitX, exitY, exitWidth, exitHeight)){
            System.out.println("you win");
        }

        fill(0, 0, 0);
        textSize(40);
        text("Exit", 210, 540);

        // if (Wall.checkTouch() == true) {
        //     // screen = 1;
        //     // and create the end screen!
        // }
    }
    }

    public void keyPressed() {
        if (key == 's') {
            saveFile();
        }
        if (keyCode == UP) {
            ball.moveUp();
        }
        if (keyCode == DOWN) {
            ball.moveDown();
        }
        if (keyCode == RIGHT) {
            ball.moveRight();
        }
        if (keyCode == LEFT) {
            ball.moveLeft();
        }
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
