package ml.javalearn.app;

public class Node {

    private int id, x, y;
    private float cost;
    private String name;

    Node(int id, int x, int y, float cost, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.name = name;
    }

    public int getId() { return id; }

    int getX() { return x; }

    int getY() { return y; }

    public float getCost() { return cost; }

    public String getName() { return name; }
}
