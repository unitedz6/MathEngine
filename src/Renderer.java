import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.nio.Buffer;

public class Renderer {

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

        mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics G) {
                super.paintComponent(G);
                G.drawImage(image, 0, 0, null);
            }
        };

        mainPanel.setBackground(Color.BLACK);

        JLabel userText = new JLabel("...");
        userText.setPreferredSize(new Dimension(25, 25));

        JTextField textField = new JTextField("", 1);


        mainFrame.add(mainPanel);
        mainFrame.add(userText, BorderLayout.NORTH);
        mainFrame.add(textField, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

        textField.addActionListener(e -> {
            actionPerformed(textField, userText);

        });
        drawCartesianPlane();

    }


    public void toggleLightMode() {

    }

    public void toggleDarkMode() {

    }

    public static void generatePixel(int x, int y, Color color) {
        if (((x >= 0 && x < image.getWidth())) && (y >= 0 && y < image.getHeight())) {
            image.setRGB(x, y, color.getRGB());
            refresh();
        }
    }

    public static void drawCartesianPlane(){

        // create x-axis
        for (int x = (PROGRAM_WIDTH / 4); x <= (PROGRAM_WIDTH * 3/4); x++) {
            generatePixel(x, (PROGRAM_HEIGHT)/2, Color.blue);
        }
        // create x-dividers
        int spacing = 25;
        for (int x = (PROGRAM_WIDTH / 4); x <= (PROGRAM_WIDTH * 3/4); x+=spacing){
            for (int y = PROGRAM_HEIGHT/2 - 5; y <= PROGRAM_HEIGHT/2 + 5; y++){
                generatePixel(x, y, Color.WHITE);
            }
        }

        // create y-axis
        for (int y = (PROGRAM_HEIGHT / 4); y <= PROGRAM_HEIGHT * 3/4; y++) {
            generatePixel((PROGRAM_WIDTH)/2, y, Color.red);
        }
        // create y-dividers
        for (int y = ((PROGRAM_HEIGHT) / 4); y <= (PROGRAM_HEIGHT * 3/4); y+=spacing){
            for (int x = PROGRAM_WIDTH/2 - 5; x <= PROGRAM_WIDTH/2+5; x++){
                generatePixel(x, y, Color.white);
            }
        }
    }


    public static void refresh() {
        mainPanel.repaint();
    }

    public static void actionPerformed(JTextField textField, JLabel userInput) {
        String input = textField.getText();
        userInput.setText(input);

    }
}