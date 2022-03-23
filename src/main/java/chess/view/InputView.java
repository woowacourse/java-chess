package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startGuideMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("체스 게임을 시작합니다. ")
            .append(System.lineSeparator())
            .append("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        System.out.println(stringBuilder);
    }

    public static String requestGameStartCommand() {
        try {
            String input = scanner.nextLine();
            validateGameStartCommand(input);
            return input;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestGameStartCommand();
        }
    }

    private static void validateGameStartCommand(String input) {
        if (!input.equals("start") && !input.equals("end")) {
            throw new IllegalArgumentException("start 또는 end 를 입력해주세요.");
        }
    }
}
