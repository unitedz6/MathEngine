public class Mouse {
    private double currentPosX;
    private double currentPosY;

    public Mouse(double currentPosX, double currentPosY){
        this.currentPosX = currentPosX;
        this.currentPosY = currentPosY;
    }

    // setters
    public void setCurrentPosX(double x){
        this.currentPosX = x;
    }

    public void setCurrentPosY(double y){
        this.currentPosY = y;
    }

    public void setMousePos(double x, double y){
        this.currentPosX = x;
        this.currentPosY = y;
    }

    // getters
    public double getCurrentPosX(){
        return currentPosX;
    }

    public double getCurrentPosY(){
        return currentPosY;
    }

    public Vectors getMousePos(){
        return new Vectors(new double[]{currentPosX, currentPosY});
    }
}
