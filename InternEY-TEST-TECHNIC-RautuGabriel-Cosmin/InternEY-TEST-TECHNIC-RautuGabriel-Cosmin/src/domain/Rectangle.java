package domain;

public class Rectangle {
    private final String nameOfRectangle; //the name of the rectangle
    private final int coordinateX; // the coordinate of the rectangle on the Ox axes
    private final int coordinateY; // the coordinate of the rectangle on the Oy axes
    private final int width; // the width of the rectangle
    private final int height; // the height of the rectangle

    public Rectangle(String nameOfRectangle,int coordinateX, int coordinateY, int width, int height){
        this.nameOfRectangle = nameOfRectangle;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
    }

    public String getNameOfRectangle() {
        return nameOfRectangle;
    }

    public int getCoordinateX(){
        return coordinateX;
    }

    public int getCoordinateY(){
        return coordinateY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    /**
     * this method finds the overlapping area of two rectangles and returns a new rectangle if they overlap or null
     * if they do not overlap
     */
    public static Rectangle findOverlap(Rectangle rectangle1, Rectangle rectangle2){
        //here we compute the overlapping boundaries
        int overlapX1 = Math.max(rectangle1.getCoordinateX(), rectangle2.getCoordinateX()); //left side
        int overlapY1 = Math.max(rectangle1.getCoordinateY(), rectangle2.getCoordinateY()); //top side
        int overlapX2 = Math.min(rectangle1.getCoordinateX() + rectangle1.getWidth(), //right side
                rectangle2.getCoordinateX() + rectangle2.getWidth());
        int overlapY2 = Math.min(rectangle1.getCoordinateY() + rectangle1.getHeight(), //bottomm side
                rectangle2.getCoordinateY() + rectangle2.getHeight());

        int overlapWidth = overlapX2 - overlapX1; //overall width
        int overlapHeight = overlapY2 - overlapY1; //overall height

        if(overlapWidth > 0 && overlapHeight > 0){ //if there is overlap we create a new constructor that will display
            //the two rectangles
            return new Rectangle("Overlap( " + rectangle1.getNameOfRectangle() + " and " +
                    rectangle2.getNameOfRectangle(), overlapX1, overlapY1, overlapWidth, overlapHeight);
        }else{// if there is no overlap we return null
            return null;
        }
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "nameOfRectangle = " + nameOfRectangle +
                ", coordinateX = " + coordinateX +
                ", coordinateY = " + coordinateY +
                ", width = " + width +
                ", height = " + height +
                '}';
    }

    public static void main(String[] args){
        Rectangle rectangle = new Rectangle("rect1",10,10,10,10);
        System.out.println(rectangle);
    }
}
