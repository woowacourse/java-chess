package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class InputView {

    private static final Pattern NUMBER_REGEX = Pattern.compile("^-?[0-9]+$");
    private static final Scanner scanner = new Scanner(System.in);

    public static int readRoomId() {
        String input = scanner.nextLine();
        checkNumber(input);
        checkBigNumber(input);
        return Integer.parseInt(input);
    }

    private static void checkNumber(String input) {
        if (!NUMBER_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    private static void checkBigNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("서비스 규모가 작아서 큰 단위의 게임방은 지원하지 않습니다.");
        }
    }

    public static String readCommand() {
        String input = scanner.nextLine();
        checkBlank(input);
        return input;
    }


    private static void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }
}
