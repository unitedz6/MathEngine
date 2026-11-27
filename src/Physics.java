public class Physics {

    private double GRAVITY;
    private double previousTime;
    private double currentTime;


    public Physics(){
        this.GRAVITY = -9.81;
        this.previousTime = 0.0;
        this.currentTime = 0.0;
    }

    public static void physicsLoop(PhysicsParticles particle, double deltaTime){

            if (!particle.getParticlesAnchored()){
                particle.setPreviousPosition(particle.getCurrentPosition());

                particle.setVelocity(updateVelocity(particle, deltaTime));

                particle.setCurrentPosition(updatePosition(particle, deltaTime));
            }
        }

    // setters
    public void setGRAVITY(double GRAVITY){
        this.GRAVITY = GRAVITY;
    }

    public void setPreviousTime(double previousTime){
        this.previousTime = previousTime;
    }

    public void setCurrentTime(double currentTime){
        this.currentTime = currentTime;
    }


    // getters

    public double getGravity(){
        return GRAVITY;
    }

    public double getPreviousTime(){
        return previousTime;
    }

    public double getCurrentTime(){
        return currentTime;
    }

    // functions
    public static double deltaTime(double previousTime, double currentTime){
        return currentTime - previousTime;
    }

    public static Vectors updateVelocity(PhysicsParticles particle, double deltaTime) {
        return Vectors.addVectors(particle.getVelocity(), Vectors.scalarMultiply(deltaTime, particle.getAcceleration()));
    }

    public static Vectors updatePosition(PhysicsParticles particle, double deltaTime) {
       return Vectors.addVectors(
               particle.getCurrentPosition(),
               Vectors.scalarMultiply(deltaTime, particle.getVelocity())
       );
    }
}
