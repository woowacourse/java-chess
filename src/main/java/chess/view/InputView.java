package chess.view;

import static chess.view.Expressions.EXPRESSIONS_COLUMN;
import static chess.view.Expressions.EXPRESSIONS_COMMAND;
import static chess.view.Expressions.EXPRESSIONS_ROW;

import chess.command.Command;
import chess.domain.board.Position;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String COMMAND_MOVE = "move";

    private static final String ERROR_BLANK = "명령어를 입력하세요.";
    private static final String ERROR_NO_SUCH_COMMAND = "실행 가능한 명령이 아닙니다.";
    private static final String ERROR_MOVE_COMMAND_FORMAT = "이동 명령을 형식에 맞게 입력하세요.";
    private static final String ERROR_NO_MORE_POSITION = "더 이상 찾을 수 있는 위치값이 없습니다.";

    private static final String DELIMITER_COMMAND_MOVE = " ";
    private static final String REGEX_POSITION = "[a-h][1-8]";
    private static final String REGEX_COMMAND_MOVE = String.join(
            DELIMITER_COMMAND_MOVE, COMMAND_MOVE, REGEX_POSITION, REGEX_POSITION);

    private static final Pattern PATTERN_POSITION = Pattern.compile(REGEX_POSITION);
    private static final Pattern PATTERN_COMMAND_MOVE = Pattern.compile(REGEX_COMMAND_MOVE);

    private static final int INDEX_COLUMN = 0;
    private static final int INDEX_ROW = 1;

    private static final Scanner SCANNER = new Scanner(System.in);

    public Command readCommand() {
        String rawCommand = SCANNER.nextLine();
        checkBlank(rawCommand);
        return parseCommand(rawCommand);
    }

    private void checkBlank(String rawCommand) {
        if (rawCommand.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK);
        }
    }

    private Command parseCommand(String rawCommand) {
        Supplier<Command> commandSupplier = EXPRESSIONS_COMMAND.get(rawCommand);
        if (commandSupplier != null) {
            return commandSupplier.get();
        }
        if (rawCommand.startsWith(COMMAND_MOVE)) {
            return parseMoveCommand(rawCommand);
        }
        throw new IllegalArgumentException(ERROR_NO_SUCH_COMMAND);
    }

    private Command parseMoveCommand(String rawCommand) {
        Matcher moveCommandMatcher = PATTERN_COMMAND_MOVE.matcher(rawCommand);
        if (!moveCommandMatcher.matches()) {
            throw new IllegalArgumentException(ERROR_MOVE_COMMAND_FORMAT);
        }
        Matcher positionMatcher = PATTERN_POSITION.matcher(rawCommand);
        Position sourcePosition = findPosition(positionMatcher);
        Position targetPosition = findPosition(positionMatcher);

        return Command.createMove(sourcePosition, targetPosition);
    }

    private Position findPosition(Matcher positionMatcher) {
        if (!positionMatcher.find()) {
            throw new IllegalArgumentException(ERROR_NO_MORE_POSITION);
        }
        return parsePosition(positionMatcher.group());
    }

    private Position parsePosition(String rawPosition) {
        return Position.of(EXPRESSIONS_COLUMN.get(rawPosition.charAt(INDEX_COLUMN)),
                EXPRESSIONS_ROW.get(rawPosition.charAt(INDEX_ROW)));
    }
}
