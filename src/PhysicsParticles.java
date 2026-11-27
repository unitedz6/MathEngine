public class PhysicsParticles {

    private Vectors currentPosition;
    private Vectors previousPosition;
    private boolean particlesAnchored;

    public PhysicsParticles(Vectors position, boolean anchored){
        this.currentPosition = new Vectors(new double[]{
                position.getVectorX(),
                position.getVectorY()
        });

        this.previousPosition = new Vectors(new double[]{
                position.getVectorX(),
                position.getVectorY()
        });
    }
}
