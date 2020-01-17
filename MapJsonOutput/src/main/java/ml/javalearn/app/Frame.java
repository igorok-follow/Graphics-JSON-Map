package ml.javalearn.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Frame extends JFrame {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private Parser parser;

    Frame(Parser parser) {
        this.parser = parser;
        init();
        setPanel();
    }

    private void setPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                nodes = parser.getNodesList();
                edges = parser.getEdgesList();
                for (Node node : nodes) {
                    g.drawOval(node.getX() * 100, node.getY() * 100, 25, 25);
                }
            }
        };

        getContentPane().add(panel);
    }

    private void createFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JSON Map");
        setSize(1000, 825);
        setVisible(true);
    }

    private void init() {
        createFrame();
    }


}
