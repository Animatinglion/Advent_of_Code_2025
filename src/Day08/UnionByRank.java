package Day08;

import java.util.*;

/**
 * @author ChatGPT
 */
public class UnionByRank {

    private final Map<JBox, JBox> parent = new HashMap<>();
    private final Map<JBox, Integer> rank = new HashMap<>();

    /**
     * Initializes the Union-Find structure from a set of edges.
     * Only JBoxes are unioned; edges only provide the elements.
     */
    public UnionByRank(Collection<Edge> edges) {
        Set<JBox> boxes = new HashSet<>();
        for (Edge e : edges) {
            boxes.add(e.first);
            boxes.add(e.second);
        }

        for (JBox box : boxes) {
            parent.put(box, box);
            rank.put(box, 0);
        }
    }

    /** Find with path compression */
    public JBox find(JBox box) {
        if (parent.get(box) != box) {
            parent.put(box, find(parent.get(box))); // path compression
        }
        return parent.get(box);
    }

    /** Union by rank */
    public void union(JBox a, JBox b) {
        JBox rootA = find(a);
        JBox rootB = find(b);

        if (rootA == rootB) return;

        int rankA = rank.get(rootA);
        int rankB = rank.get(rootB);

        if (rankA < rankB) {
            parent.put(rootA, rootB);
        } else if (rankA > rankB) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rankA + 1);
        }
    }

    /** Returns true if two JBoxes are in the same set */
    public boolean connected(JBox a, JBox b) {
        return find(a).equals(find(b));
    }

    /**
     * Retrieves the entire union (set) containing the given JBox.
     * This is intentionally slow: O(n) scan over all parents.
     */
    public Set<JBox> getSet(JBox box) {
        JBox root = find(box);

        Set<JBox> result = new HashSet<>();
        for (JBox b : parent.keySet()) {
            if (find(b).equals(root)) {
                result.add(b);
            }
        }
        return result;
    }

    /**
     * Returns the k largest sets (connected components) in descending size order.
     * For your case, call getLargestSets(3).
     */
    public List<Set<JBox>> getLargestSets(int k) {
        // Map root -> all JBoxes in its component
        Map<JBox, Set<JBox>> components = new HashMap<>();

        // Group by root
        for (JBox box : parent.keySet()) {
            JBox root = find(box);
            components.computeIfAbsent(root, r -> new HashSet<>()).add(box);
        }

        // Convert to list of sets and sort by size descending
        List<Set<JBox>> sorted = new ArrayList<>(components.values());
        sorted.sort((a, b) -> Integer.compare(b.size(), a.size()));

        // Return top k (or fewer)
        if (sorted.size() <= k) {
            return sorted;
        }
        return sorted.subList(0, k);
    }
}
