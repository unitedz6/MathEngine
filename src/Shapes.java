import java.awt.*;
import java.net.http.UnsupportedProtocolVersionException;

public class Shapes {

    private static final double PI = MathematicalOperations.PI;

    private Shape shape;
    private Vectors position;
    private Vectors[] vertices = new Vectors[0];
    private double length;
    private double height;
    private double width;
    private double rotation;
    private double radius;
    private Color color;
    private boolean filled;

    public enum Shape {
        CIRCLE,
        RECTANGLE,
        TRIANGLE,
        POLYGON,
        ELLIPSE,
        PARABOLA,
        LINE
    }


    public Shapes(Vectors position, double length, double height, double width, double rotation, double radius, Color color, boolean filled, Shape shape){
        this.position = position;
        this.length = length;
        this.height = height;
        this.width = width;
        this.rotation = rotation;
        this.radius = radius;
        this.color = color;
        this.filled = filled;
        this.shape = shape;
    }

    // setters
    public void setPosition(Vectors vector){
        this.position = vector;
    }

    public void setLength(double length){
        this.length = length;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public void setShape(Shape shape){
        this.shape = shape;
    }

    // getters
    public Vectors getPosition(){
        return position;
    }

    public double getLength(){
        return length;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    public double getRotation(){
        return rotation;
    }

    public double getRadius(){
        return radius;
    }

    public Color getColor(){
        return color;
    }

    public Vectors[] getVertices(){
        return vertices;
    }

    public boolean getFilled(){
        return filled;
    }

    public Shape getShape(){
        return shape;
    }

    public double getArea(){
        switch (shape) {
            case CIRCLE:
              return (PI * MathematicalOperations.exponent(radius, 2));

            case RECTANGLE:
                return width * height;

            case TRIANGLE:
                return (0.5 * width * height);

            case POLYGON:
                if (vertices.length < 3){
                    throw new IllegalArgumentException("Polygons require at least 3 vertices");
                }
                double areaSum = 0.0;

                        for (int i = 0; i < vertices.length; i++) {
                            int j = (i + 1) % vertices.length;

                        Matrix m = new Matrix(new double[][]{
                                {vertices[i].getVectorX(), vertices[i].getVectorY()},
                                {vertices[j].getVectorX(), vertices[j].getVectorY()}
                        });

                            areaSum += Matrix.calculateDeterminant(m);
                        }
                        return 0.5 * MathematicalOperations.absolute(areaSum);

            case ELLIPSE:
                return (PI*(MathematicalOperations.divide(width, 2)*MathematicalOperations.divide(height, 2)));

            default:
                throw new UnsupportedOperationException();
        }
    }
/*
    public double getPerimeter(){
        switch (shape){
            case CIRCLE:
                return (2*PI*radius);
            case RECTANGLE:
                return 2*(length+width);
            case TRIANGLE:
            case POLYGON:
            case ELLIPSE:
            default:
                throw new UnsupportedProtocolVersionException();
        }
    }
*/
    public void calculateVertices(){

    }

    public void rotateVertices(){

    }
}
