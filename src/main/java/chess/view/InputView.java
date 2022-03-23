package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String GAME_START_COMMAND_FORMAT = "게임 시작은 %s, 종료는 %s 명령을 입력하세요.%n";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String START_COMMAND_EXCEPTION_MESSAGE =
            "명령어는 " + START_COMMAND + "와 " + END_COMMAND + "만 입력하실 수 있습니다.";

    public boolean inputStartCommand() {
        System.out.println(GAME_START_MESSAGE);
        System.out.printf(GAME_START_COMMAND_FORMAT, START_COMMAND, END_COMMAND);

        String rawStartCommand = SCANNER.nextLine();
        try {
            return isStarting(rawStartCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputStartCommand();
        }
    }

    private boolean isStarting(String rawStartCommand) {
        if (START_COMMAND.equalsIgnoreCase(rawStartCommand)) {
            return true;
        }
        if (END_COMMAND.equalsIgnoreCase(rawStartCommand)) {
            return false;
        }
        throw new IllegalArgumentException(START_COMMAND_EXCEPTION_MESSAGE);
    }
}
