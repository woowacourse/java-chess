package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String CHESS_START_OR_END = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    private InputView() {
    }

    public static boolean inputChessStartOrEnd() {
        try {
            System.out.println(CHESS_START_OR_END);
            final String userInput = scanner.nextLine();
            return validateStartOrEnd(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputChessStartOrEnd();
        }
    }

    private static boolean validateStartOrEnd(final String userInput) {
        if ("start".equals(userInput)) {
            return true;
        }
        if ("end".equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException("허용되지 않은 명령입니다.");
    }
}
