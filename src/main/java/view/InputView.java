package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public StartOrEndCommand enterStartOrEnd() {
        return StartOrEndCommand.from(scanner.nextLine());
    }
}
