public class PhysicsParticles {

    private Vectors currentPosition;
    private Vectors previousPosition;
    private Vectors velocity;
    private Vectors acceleration;
    private final double mass;
    private final double radius;
    private static final double DENSITY = 1.0;
    private boolean particlesAnchored;

    public PhysicsParticles(Vectors position, Vectors velocity, Vectors acceleration, double mass, double radius, boolean anchored) {
        currentPosition = new Vectors(new double[]{
                position.getVectorX(),
                position.getVectorY()
        });

        previousPosition = new Vectors(new double[]{
                position.getVectorX(),
                position.getVectorY()
        });

        this.velocity = velocity;
        this.acceleration = acceleration;
        this.radius = radius;
        this.mass = DENSITY * MathematicalOperations.PI * radius * radius;
        this.particlesAnchored = anchored;

    }

    // setters
    public void setCurrentPosition(Vectors currentPosition){
        this.currentPosition = currentPosition;
    }

    public void setPreviousPosition(Vectors previousPosition){
        this.previousPosition = previousPosition;
    }
    public void setVelocity(Vectors velocity){
        this.velocity = velocity;
    }

    public void setAcceleration(Vectors acceleration){
        this.acceleration = acceleration;
    }

    public void setParticlesAnchored(boolean anchored){
        this.particlesAnchored = anchored;
    }


    // getters
    public Vectors getCurrentPosition(){
        return currentPosition;
    }

    public Vectors getPreviousPosition(){
        return previousPosition;
    }

    public Vectors getVelocity(){
        return velocity;
    }

    public Vectors getAcceleration(){
        return acceleration;
    }

    public double getMass(){
        return mass;
    }

    public double getRadius(){
        return radius;
    }

    public boolean getParticlesAnchored(){
        return particlesAnchored;
    }
}
