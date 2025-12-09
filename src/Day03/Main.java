package Day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> file;
        try {
            file = Files.readAllLines(Path.of("src/Day03/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long result = 0;
        for (String bank : file) {
            //result += getJoltage(bank);
            result += getJoltage2(bank, 12);
        }
        System.out.println(result);
    }

    //part 1
    private static int getJoltage(String bank) {
        int maxFirst = 0;
        int maxFirstIndex = 0;
        int maxSecond = 0;
        for  (int i = 0; i < bank.length() - 1; i++) {
            if (bank.charAt(i) - '0' > maxFirst) {
                maxFirst = bank.charAt(i) - '0';
                maxFirstIndex = i;
            }
        }
        for (int i = maxFirstIndex + 1; i < bank.length(); i++) {
            if (bank.charAt(i) - '0' > maxSecond) {
                maxSecond = bank.charAt(i) - '0';
            }
        }
        return maxFirst * 10 + maxSecond;
    }

    //part 2
    private static long getJoltage2(String bank, int numDigits) {
        long result = 0;
        int leftLimit = 0;
        for (int j = numDigits - 1; j >= 0 ; j--) {
            int nextMax = 0;
            for (int i = leftLimit; i < bank.length() - j; i++) {
                if  (bank.charAt(i) - '0' > nextMax) {
                    nextMax = bank.charAt(i) - '0';
                    leftLimit = i + 1;
                }
            }
            result += nextMax * Math.pow(10, j);
        }
        return result;
    }
}
