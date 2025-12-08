package Day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(countIntermideate0s(parse(Files.readAllLines(Paths.get("src/Day01/input.txt")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] parse(List<String> file) throws IOException {
        return file.stream().
                map(s -> s.replace('R','0').replace('L', '-'))
                .flatMapToInt(s -> IntStream.of(Integer.parseInt(s))).toArray();
    }

    //part1
    private static int count0s(int[] rotations) {
        int count = 0;
        int dial = 50;
        for (int a : rotations) {
            dial = (dial + a) % 100;
            if (dial == 0) count++;
        }
        return count;
    }

    //part 2 wiOFGwioezgfIWEGUHweg
    private static int countIntermideate0s(int[] rotations) {
        int count = 0;
        int dial = 50;
        for (int a : rotations) {
            dial = dial + a;
            if (dial == 0) count++;
            else if (dial > 99) {
                count += dial / 100;
                dial = dial % 100;
            }
            else if (dial < 0) {
                count += Math.abs(dial / 100);
                dial = dial % 100;
                if (dial != a) count++;
                dial = 100 + dial;
            }
        }
        return count;
    }

    //works
    private static int GPTcountIntermediate0s(int[] rotations) {
        int count = 0;
        int dial = 50;

        for (int a : rotations) {
            int steps = Math.abs(a);

            // compute distance to first time we would land on 0 in this direction
            int first;
            if (a > 0) {
                first = (100 - dial) % 100;
                if (first == 0) first = 100;
            } else if (a < 0) {
                first = dial % 100;
                if (first == 0) first = 100;
            } else {
                first = Integer.MAX_VALUE; // no movement
            }

            if (steps >= first) {
                count += 1 + (steps - first) / 100;
            }

            // update dial safely (java % can be negative)
            dial = (dial + a) % 100;
            if (dial < 0) dial += 100;
        }

        return count;
    }
}
