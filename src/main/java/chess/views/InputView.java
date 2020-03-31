package chess.views;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static InputDto getCommand() {
        String string = scanner.nextLine();
        return new InputDto(string);
    }
}
