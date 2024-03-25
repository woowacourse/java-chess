package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        return scanner.next();
    }

    public static List<String> readPositions() {
        return Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .toList();
    }
}
