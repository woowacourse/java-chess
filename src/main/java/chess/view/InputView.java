package chess.view;

import chess.domain.board.Position;
import chess.command.Command;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    static final String COMMAND_START = "start";
    static final String COMMAND_END = "end";
    private static final String COMMAND_STATUS = "status";
    private static final String COMMAND_MOVE = "move";

    private static final String ERROR_BLANK = "명령어를 입력하세요.";
    private static final String ERROR_NO_SUCH_COMMAND = "실행 가능한 명령이 아닙니다.";
    private static final String ERROR_MOVE_COMMAND_FORMAT = "이동 명령을 형식에 맞게 입력하세요.";

    private static final String DELIMITER_COMMAND_MOVE = " ";
    private static final String REGEX_POSITION = "[a-h][1-8]";
    private static final String REGEX_COMMAND_MOVE = String.join(
            DELIMITER_COMMAND_MOVE, COMMAND_MOVE, REGEX_POSITION, REGEX_POSITION);

    private static final Pattern PATTERN_POSITION = Pattern.compile(REGEX_POSITION);
    private static final Pattern PATTERN_COMMAND_MOVE = Pattern.compile(REGEX_COMMAND_MOVE);

    private static final Scanner SCANNER = new Scanner(System.in);


    public String inputCommand() {
        return SCANNER.nextLine();
    }

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
        if (COMMAND_START.equals(rawCommand)) {
            return Command.createStart();
        }
        if (COMMAND_END.equals(rawCommand)) {
            return Command.createEnd();
        }
        if (COMMAND_STATUS.equals(rawCommand)) {
            return Command.createStatus();
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
        String rawSourcePosition = findPosition(positionMatcher);
        String rawTargetPosition = findPosition(positionMatcher);

        return Command.createMove(Position.from(rawSourcePosition), Position.from(rawTargetPosition));
    }

    private String findPosition(Matcher positionMatcher) {
        positionMatcher.find();
        return positionMatcher.group();
    }
}
