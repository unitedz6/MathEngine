import java.awt.*;

public class Lighting {
    private Vectors position;
    private Vectors direction;

    private double intensity;
    private double angle;
    private double radius;
    private double falloff;
    private Color color;
    private boolean shadows;
    private boolean enabled;


    public Lighting(Vectors position, Vectors direction, double intensity, double angle, double radius, double falloff, Color color, boolean shadows, boolean enabled){
        this.position = position;
        this.direction = direction;
        this.intensity = intensity;
        this.angle = angle;
        this.radius = radius;
        this.falloff = falloff;
        this.color = color;
        this.shadows = shadows;
        this.enabled = enabled;
    }

    // getters
    public double[] getPosition() {
        return position.getVector();
    }

    public Vectors getDirection() {
        return direction;
    }

    public double getIntensity() {
        return intensity;
    }

    public double getAngle() {
        return angle;
    }

    public double getRadius() {
        return radius;
    }

    public double getFalloff() {
        return falloff;
    }

    public Color getColor(){
        return color;
    }

    public boolean getShadows(){
        return shadows;
    }

    public boolean getEnabled(){
        return enabled;
    }

    // setters
    public void setPosition(Vectors position) {
        this.position = position;
    }

    public void setDirection(Vectors direction) {
        this.direction = direction;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setFalloff(double falloff) {
        this.falloff = falloff;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setShadows(boolean shadows){
        this.shadows = shadows;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    // functions
    public double calculateIntensity(Vectors pixelPos){
       return intensity * MathematicalOperations.exponent(MathematicalOperations.max(0, 1 - Vectors.vectorDistance(pixelPos, position) / radius), (int) falloff);
    }

    public Color calculateColor(Vectors pixelPos){
        double intensity = calculateIntensity(pixelPos);

        int red = (int) (color.getRed() * intensity);
        int blue = (int) (color.getBlue() * intensity);
        int green = (int) (color.getGreen() * intensity);

        return new Color(red, green, blue);
    }
}
