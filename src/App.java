import processing.core.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;

public class App extends PApplet {
    float x1, y1, x2, y2;
    boolean clicked;
    float x;
    float y;
    // int hieght;
    ArrayList<Wall> walls = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
      
        // wall1 = new Wall(30, 50, 50, 5, this);
        // wall2 = new Wall(20, 10, 60, 5, this);
        // walls.add(wall1);
        // walls.add(wall2);

        // width = 5;
        // if(width < hieght){
        //     width = 5;
        // }
        // else if (width > hieght){
        //     width = hieght;
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

        if(wallWidth < wallHeight){
            wallWidth = 5;
        }

        if(wallHeight < wallWidth){
            wallHeight = 5;
        }
        rect(min(x1, x2), min(y1, y2), wallWidth, wallHeight);

    }

    public void keyPressed() {
        if (key == 's') {
            saveFile();
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

        try (Scanner scanner = new Scanner(Paths.get(filePath))){
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
        Wall w = new Wall(min(x1, x2), min(y1, y2), abs(x1 - x2), abs(y1 - y2), this);
        walls.add(w);

    }

}
