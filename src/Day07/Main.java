package Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/Day07/input.txt");
        try  (Scanner input = new Scanner(file)) { //only one at a time, too lazy to do new scanner
            //countsplits(input);
            System.out.println(countWorlds(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static long[] convertLine(String line) {
        return line.chars().map(c -> {
            if (c == '.') {return 0;}
            if (c == '^') {return -1;}
            return c - '0';
        }).asLongStream().toArray();
    }

    private static long countWorlds(Scanner input) {
        long[] prevLine = convertLine(input.nextLine().replace('|', '1'));
        System.out.println(Arrays.toString(prevLine));
        long[] line = convertLine(input.nextLine());
        System.out.println(Arrays.toString(line));

        while (input.hasNextLine()) {
            for (int i = 0; i < prevLine.length; i++) {
                if (prevLine[i] > 0) {
                    if (line[i] >= 0) {
                        line[i] += prevLine[i];
                    } else if (line[i] == -1) {
                        if (i - 1 > -1 && line[i - 1] >= 0) {
                            line[i - 1] += prevLine[i];
                        }
                        if (i + 1 < line.length && line[i + 1] >= 0) {
                            line[i + 1] += prevLine[i];
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
            System.out.println(Arrays.toString(line));
            prevLine = line;
            line = convertLine(input.nextLine());
        }
        long worlds = 0;
        for (long a : prevLine) {
            if (a > 0) worlds += a;
        }
        return worlds;
    }

    private static void countsplits(Scanner input) {
        int splits = 0;
        char[] prevLine = input.nextLine().toCharArray();
        char[] line = input.nextLine().toCharArray();
        while (input.hasNextLine()) {
            for  (int i = 0; i < prevLine.length; i++) {
                if (prevLine[i] == '|') {
                    switch (line[i]) {
                        case '.':
                            line[i] = '|';
                            break;
                        case '^':
                            if (line[i - 1] != '^') line[i - 1] = '|';
                            if (line[i + 1] != '^') line[i + 1] = '|';
                            splits++;
                            break;
                            default: break;
                    }
                }
            }
            System.out.println(line);
            prevLine = line;
            line = input.nextLine().toCharArray();
        }
        System.out.println(splits);
    }
}