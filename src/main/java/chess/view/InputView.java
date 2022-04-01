package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
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
        final String command = SCANNER.nextLine();
        return command.split(COMMAND_DELIMITER);
    }
}
