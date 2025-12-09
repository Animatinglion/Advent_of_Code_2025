package Day08;

public class Edge {
    public final double distnace;
    public final JBox first;
    public final JBox second;

    public Edge(JBox first, JBox second) {
        this.first = first;
        this.second = second;
        this.distnace = getDistance(first, second);
    }

    private static double getDistance(JBox first, JBox second) {
        int dx = Math.abs(first.x - second.x);
        int dy = Math.abs(first.y - second.y);
        int dz = Math.abs(first.z - second.z);
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
