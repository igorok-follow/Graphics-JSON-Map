package ml.javalearn.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Parser {

    private JSONArray nodes, edges;
    private ArrayList<Node> nodesList = new ArrayList<>();
    private ArrayList<Edge> edgesList = new ArrayList<>();

    Parser() throws IOException, ParseException {
        readJson();
    }

    private void readJson() throws IOException, ParseException {
        File jsonFile = new File("graph_1.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsObject = (JSONObject) jsonParser.parse(new FileReader(jsonFile));
        nodes = (JSONArray) jsObject.get("nodes");
        edges = (JSONArray) jsObject.get("edges");

        setNodes();
        setEdges();

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            new Frame(this);
        });
    }

    private void setNodes() {
        for (Object o : nodes) {
            JSONObject jsonObject = (JSONObject) o;
            int id = Integer.parseInt(jsonObject.get("id").toString());
            int x = Integer.parseInt(jsonObject.get("x").toString());
            int y = Integer.parseInt(jsonObject.get("y").toString());
            float cost = Float.parseFloat(jsonObject.get("cost").toString());
            String name = (String) jsonObject.get("name");
            Node node = new Node(id, x, y, cost, name);
            nodesList.add(node);
        }
    }

    private void setEdges() {
        for (Object o : edges) {
            JSONObject jsonObject = (JSONObject) o;
            int from_id = Integer.parseInt(jsonObject.get("from_id").toString());
            int to_id = Integer.parseInt(jsonObject.get("to_id").toString());
            float weight = Float.parseFloat(jsonObject.get("weight").toString());
            Edge edge = new Edge(from_id, to_id, weight);
            edgesList.add(edge);
        }
    }

    ArrayList<Node> getNodesList() { return nodesList; }
    ArrayList<Edge> getEdgesList() { return edgesList; }
}
