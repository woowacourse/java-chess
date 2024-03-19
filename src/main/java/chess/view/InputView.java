package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean inputCommand() {
        String command = SCANNER.nextLine();
        return Command.isStart(command);
    }

    public static void inputEndCommand() {
        String command = SCANNER.nextLine();
        if (Command.isEnd(command)) {
            return;
        }
        throw new IllegalArgumentException("시작 후 또 시작을 입력할 수 없습니다.");
    }


}
