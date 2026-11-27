import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    static double zoom = 1.0;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static final int SCREEN_WIDTH = screenSize.width;
    static final int SCREEN_HEIGHT = screenSize.height;

    static final int PROGRAM_WIDTH = (SCREEN_WIDTH) / 2;
    static final int PROGRAM_HEIGHT = (SCREEN_HEIGHT) / 2;

    static BufferedImage image = new BufferedImage(PROGRAM_WIDTH, PROGRAM_HEIGHT, BufferedImage.TYPE_INT_RGB);
    static JPanel mainPanel;

    public static void main(String[] args) throws InterruptedException {

        JFrame mainFrame = new JFrame("Renderer");

        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
        mainFrame.setLocationRelativeTo(null);


            @Override
            protected void paintComponent(Graphics G) {
                super.paintComponent(G);

                Graphics2D g2 = (Graphics2D) G.create();
                g2.scale(zoom, zoom);
                g2.drawImage(image, 0, 0, null);
                g2.dispose();
            }
        };


        JLabel userText = new JLabel("...");
        userText.setPreferredSize(new Dimension(25, 25));

        JTextField textField = new JTextField("", 1);


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


                    zoom *= 1.1;
                    refresh();
                }, KeyStroke.getKeyStroke("ctrl EQUALS"), JComponent.WHEN_IN_FOCUSED_WINDOW);

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

    public void toggleLightMode() {

    }

    public void toggleDarkMode() {

    }

        if (((x >= 0 && x < image.getWidth())) && (y >= 0 && y < image.getHeight())) {
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

        }
        fillCircle(x,y,r);
    }

    public static void fillCircle(double posX, double posY, double r) {

        int radius = (int) MathematicalOperations.ceil(r);

        for (int y = -radius; y <= radius; y++){
            for (int x = -radius; x <= radius; x++){

                if (x*x+y*y<= r*r){
                }
            }
        }
    }

    public static void createCurvedLine(){

    }

    public static void createTriangle(){

    }


    public static void refresh() {
    }

    public static void actionPerformed(JTextField textField, JLabel userInput) {
        String input = textField.getText();
        userInput.setText(input);

    }
}