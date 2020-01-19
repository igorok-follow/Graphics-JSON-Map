package ml.javalearn.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

class Frame extends JFrame {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private Parser parser;
    private HashMap<Integer, Node> getNameUseId;
    private JComboBox<String> from_name, to_name;
    private JPanel interfacePanel;
    private String from_way, to_way;
    private PopupWindow popupWindow;

    Frame(Parser parser) {
        this.parser = parser;
        fillMap();
        init();
        setPanels();
        setComponentsForSetAWay();
    }

    private void fillMap() {
        getNameUseId = new HashMap<>();
        nodes = parser.getNodesList();
        edges = parser.getEdgesList();
        for (Node node : nodes) {
            getNameUseId.put(node.getId(), node);
        }
    }

    private void findShortestWay() {

    }

    private void setComponentsForSetAWay() {
        from_name = new JComboBox<>();
        to_name   = new JComboBox<>();
        for (Node node : nodes) {
            from_name.addItem(node.getName());
            to_name.addItem(node.getName());
        }

        from_name.setBounds(50, 30, 100, 50);
        to_name.setBounds(200, 30, 100, 50);

        JLabel from = new JLabel("From:");
        JLabel to   = new JLabel("To:");

        from.setBounds(10, 30, 50, 50);
        to.setBounds(160, 30, 40, 50);

        JButton acceptWay = new JButton("pave the way");
        acceptWay.setBounds(50, 100, 250, 50);
        acceptWay.addActionListener(e -> {
            from_way = (String) from_name.getSelectedItem();
            to_way   = (String) to_name.getSelectedItem();
            System.out.println("from: " + from_way + " to: " + to_way);

        });

        popupWindow = new PopupWindow(20, 170, 280, 300);

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        exit.setBounds(3, 765, 305, 20);

        interfacePanel.add(from_name);
        interfacePanel.add(to_name);
        interfacePanel.add(from);
        interfacePanel.add(to);
        interfacePanel.add(acceptWay);
        interfacePanel.add(popupWindow);
        interfacePanel.add(exit);
    }

    private void setPanels() {
        JPanel renderPanel = new JPanel() {

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
//                        g.drawString(String.valueOf(edge.getWeight()),
//                                (fromNode.getX() + toNode.getX()) / 2 * 100 + 42, (fromNode.getY() + toNode.getY()) / 2 * 100 + 42);
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
        renderPanel.setPreferredSize(new Dimension(1000, 1000));
        renderPanel.setBounds(0, 0, 1000, 1000);

        interfacePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawLine(0, 0, 0, 1000);
            }
        };
        interfacePanel.setLayout(null);
        interfacePanel.setBounds(1000, 0, 400, 1000);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.add(renderPanel);
        contentPanel.add(interfacePanel);

        getContentPane().add(contentPanel);
    }

    private void createFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JSON Map");
        setSize(1325, 825);
//        setResizable(false);
//        setUndecorated(true);
        setVisible(true);
    }

    private void init() {
        createFrame();
    }


}
