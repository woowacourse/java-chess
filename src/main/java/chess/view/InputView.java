package chess.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public void terminate() {
        scanner.close();
    }

    public String getStartOrEndInput(OutputView outputView) {
        try {
            String input = scanner.nextLine();
            validateStartOrEndInput(input);
            return input;
        } catch (IllegalArgumentException e) {
            return getStartOrEndInput(outputView);
        }
    }

    private void validateStartOrEndInput(String input) {
        if (!input.equals("start") && !input.equals("end")) {
            throw new IllegalArgumentException("start 또는 end를 입력해주세요.");
        }
    }
}
