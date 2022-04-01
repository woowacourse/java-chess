package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Pattern MOVE_PATTERN = Pattern.compile("move [a-h]\\d [a-h]\\d");

    public static String inputCommend() {
        String commend = SCANNER.nextLine();
        validateCommend(commend);
        return commend;
    }

    private static void validateCommend(String commend) {
        if (commend.contains("move") && !MOVE_PATTERN.matcher(commend).matches()) {
            throw new IllegalArgumentException("move 포맷에 맞게 작성해주세요.");
        }
    }
}
