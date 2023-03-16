package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String NEXT_LINE = System.lineSeparator();
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String readCommand() {
        return scanner.nextLine();
    }

    public static List<String> readMoveCommand() {
        System.out.println(NEXT_LINE + "이동할 위치를 입력해주세요.");
        final String moveCommand = scanner.nextLine();

        return Arrays.stream(moveCommand.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }
}
