package chess.view;

import java.util.Scanner;

public class InputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    private static final String INPUT_CONTINUE_COMMAND_MESSAGE = "체스 게임을 시작합니다.%n게임 시작은 start, 종료는 end 명령을 입력하세요.%n";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean inputContinueCommand() {
        System.out.printf(INPUT_CONTINUE_COMMAND_MESSAGE);
        return SCANNER.nextLine().equals(START_COMMAND);
    }
}
