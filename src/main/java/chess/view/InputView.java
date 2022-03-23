package chess.view;

import java.util.Locale;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String MESSAGE_ASK_START = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String START = "start";
    private static final String END = "end";
    private static final String ERROR_MESSAGE_START_INPUT = "[ERROR] 지정된 명령어를 사용하세요.";

    public static Boolean isStart() {
        System.out.println(MESSAGE_ASK_START);
        String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        try {
            validateStartInput(answer);
            return START.equals(answer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isStart();
        }
    }

    private static void validateStartInput(String answer) {
        if (!(START.equals(answer) || END.equals(answer))) {
            throw new IllegalArgumentException(ERROR_MESSAGE_START_INPUT);
        }
    }
}
