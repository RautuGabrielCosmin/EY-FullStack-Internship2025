package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //We check if the rectangle is completely in the size of the canvas
    private static boolean isCompletelyInCanvas(Rectangle rectangle, int canvasWidth, int canvasHeight) {
        int coordinateX1 = rectangle.getCoordinateX();//the left X of the rectangle
        int coordinateY1 = rectangle.getCoordinateY();//the top Y of the rectangle
        int coordinateX2 = coordinateX1 + rectangle.getWidth();//the right X boundary
        int coordinateY2 = coordinateY1 + rectangle.getHeight();//the bottom Y boundary

        //if the conditions meet we return true because the rectangle is entirely within 0, canvasWidth and 0, canvasHeight
        return (coordinateX1 >= 0 && coordinateY1 >= 0 && coordinateX2 <= canvasWidth && coordinateY2 <= canvasHeight);
    }

    // Check if rectangle1 is completely inside rectangle2
    private static boolean isInsideTheCanvas(Rectangle rectangle1, Rectangle rectangle2) {
        int rectangle1CoordinateX1 = rectangle1.getCoordinateX();//left X of rectagnle1
        int rectangle1CoordinateY1 = rectangle1.getCoordinateY();//top Y of rectangle1
        int rectangle1CoordinateX2 = rectangle1CoordinateX1 + rectangle1.getWidth();// right X of rectangle1
        int rectangle1CoordinateY2 = rectangle1CoordinateY1 + rectangle1.getHeight();//bottom Y of rectangle1

        int rectangle2CoordinateX1 = rectangle2.getCoordinateX();//left X of rectangle2
        int rectangle2CoordinateY1 = rectangle2.getCoordinateY();//top Y of rectangle2
        int rectangle2CoordinateX2 = rectangle2CoordinateX1 + rectangle2.getWidth();//right X of rectangle2
        int rectangle2CoordinateY2 = rectangle2CoordinateY1 + rectangle2.getHeight();//bottom Y of rectangle2

        //return true if rectangle1 is within the dimensions of rectangle2
        return (rectangle1CoordinateX1 >= rectangle2CoordinateX1 && rectangle1CoordinateY1 >= rectangle2CoordinateY1 &&
                rectangle1CoordinateX2 <= rectangle2CoordinateX2 && rectangle1CoordinateY2 <= rectangle2CoordinateY2);
    }

    public static void main(String[] args) {
        // 1) Read the canvas dimensions
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the width of the canvas: ");
        int canvasWidth = scanner.nextInt();

        System.out.println("Enter the height of the canvas: ");
        int canvasHeight = scanner.nextInt();

        List<Rectangle> rectangles = new ArrayList<>();
        //!!!
        // Cine se va uita peste acest program (daca se uita cineva :) ) il rog ca aici unde am pus path-ul fisierului
        //input.txt sa salveze fisierele in calculator si sa schimbe path (ruta) fisierului meu cu cea din calculatorul dumneavoastra
        //!!!
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Rautu\\Desktop\\Leetcode\\untitled\\src\\domain\\input.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {//skip the blank lines if there are any in the file
                    continue;
                }
                try {
                    String[] strings = line.split("\\s+");//in the file we must have this format name coordinateX
                    //coordinateY width height there should not be any commas or dashes only spaces
                    if (strings.length != 5) {
                        System.out.println("Invalid line format: " + line);
                        continue; // skip this line but keep reading
                    }
                    //the format from the input.txt file name coordX coordY width height
                    String name = strings[0];
                    int coordinateX = Integer.parseInt(strings[1]);
                    int coordinateY = Integer.parseInt(strings[2]);
                    int width = Integer.parseInt(strings[3]);
                    int height = Integer.parseInt(strings[4]);

                    if (width <= 0 || height <= 0) {
                        System.out.println("Invalid width or height format: " + line);
                        continue;
                    }

                    rectangles.add(new Rectangle(name, coordinateX, coordinateY, width, height));

                } catch (NumberFormatException e) {
                    System.out.println("Invalid integer format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //a) List rectangles that are completely in the canvas
        List<Rectangle> insideCanvas = new ArrayList<>(); //create the list
        for (Rectangle rectangle : rectangles) {//itereate over the rectangles
            if (isCompletelyInCanvas(rectangle, canvasWidth, canvasHeight)) {//if the rectangle is fully in the canvas
                //we add it
                insideCanvas.add(rectangle);
            }
        }
        //print on the screen every rectangle that is inside the canvas
        System.out.println("\na):");
        for (Rectangle rectangle : insideCanvas) {
            System.out.println(rectangle + " ,");
        }

        //b) From insideCanvas, list rectangles that are already inside the canvas that do NOT overlap any other rectangle
        List<Rectangle> nonOverlappingRectanglesInsideCanvas = new ArrayList<>();//create a list to hold rectangles that do not overlap with each other inside the canvas
        for (Rectangle rectangle1 : insideCanvas) {//iterate over the list of the rectangles that are inside the canvas
            boolean overlapsRectangle = false;//first we set false because they do not overlap
            for (Rectangle rectangle2 : insideCanvas) {
                if (rectangle1 == rectangle2) { // skip itself to avoid checking overlap with itself
                    continue;//continue to the next iteration
                }
                if (Rectangle.findOverlap(rectangle1, rectangle2) != null) {
                    overlapsRectangle = true;//we set true because they do overlap
                    break;
                }
            }
            if (!overlapsRectangle) {//if the rectangles do not overlap we add them to the list.
                nonOverlappingRectanglesInsideCanvas.add(rectangle1);
            }
        }
        //print the rectangles that do not overlap
        System.out.println("\nb):");
        for (Rectangle rectangle : nonOverlappingRectanglesInsideCanvas) {
            System.out.println(rectangle + " ,");
        }

        //(C) From insideCanvas, list rectangles that are completely included in another rectangle
        List<Rectangle> insideAnotherRectangleFromInsideCanvas = new ArrayList<>();//create a new list of rectangles that are included in another rectangle
        for (Rectangle rectangle1 : insideCanvas) {//iterate over the list with rectangle1
            for (Rectangle rectangle2 : insideCanvas) {//iterate over the list with rectangle2
                if (rectangle1 == rectangle2) { // skip itself to avoid checking overlap with itself
                    continue;//continue to the next iteration
                }
                if (isInsideTheCanvas(rectangle1, rectangle2)) {//if rectangle1 is inside rectangle2 we add it to the list
                    insideAnotherRectangleFromInsideCanvas.add(rectangle1);
                    break;
                }
            }
        }
        System.out.println("\nc):");
        for (Rectangle rectangle : insideAnotherRectangleFromInsideCanvas) {
            System.out.println(rectangle + " ,");
        }
        //e)
        boolean[][] grid = new boolean[canvasWidth][canvasHeight];//create a 2D boolean array with the size of the canvas
        int coveredCells = 0;// coveredCells will count how many cells are covered by at least 1 rectangle
        for(Rectangle rectangle : insideCanvas) {//iterates over the rectangles from inside the canvas
            int startX = rectangle.getCoordinateX();//left X coordinate of the canvas
            int startY = rectangle.getCoordinateY();//top Y coordinate of the canvas
            int endX = startX + rectangle.getWidth();//the right boundary
            int endY = startY + rectangle.getHeight();//the bottom boundary

            for (int x = startX; x < endX; x++) {//this loop will cover the horizontal line
                for (int y = startY; y < endY; y++) {//this loop will cover the vertical line
                    if (!grid[x][y]) {//if it is false it means that the cell is covered by a rectangle
                        grid[x][y] = true; //if it is true it is not covered by a triangle and we set it true
                        coveredCells++;//increment the counter because we found one cell that was not marked
                    }
                }
            }
        }
        int uncoveredArea = canvasWidth * canvasHeight - coveredCells;//computes the uncovered area by substracting the coveredCells from the total area of the canvas
        System.out.println("\ne):" + uncoveredArea);
    }
}