package chess.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SPACE = " ";
    private static final String MESSAGE_FOR_INPUT = "명령을 입력해 주세요.";
    private static final String WRONG_INPUT = "잘못된 명령어입니다. 다시 입력해 주세요.";

    private static String getInput() {
        return SCANNER.nextLine().strip();
    }

    private static List<String> getStringList() {
        final String[] rawInput = getInput().split(SPACE);
        return new ArrayList<>(Arrays.asList(rawInput));
    }

    private static List<String> stringList(final String message) {
        System.out.println(message);
        return getStringList();
    }

    private static List<String> stringList(final Exception error) {
        System.err.println(error.getMessage());
        return getStringList();
    }

    public static List<String> commandList() {
        return stringList(MESSAGE_FOR_INPUT);
    }

    public static List<String> retryCommandList() {
        return stringList(new Exception(WRONG_INPUT));
    }
}
