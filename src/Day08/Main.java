package Day08;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Set<JBox> jBoxSet = parse();
        PriorityQueue<Edge> pq = generate(jBoxSet);
        System.out.println(getSolution1(pq, 1000));
        //12896 is not correct -> too low
    }

    private static Set<JBox> parse() {
        Set<JBox> set = new HashSet<>();
        List<String> file;
        try {
            file = Files.readAllLines(Paths.get("src/Day08/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String box : file) {
            int[] pos = Arrays.stream(box.split(",")).mapToInt(Integer::parseInt).toArray();
            set.add(new JBox(pos[0], pos[1], pos[2]));
        }
        return set;
    }

    private static int getSolution1(PriorityQueue<Edge> pq, int connections) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < connections; i++) {
            if (pq.isEmpty()) throw new  RuntimeException();
            edges.add(pq.poll());
        }
        UnionByRank ur = new UnionByRank(edges);
        for (Edge e : edges) {
            ur.union(e.first, e.second);
        }
        AtomicInteger result = new AtomicInteger(1);
        ur.getLargestSets(3).stream().mapToInt(Set::size).forEach(i -> result.updateAndGet(v -> v * i));
        return result.get();
    }

    /**
     * @author ChatGPT's correction of me
     */
    private static PriorityQueue<Edge> generate(Set<JBox> jBoxSet) {
        Comparator<Edge> comparator = Comparator.comparingDouble(e -> e.distnace);
        PriorityQueue<Edge> pq = new PriorityQueue<>(comparator);

        List<JBox> list = new ArrayList<>(jBoxSet);  // snapshot

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                pq.add(new Edge(list.get(i), list.get(j)));
            }
        }

        return pq;
    }
}
