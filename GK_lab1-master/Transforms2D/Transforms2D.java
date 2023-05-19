package Transforms2D;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

    private class Display extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.translate(300,300);  // Moves (0,0) to the center of the display.
            int whichTransform = transformSelect.getSelectedIndex();

            // TODO Apply transforms here, depending on the value of whichTransform!

            switch(whichTransform){
                case 1:
                    g2.scale(0.5, 0.5);
                    break;
                case 2:
                    g2.scale(2, 2);
                    break;
                case 3:
                    g2.scale(2, 0.5);
                    break;
                case 4:
                    g2.rotate(1, 20, 20);
                    break;
                case 5:
                    g2.rotate(0.33, 20, 20); g2.scale(0.3, 0.3);
                    break;
                case 6:
                    g2.rotate(-0.33, 10, 40); g2.scale(1.2, 0.75);
                    break;
                case 7:
                    g2.rotate(Math.PI, 10, 40); g2.scale(1, 1);
                    break;
                case 8:
                    g2.scale(2, 0.75);
                    break;
                case 9:
                    g2.scale(0.75, 2);
                    break;
            }

            double polygonRadius = 150;
            int polygonNumberOfSpikes = 16;
            int[] spikesXCoords = new int[17];
            int[] spikesYCoords = new int[17];

            for (int spike = 0; spike < polygonNumberOfSpikes; spike++) {
                spikesXCoords[spike] = (int) Math.round(polygonRadius * Math.cos(2 * Math.PI * spike / 16));
                spikesYCoords[spike] = (int) Math.round(polygonRadius * Math.sin(2 * Math.PI * spike / 16));
            }

            g.drawPolygon(spikesXCoords, spikesYCoords, polygonNumberOfSpikes);
            g.setColor(Color.RED);
            g.fillPolygon(spikesXCoords, spikesYCoords, polygonNumberOfSpikes);
            Polygon p = new Polygon();
            g.fillPolygon(p);
        }
    }

    private Display display;
    private JComboBox<String> transformSelect;

    public Transforms2D() throws IOException {
        display = new Display();
        display.setBackground(Color.YELLOW);
        display.setPreferredSize(new Dimension(600,600));
        transformSelect = new JComboBox<String>();
        transformSelect.addItem("None");
        for (int i = 1; i < 10; i++) {
            transformSelect.addItem("No. " + i);
        }
        transformSelect.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.repaint();
            }
        });
        setLayout(new BorderLayout(3,3));
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER));
        top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        top.add(new JLabel("Transform: "));
        top.add(transformSelect);
        add(display,BorderLayout.CENTER);
        add(top,BorderLayout.NORTH);
    }


    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("2D Transforms");
        window.setContentPane(new Transforms2D());
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
        window.setVisible(true);
    }

}