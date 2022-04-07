package chess.consoleview;

import chess.dto.request.ConsoleCommandDto;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String COMMAND_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int ARGUMENT_START_INDEX = 1;

    public static ConsoleCommandDto inputCommand() {
        List<String> splitCommand = Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));

        return ConsoleCommandDto.from(
                splitCommand.get(COMMAND_INDEX),
                splitCommand.subList(ARGUMENT_START_INDEX, splitCommand.size())
        );
    }
}
