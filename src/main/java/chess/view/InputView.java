package chess.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputGameCommand() {
        String input = scanner.nextLine();
        validateGameCommandInput(input);
        return input;
    }

    private void validateGameCommandInput(final String input) {
        if (!input.equals("start") && !input.equals("end")) {
            throw new IllegalArgumentException("[ERROR] 게임 커맨드는 \"start\" 또는 \"end\"여야 합니다.");
        }
    }
}
