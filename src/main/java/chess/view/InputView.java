package chess.view;

import chess.domain.gamestate.Option;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static chess.domain.gamestate.Option.COMMAND_INDEX;

public class InputView {
    private static final String INVALID_OPTION_ERROR = "옵션을 정확하게 입력해주세요.";
    public static final String SPACE = "\\s";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> takeInput() {
        try {
            String input = SCANNER.nextLine();
            List<String> splitInput = Arrays.stream(input.split(SPACE))
                    .map(String::trim)
                    .collect(Collectors.toList());
            validate(splitInput.get(COMMAND_INDEX));
            return splitInput;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return takeInput();
        }
    }

    private static void validate(String input) {
        if (Option.hasNoOption(input)) {
            throw new IllegalArgumentException(INVALID_OPTION_ERROR);
        }
    }
}
