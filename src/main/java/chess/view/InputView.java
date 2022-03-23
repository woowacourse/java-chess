package chess.view;

import java.util.Scanner;

public class InputView {
    private static final String SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMEND_MESSAGE = "체스 게임을 시작합니다." + SEPARATOR
            + "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public static String inputCommend() {
        System.out.println(COMMEND_MESSAGE);
        String commend = SCANNER.nextLine();
        validateCommend(commend);
        return commend;
    }

    private static void validateCommend(String commend) {
        if (!commend.equals("start") && !commend.equals("end")) {
            throw new IllegalArgumentException("start 혹은 end를 입력해주세요.");
        }
    }
}
