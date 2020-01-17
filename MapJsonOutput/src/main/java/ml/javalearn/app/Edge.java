package ml.javalearn.app;

public class Edge {

    private int from_id, to_id;
    private float weight;

    Edge(int from_id, int to_id, float weight) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.weight = weight;
    }

    public int getFrom_id() { return from_id; }
    public int getTo_id() { return to_id; }
    public float getWeight() { return weight; }

}
