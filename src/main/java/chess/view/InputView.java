package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static String inputCommand() {
        try {
            System.out.print("> ");
            String input = SCANNER.nextLine();
            validateInputCommand(input);
            return input;
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputCommand();
        }
    }

    private static void validateInputCommand(String input) {
        int inputLength = input.split(" ").length;
        if (inputLength != 1 && inputLength != 3) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다.");
        }
    }
}
