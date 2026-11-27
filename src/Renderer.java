import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Renderer {

    static double zoom = 1.0;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static final int SCREEN_WIDTH = screenSize.width;
    static final int SCREEN_HEIGHT = screenSize.height;

    static final int PROGRAM_WIDTH = (SCREEN_WIDTH) / 2;
    static final int PROGRAM_HEIGHT = (SCREEN_HEIGHT) / 2;

    static BufferedImage image = new BufferedImage(PROGRAM_WIDTH, PROGRAM_HEIGHT, BufferedImage.TYPE_INT_RGB);
    static JPanel mainPanel;
    static JPanel viewPortPanel;

    static Mouse mouse = new Mouse(0, 0);

    public static void main(String[] args) throws InterruptedException {

        JFrame mainFrame = new JFrame("Renderer");


        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        mainFrame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        viewPortPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics G) {
                super.paintComponent(G);

                Graphics2D g2 = (Graphics2D) G.create();

                double getPanelX = viewPortPanel.getWidth() / 2.0;
                double getPanelY = viewPortPanel.getHeight() / 2.0;


                g2.translate(getPanelX, getPanelY);
                g2.scale(zoom, zoom);
                g2.translate(-getPanelX, -getPanelY);
                g2.drawImage(image, 0, 0, null);
                g2.dispose();
            }
        };

        mainPanel.setBackground(new Color(35, 39, 47));
        viewPortPanel.setBackground(new Color(18, 21, 27));

        JLabel userText = new JLabel("...");
        userText.setPreferredSize(new Dimension(25, 25));

        JTextField textField = new JTextField("", 1);


        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(viewPortPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainFrame.add(userText, BorderLayout.NORTH);
        mainFrame.add(textField, BorderLayout.SOUTH);
        mainPanel.setFocusable(true);

        mainFrame.setVisible(true);

        Vectors vector = new Vectors(new double[]{100, 0});
        double theta = MathematicalOperations.toRadians(90);
        Matrix rotation = Matrix.rotationMatrix(theta);

        Matrix vectorMatrix = new Matrix(new double[][]{
                {vector.getVectorX()},
                {vector.getVectorY()}
        });

        Matrix resultingVector = Matrix.matrixMultiplication(rotation, vectorMatrix);
        Vectors rotatedVector = new Vectors(new double[]{
                resultingVector.getElement(0, 0),
                resultingVector.getElement(1, 0)
        });

        //createRope(rotatedVector, 4);

        ArrayList<PhysicsParticles> particles = new ArrayList<>();

        Lighting light = new Lighting(
                mouse.getMousePos(),
                new Vectors(new double[]{0, 0}),
                0.75,
                360,
                100,
                3.0,
                Color.white,
                false,
                true
        );

        double radius = 5.0;
        double mass = Math.random();

       for (int i = 0; i < 5; i++){
           particles.add(new PhysicsParticles(
              new Vectors(new double[]{100 + (50*i), 100}),
              new Vectors(new double[]{0, 0}),
              new Vectors(new double[]{0, 9.81}),
                   mass,
                   radius*(i*0.50),
                   false
           ));
       }



        renderLoop(particles, mouse, light);

       viewPortPanel.addMouseMotionListener(new MouseMotionListener() {

           @Override
           public void mouseMoved(MouseEvent e){
               mouse.setMousePos(e.getX() / zoom, e.getY() / zoom);
           }

           @Override
           public void mouseDragged(MouseEvent e){
               mouse.setMousePos(e.getX(), e.getY());
           }
       });

        // zoom-in
        viewPortPanel.registerKeyboardAction(e-> {

                    if (zoom * 1.1 > 1.5) {
                        return;
                    }
                    zoom *= 1.1;
                    refresh();
                }, KeyStroke.getKeyStroke("ctrl EQUALS"), JComponent.WHEN_IN_FOCUSED_WINDOW);
        // zoom-out
        viewPortPanel.registerKeyboardAction(e-> {

                if (zoom / 1.1 < 1.1){
                    return;
                }
                zoom /= 1.1;
                refresh();
        }, KeyStroke.getKeyStroke("ctrl MINUS"), JComponent.WHEN_IN_FOCUSED_WINDOW);

        textField.addActionListener(e-> {
            actionPerformed(textField, userText);
            mainPanel.requestFocusInWindow();
        });

        textField.addActionListener(e -> {
            actionPerformed(textField, userText);

        });
    }

    public static void clearImage(){
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(18, 21, 27));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    public static void renderLoop(ArrayList<PhysicsParticles> particles, Mouse mouse, Lighting light){
        Timer timer = new Timer(16, e->{
            clearImage();

            light.setPosition(mouse.getMousePos());
            light.setColor(new Color(
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256),
                    (int) (Math.random() * 256)
            ));
            renderLight(light);

            for (int i = 0; i < particles.size(); i++){
                Physics.physicsLoop(particles.get(i), 1.0 / 60.0);
                createCircle(
                        particles.get(i).getCurrentPosition().getVectorX(),
                        particles.get(i).getCurrentPosition().getVectorY(),
                        particles.get(i).getRadius()
                );
            }
            refresh();
        });

        timer.start();
    }

    public void toggleLightMode() {

    }

    public void toggleDarkMode() {

    }

    public static void generatePixel(int x, int y) {
        if (((x >= 0 && x < image.getWidth())) && (y >= 0 && y < image.getHeight())) {
            image.setRGB(x, y, new Color(
                    (int) (Math.random()*256),
                    (int) (Math.random()*256),
                    (int) (Math.random()*256)
            ).getRGB());
        }
    }

    public static void renderLight(Lighting light) {
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                Vectors pixelPos = new Vectors(new double[]{x, y});

                image.setRGB(x, y, light.calculateColor(pixelPos).getRGB());
            }
        }
    }

    public static void createRope(Vectors vector, int physicsCircles) {

        if (physicsCircles < 2) {
            return;
        }

        double x = image.getWidth() / 2.0;
        double y = image.getHeight() / 2.0;

        double vectorLengthX = vector.getVectorX();
        double vectorLengthY = vector.getVectorY();

        double length = Vectors.getVectorLength(vector);// relative to the length of the vector itself, and physics circles

        if (length == 0) {
            return;
        }

        double spacing = length / (physicsCircles - 1);

        double directionX = vectorLengthX / length;
        double directionY = vectorLengthY / length;

        int steps = (int) Math.ceil(length);


        for (int i = 0; i <= steps; i++){

            double t = (double) i / steps;

            int pixelX = (int) MathematicalOperations.round(x+t*vectorLengthX);
            int pixelY = (int) MathematicalOperations.round(y+t*vectorLengthY);

            generatePixel(pixelX, pixelY);

            for (int j = 0; j < physicsCircles; j++) {
                double circleX = x + j * spacing * directionX;
                double circleY = y + j * spacing * directionY;

                createCircle(circleX, circleY, 3);
            }
        }
        refresh();
    }


    public static void createCircle(double x, double y, double r) {

        for (int i = 0; i <= 360; i++) {
            double theta = MathematicalOperations.toRadians(i);

            int pixelX = (int) Math.round((x + r * (MathematicalOperations.cos(theta))));
            int pixelY = (int) Math.round((y + r * (MathematicalOperations.sin(theta))));

            generatePixel(pixelX, pixelY);
        }
        fillCircle(x,y,r);
    }

    public static void fillCircle(double posX, double posY, double r) {

        int radius = (int) MathematicalOperations.ceil(r);

        for (int y = -radius; y <= radius; y++){
            for (int x = -radius; x <= radius; x++){

                if (x*x+y*y<= r*r){
                    generatePixel((int)MathematicalOperations.round(posX + x), (int)MathematicalOperations.round(posY + y));
                }
            }
        }
    }

    public static void createCurvedLine(){

    }

    public static void createTriangle(){

    }


    public static void refresh() {
        viewPortPanel.repaint();
    }

    public static void actionPerformed(JTextField textField, JLabel userInput) {
        String input = textField.getText();
        userInput.setText(input);

    }
}