package chess.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String askGameCommand() {
        return SCANNER.nextLine();
    }
}
