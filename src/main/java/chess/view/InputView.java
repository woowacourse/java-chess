package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Command askCommand() {
        String input = SCANNER.nextLine();
        return Command.findBy(input);
    }
}
