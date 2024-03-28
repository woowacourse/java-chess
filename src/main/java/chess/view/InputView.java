package chess.view;

import chess.command.Command;
import chess.command.CommandType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final InputView INSTANCE = new InputView(new Scanner(System.in));
    private static final Map<String, CommandType> COMMANDS = Map.of(
            "start", CommandType.START,
            "end", CommandType.END,
            "move", CommandType.MOVE
    );
    private static final String COMMAND_DELIMITER = " ";
    private static final int COMMAND_TYPE_INDEX = 0;

    private final Scanner scanner;

    private InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public Command readCommand() {
        String input = scanner.nextLine();
        List<String> commandTypeAndArgs = Arrays.asList(input.split(COMMAND_DELIMITER));
        CommandType commandType = makeCommandType(commandTypeAndArgs);

        if (commandType == CommandType.MOVE) {
            List<String> argsText = commandTypeAndArgs.subList(COMMAND_TYPE_INDEX + 1, commandTypeAndArgs.size());
            return new Command(commandType, argsText);
        }

        return Command.createNoArgCommand(commandType);
    }

    private CommandType makeCommandType(List<String> splittedCommand) {
        String commandTypeText = splittedCommand.get(COMMAND_TYPE_INDEX);
        validateExistCommand(commandTypeText);
        return COMMANDS.get(commandTypeText);
    }

    private void validateExistCommand(String commandText) {
        if (!COMMANDS.containsKey(commandText)) {
            throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
        }
    }
}
