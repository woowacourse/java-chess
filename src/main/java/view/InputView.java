package view;

import domain.Command;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Command readCommand() {

        final String input = SCANNER.next();

        if ("start".equals(input)) {
            return Command.START;
        }
        if ("end".equals(input)) {
            return Command.END;
        }
        throw new IllegalArgumentException("start 또는 end로 입력바랍니다.");
    }
}
