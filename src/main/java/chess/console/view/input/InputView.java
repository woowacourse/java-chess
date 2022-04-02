package chess.console.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import chess.console.domain.CommandRequest;

public class InputView {

    private static final String COMMAND_DELIMITER = " ";
    private static final int LIMIT_FOR_SPLIT_ALL_ELEMENT = -1;

    public CommandRequest requestCommand() {
        final String inputLine = Console.readLine();
        final List<String> inputValues = splitInputLineWithDelimiter(inputLine);
        return convertToCommandRequest(inputValues);
    }

    private List<String> splitInputLineWithDelimiter(final String inputLine) {
        return Arrays.stream(inputLine.split(COMMAND_DELIMITER, LIMIT_FOR_SPLIT_ALL_ELEMENT))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private CommandRequest convertToCommandRequest(final List<String> inputValues) {
        validateInputValuesNotEmpty(inputValues);
        final String command = inputValues.remove(0);
        return new CommandRequest(command, inputValues);
    }

    private static void validateInputValuesNotEmpty(final List<String> inputValues) {
        if (inputValues.isEmpty()) {
            throw new IllegalArgumentException("명령어가 비어있습니다.");
        }
    }

    public String requestPieceInitialToPromotion() {
        final String inputLine = Console.readLine();
        return inputLine.trim();
    }
}
