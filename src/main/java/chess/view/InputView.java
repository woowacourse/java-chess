package chess.view;

import static chess.view.OutputView.print;

import chess.domain.command.CommandType;
import chess.domain.command.Command;
import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    private static final String INVALID_COMMAND_DESCRIPTION_INPUT_EXCEPTION_MESSAGE = "잘못된 값을 입력하였습니다.";

    private static final Scanner scanner = new Scanner(System.in);

    public Command requestValidCommandInput() {
        try {
            return requestCommandInput();
        } catch (IllegalArgumentException e) {
            print(e.getMessage());
        }
        return requestValidCommandInput();
    }

    private Command requestCommandInput() {
        String input = readConsoleInput();
        CommandType validCommandType = Arrays.stream(CommandType.values())
                .filter(type -> type.isValidDescription(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_DESCRIPTION_INPUT_EXCEPTION_MESSAGE));
        return new Command(validCommandType, input);
    }

    private static String readConsoleInput() {
        String input = scanner.nextLine();
        return input.trim();
    }
}
