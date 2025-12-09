package Day08;

import java.util.Objects;

public class JBox {
    public final int x;
    public final int y;
    public final int z;

    JBox(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceTo(JBox other) {
        double dx = Math.abs(this.x - other.x);
        double dy = Math.abs(this.y - other.y);
        double dz = Math.abs(this.z - other.z);
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        JBox jBox = (JBox) o;
        return x == jBox.x && y == jBox.y && z == jBox.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
