package chess.view;

import chess.domain.util.Run;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Run inputStart() {
        OutputView.printInputStartGuideMessage();
        try {
            return Run.of(SCANNER.nextLine());
        } catch(IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputStart();
        }
    }
}
