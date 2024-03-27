package chessgame.view;

import java.util.Scanner;

public class InputView {
    private InputView() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static ChessCommand inputChessCommand() {
        System.out.println();
        return ChessCommand.from(scanner.next());
    }

    public static String inputChessPoint() {
        return scanner.next();
    }
}
