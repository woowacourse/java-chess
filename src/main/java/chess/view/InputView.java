package chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Command requestGameCommand() {
        return Command.findByAnswer(scanner.nextLine());
    }

}
