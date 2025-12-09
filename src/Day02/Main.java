package Day02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    //chatgpt for getting the backreference to work (i still dont get it)
    private static final Pattern invalidIDRegex = Pattern.compile("^(\\d+)\\1+$");
    public static void main(String[] args) {
        String input;
        try {
            input = Files.readString(Path.of("src/Day02/input.txt"));
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
        String[] ranges = input.split(",");
        List<Long> nums = new ArrayList<>();
        for  (String r : ranges) {
            String[] bounds =  r.split("-");
            for (long i = Long.parseLong(bounds[0]); i <= Long.parseLong(bounds[1]); i++) {
                nums.add(i);
            }
        }
        long result = 0;
        for (long x : nums) {
            if (isRepetition2(String.valueOf(x))) result += x;
        }
        System.out.println(result);
    }

    //part 1
    private static boolean isRepetition(String input) {
        if (input == null || input.isEmpty()) return false;
        return input.substring(0, input.length()/2).equals(input.substring(input.length()/2));
    }

    //part 2
    private static boolean isRepetition2(String input) {
        if (input == null || input.isEmpty()) return false;
        return invalidIDRegex.matcher(input).matches();
    }
}
