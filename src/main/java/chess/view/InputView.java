package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner SCANNER = new Scanner(System.in);
    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public String scanCommand() {
        return SCANNER.nextLine();
    }
}
