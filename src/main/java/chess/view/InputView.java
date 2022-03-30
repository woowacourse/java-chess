package chess.view;

import chess.dto.RequestDto;
import java.util.Scanner;

public class InputView {

    private static final String EMPTY_SEPARATOR = " ";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static RequestDto toInputOption() {
        String input = SCANNER.nextLine();
        InputOption inputOption = InputOption.from(input);

        if (inputOption == InputOption.MOVE) {
            String[] splits = input.split(EMPTY_SEPARATOR);
            return RequestDto.of(inputOption, splits[1], splits[2]);
        }
        return RequestDto.from(inputOption);
    }

    public static RequestDto inputOption() {
        try {
            return toInputOption();
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return inputOption();
        }
    }
}
