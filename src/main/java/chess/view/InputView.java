package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean inputCommand() {
        String command = SCANNER.nextLine().toLowerCase();
        if (command.equals("start")) {
            return true;
        }
        if (command.equals("end")) {
            return false;
        }
        throw new IllegalStateException("잘못된 커맨드 입력입니다.");
    }
}
