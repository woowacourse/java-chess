package chess.view;

import java.util.Scanner;

public class InputView {

    private static final InputView INPUT_VIEW = new InputView();
    private final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public String scanCommand() {
        return SCANNER.nextLine();
    }
}
