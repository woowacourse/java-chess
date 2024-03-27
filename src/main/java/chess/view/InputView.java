package chess.view;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String COMMAND_DELIMITER = " ";
    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int START_FILE_RANK_INDEX = 1;
    private static final int TARGET_FILE_RANK_INDEX = 2;
    private static final int RANK_BEGIN_INDEX = 1;
    private static final int FILE_BEGIN_INDEX = 0;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));

    private final Scanner scanner;

    private InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public Command readCommand() {
        String input = scanner.nextLine();
        List<String> command = Arrays.asList(input.split(COMMAND_DELIMITER));
        validateEmpty(command);
        return makeCommand(command);
    }

    private void validateEmpty(List<String> command) {
        if (command.isEmpty()) {
            throw new IllegalArgumentException("커맨드가 입력되지 않았습니다.");
        }
    }

    private Command makeCommand(List<String> command) {
        CommandType commandType = CommandTypeView.find(command.get(COMMAND_TYPE_INDEX));
        if (commandType == CommandType.MOVE) {
            validateMoveCommandSize(command);
            Position startPosition = makePosition(command.get(START_FILE_RANK_INDEX));
            Position targetPosition = makePosition(command.get(TARGET_FILE_RANK_INDEX));
            return new Command(commandType, startPosition, targetPosition);
        }
        return Command.from(commandType);
    }

    private void validateMoveCommandSize(final List<String> command) {
        if (command.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("move 입력 시 startPosition과 targetPosition을 정확히 입력해 주세요.");
        }
    }

    private Position makePosition(String startFileRank) {
        return new Position(
                RankView.find(startFileRank.substring(RANK_BEGIN_INDEX)),
                FileView.find(startFileRank.substring(FILE_BEGIN_INDEX, RANK_BEGIN_INDEX))
        );
    }
}
