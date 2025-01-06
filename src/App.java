import processing.core.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class App extends PApplet {
    // Vatiables for the wall:
    float x1, y1, x2, y2;
    boolean clicked;
    // Variables for the exit:
    int exitX = 205;
    int exitY = 497;
    int exitWidth = 75;
    int exitHeight = 60;
    float collided;
    // Game conditions:
    int scene = 1;
    boolean gameOver = false;
    boolean gameWon = false;
    // Game objects:
    ArrayList<Wall> walls = new ArrayList<>();
    PlayerBall ball;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        // Starting position for ball:
        ball = new PlayerBall(775, 100, this);
        loadFile();

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(100);
        if (scene == 1) {
            // Maze editor mode:
            fill(0);
            textSize(24);
            text("Maze Editor and Press Enter to Start Game", 370, 25);

            float wallWidth = abs(x1 - x2);
            float wallHeight = abs(y1 - y2);

            // Makes the lower of the wall's height and width be 20:
            if (wallWidth < wallHeight) {
                wallWidth = 20;
            }

            if (wallHeight < wallWidth) {
                wallHeight = 20;
            }

            rect(min(x1, x2), min(y1, y2), wallWidth, wallHeight);

            // Display the walls:
            for (Wall w : walls) {
                w.display();
            }
        } else if (scene == 2) {
            // Gameplay mode:
            ball.display();
            // Checks if the ball hits the edge of the screen:
            if (ball.hitsEdge()) {
                fill(255, 0, 0);
                textSize(64);
                text("Game Over", width / 2 - 150, height / 2);
                gameOver = true;
            }
            for (Wall w : walls) {
                w.display();
                // Checks the wall for collisions:
                if (w.checkTouch(ball)) {
                    fill(255, 0, 0);
                    textSize(64);
                    text("Game Over", width / 2 - 150, height / 2);
                    gameOver = true;
                }
                // Checks if the ball reaches the exit:
                if (ball.checkTouchOfExit(exitX, exitY, exitWidth, exitHeight)) {
                    fill(255, 0, 0);
                    textSize(64);
                    text("You Won", width / 2 - 150, height / 2);
                    gameWon = true;
                }
            }
            // Draws the Exit text:
            fill(0, 0, 0);
            textSize(40);
            text("Exit", 210, 540);
        }
    }

    public void keyPressed() {
        // Switches between the maze editor to the gameplay:
        if (scene == 1) {
            if (key == ENTER) {
                scene = 2;
            }
            // Saves the walls to a file:
            if (key == 's') {
                saveFile();
            }
        }
        // Movement during gameplay:
        else if (scene == 2) {
            if (key == ENTER) {
                resetGame();
                scene = 1;
            }
            if (keyCode == UP) {
                // Moves ball up:
                ball.moveUp();
                for (Wall w : walls) {

                    if (w.checkTouch(ball)) {
                        // Reverses movement once there is a collision:
                        ball.moveDown();
                    }
                }
            }
            if (keyCode == DOWN) {
                // Moves ball down:
                ball.moveDown();
                for (Wall w : walls) {

                    if (w.checkTouch(ball)) {
                        // Reverses movement once there is a collision:
                        ball.moveUp();
                    }
                }
            }
            if (keyCode == RIGHT) {
                // Moves ball right:
                ball.moveRight();
                for (Wall w : walls) {

                    if (w.checkTouch(ball)) {
                        // Reverses movement once there is a collision:
                        ball.moveLeft();
                    }
                }
            }

            if (keyCode == LEFT) {
                // Moves ball left:
                ball.moveLeft();
                for (Wall w : walls) {

                    if (w.checkTouch(ball)) {
                        // Reverses movement once there is a collision:
                        ball.moveRight();
                    }
                }
            }
        }

    }

    // Writes and saves the wall positions to a file:
    public void saveFile() {
        String filePath = "saveWall.txt";

        try (PrintWriter writer = new PrintWriter(filePath)) {

            for (Wall w : walls) {
                writer.println(w.saveInfo());
            }
            writer.close();
            System.out.println("Integer saved to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Reads and loads the wall positions from a file:
    public void loadFile() {
        String filePath = "saveWall.txt";

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

    // When a mouse is pressed somoene is clicking and it creates an X and Y
    // variable of where is being clicked:
    public void mousePressed() {
        clicked = true;
        x1 = mouseX;
        y1 = mouseY;
    }

    // When a mouse is being dragged and somone clicked then it creates a second X
    // and Y variable to form a rectangle:
    public void mouseDragged() {
        if (clicked) {
            x2 = mouseX;
            y2 = mouseY;
        }
    }

    // When the mouse is released it creates a wall:
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

    // Resets the game conditions and ball position:
    public void resetGame() {
        gameOver = false;
        gameWon = false;
        ball = new PlayerBall(775, 100, this);
    }
}
