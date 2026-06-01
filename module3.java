
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class module3 extends JFrame {

    private JTextArea textBox;
    private JPanel mainPanel;
    private String currentGreen;

    public module3() {

        setTitle("Menu Program (Mod3)");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // smallr box so background can be alteredd whitout affecting visibility of text
        textBox = new JTextArea(5, 25);
        textBox.setFont(new Font("Arial", Font.PLAIN, 14));
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textBox);
        mainPanel.add(scrollPane);

        // green color
        currentGreen = randomGreen();

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item1 = new JMenuItem("print Date and Time");
        JMenuItem item2 = new JMenuItem("Save to log.txt");
        JMenuItem item3 = new JMenuItem("Change Background random green color");
        JMenuItem item4 = new JMenuItem("Exit");

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.addSeparator();
        menu.add(item4);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        // date
        item1.addActionListener(e -> {
            String dateTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            textBox.setText(dateTime);
        });

        // Save to log.txt
        item2.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("log.txt", true)) {
                writer.write(textBox.getText() + "\n");
                JOptionPane.showMessageDialog(this, "Saved to log.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "There seems to be an error, please try again.");
            }
        });

        // Change background to a random green color with every click
        item3.addActionListener(e -> {
            currentGreen = randomGreen();
            Color greenColor = Color.decode(currentGreen);

            // Change background
            getContentPane().setBackground(greenColor);
            mainPanel.setBackground(greenColor);

            // seperate txt box
            textBox.setBackground(Color.WHITE);

            repaint();
        });

        // Exit, end it
        item4.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private String randomGreen() {
        Random random = new Random();

        int green = 100 + random.nextInt(156); // 100–255 (medium to max)
        int red = random.nextInt(green); // 0–(green-1)
        int blue = random.nextInt(green); // 0–(green-1)

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(module3::new);
    }
}
