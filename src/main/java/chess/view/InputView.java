package chess.view;

import chess.game.GameCommand;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static GameCommand readCommand() {
        String input = scanner.nextLine();
        return GameCommand.of(input);
    }

    public static String readWinnerName() {
        System.out.println("승자의 이름을 입력해주세요.");
        return scanner.nextLine();
    }

    public static String readLoserName() {
        System.out.println("패자의 이름을 입력해주세요.");
        return scanner.nextLine();
    }
}
