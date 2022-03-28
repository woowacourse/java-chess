package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MOVE_COMMAND_REGEX = "^move [a-z][0-9] [a-z][0-9]$";
    private static final Pattern PATTERN = Pattern.compile(MOVE_COMMAND_REGEX);
    private static final String COMMAND_DELIMITER = " ";

    public static void requestStartCommand() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");

        try {
            final String command = SCANNER.nextLine();
            validateStartCommand(command);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            requestStartCommand();
        }
    }

    private static void validateStartCommand(final String command) {
        final boolean isStart = command.equals("start");

        if (!isStart) {
            throw new IllegalArgumentException("게임 시작은 start 만 입력할 수 있습니다.");
        }
    }

    public static String[] requestGameCommand() {
        try {
            final String command = SCANNER.nextLine();
            validateGameCommand(command);
            return command.split(COMMAND_DELIMITER);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return requestGameCommand();
        }
    }

    private static void validateGameCommand(final String command) {
        final boolean isMove = PATTERN.matcher(command).matches();
        final boolean isStatus = command.equals("status");
        final boolean isEnd = command.equals("end");

        if (!isMove && !isStatus && !isEnd) {
            throw new IllegalArgumentException("올바르지 않은 명령어를 입력하셨습니다.");
        }
    }
}
