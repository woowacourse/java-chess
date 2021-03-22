package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);
    private static final String REGEX_SPLIT_REFERENCE = " ";

    public static List<String> command() {
        return Arrays.asList(sc.nextLine().trim().split(REGEX_SPLIT_REFERENCE));
    }

}
