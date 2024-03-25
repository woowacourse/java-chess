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

    public Command readStartCommand() {
        String input = scanner.nextLine();
        validateStartCommand(input);
        return Command.createNoArgCommand(COMMANDS.get(input));
    }

    private void validateStartCommand(String input) {
        List<String> commandTypeAndArgs = Arrays.asList(input.split(COMMAND_DELIMITER));
        String commandText = commandTypeAndArgs.get(0);

        validateExistCommand(commandText);
        CommandType commandType = COMMANDS.get(commandText);
        if (commandType != CommandType.START && commandType != CommandType.END) {
            throw new IllegalArgumentException("시작 커맨드는 start 또는 end만 가능합니다.");
        }
    }

    private void validateExistCommand(String commandText) {
        if (!COMMANDS.containsKey(commandText)) {
            throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
        }
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
        CommandType commandType = COMMANDS.get(commandTypeText);
        validateNotStart(commandType);
        return commandType;
    }

    // TODO: 해당 로직 컨트롤러로 이동
    private void validateNotStart(CommandType commandType) {
        if (commandType == CommandType.START) {
            throw new IllegalArgumentException("게임이 이미 진행중입니다.");
        }
    }
}
