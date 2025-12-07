package Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/input.txt");
        try  (Scanner input = new Scanner(file)) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}