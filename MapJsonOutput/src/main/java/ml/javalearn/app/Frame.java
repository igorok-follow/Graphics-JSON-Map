package ml.javalearn.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class Frame extends JFrame {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private Parser parser;
    private HashMap<Integer, Node> getNameUseId;

    Frame(Parser parser) {
        this.parser = parser;
        fillMap();
        init();
        setPanel();
    }

    private void fillMap() {
        getNameUseId = new HashMap<>();
        nodes = parser.getNodesList();
        edges = parser.getEdgesList();
        for (Node node : nodes) {
            getNameUseId.put(node.getId(), node);
        }
    }

    private void setPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int from_id, to_id;
                for (Edge edge : edges) {
                    try {
                        from_id = edge.getFrom_id();
                        to_id = edge.getTo_id();
                        Node fromNode = getNameUseId.get(from_id);
                        Node toNode = getNameUseId.get(to_id);
                        g.drawLine(fromNode.getX() * 100 + 42, fromNode.getY() * 100 + 42,
                                toNode.getX() * 100 + 42, toNode.getY() * 100 + 42);
                    } catch (Exception ex) {
                        System.out.println("Not found some edges");
                    }
                }

                for (Node node : nodes) {
                    g.setColor(new Color(0x3E3E3E));
                    g.fillOval(node.getX() * 100 + 30, node.getY() * 100 + 30, 25, 25);
                    g.setColor(new Color(0x007C7B));
                    g.drawString(node.getName(), node.getX() * 100 + 20, node.getY() * 100 + 20);
                }

            }
        };

        getContentPane().add(panel);
    }

    private void createFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JSON Map");
        setSize(1100, 825);
        setVisible(true);
    }

    private void init() {
        createFrame();
    }


}
