package chess.view;

import java.util.Scanner;

public class InputView {
    private static final String SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);
    public static String inputCommend() {
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
