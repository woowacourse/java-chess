package chess.view;

import java.util.Scanner;

public class InputView {
    private static final String START = "start";
    private static final String END = "end";
    private static final String START_OR_END_EXCEPTION = "[ERROR] start, end 중 하나를 입력해주세요.";
    private static Scanner scanner = new Scanner(System.in);

    public static boolean requestPlay() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        String input = scanner.nextLine();
        return isStartOrEnd(input);
    }

    private static boolean isStartOrEnd(String input) {
        if (isNotStartOrEnd(input)) {
            throw new IllegalArgumentException(START_OR_END_EXCEPTION);
        }
        return START.equals(input);
    }

    private static boolean isNotStartOrEnd(String input) {
        return !START.equals(input) && !END.equals(input);
    }
}
