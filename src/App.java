import processing.core.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

public class App extends PApplet {
    float x1, y1, x2, y2;
    boolean clicked;
    float x;
    float y;
    Scanner reader;
    ArrayList<Wall> walls = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        // wall1 = new Wall(30, 50, 50, 5, this);
        // wall2 = new Wall(20, 10, 60, 5, this);
        // walls.add(wall1);
        // walls.add(wall2);

        reader = new Scanner(System.in);
    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(100);

        for (Wall w : walls) {
            w.display();
        }
        rect(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2));

    }

    public void keyPressed() {
        if (key == 's') {
            saveFile();
        }
        if (key == 'l'){

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

            for(Wall w : walls) {
                writer.println(w.saveInfo());
            }
            writer.close(); // Closes the writer and saves the file
            System.out.println("Integer saved to file successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    public void loadFile(){
    walls.clear();
    String filePath = "saveWall.txt"; // Path to the text file

        try (PrintWriter writer = new PrintWriter(filePath)) {
            while(reader.hasNextLine()){
                String row = reader.nextLine();
                Wall temp = new Wall(row,this);
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
        Wall w = new Wall(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2), this);
        walls.add(w);

    }

}
