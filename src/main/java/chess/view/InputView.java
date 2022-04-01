package chess.view;

import static java.lang.System.err;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

import chess.domain.board.position.Positions;
import java.util.List;

import chess.constant.Command;
import chess.domain.board.position.Position;
import chess.dto.MoveRequest;
import chess.dto.NotMoveRequest;
import chess.dto.Request;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Pattern START_END_PATTERN = Pattern.compile("(start)|(end)", CASE_INSENSITIVE);
    private static final Pattern END_MOVE_PATTERN =
            Pattern.compile("(end)|(status)|(move [a-h][1-8] [a-h][1-8])", CASE_INSENSITIVE);
    static final String NOT_SUPPORT_OPERATION_MESSAGE = "[ERROR] 현재 명령은 허용되지 않습니다";
    static final String NOT_SUPPORT_OPERATION_MESSAGE_FORMAT = NOT_SUPPORT_OPERATION_MESSAGE + ": <%s>";

    private InputView() {
    }

    public static Command inputStartCommand() {
        try {
            String input = SCANNER.nextLine();
            validateStartCommand(input);
            return Command.from(input);
        } catch (RuntimeException e) {
            err.println(e.getMessage());
        }
        return inputStartCommand();
    }

    static void validateStartCommand(String input) {
        if (!START_END_PATTERN.matcher(input).matches()) {
            throw new UnsupportedOperationException(String.format(NOT_SUPPORT_OPERATION_MESSAGE_FORMAT, input));
        }
    }

    public static Request inputCommandInGaming() {
        try {
            return getInputCommandInGaming();
        } catch (RuntimeException e) {
            err.println(e.getMessage());
        }
        return inputCommandInGaming();
    }

    private static Request getInputCommandInGaming() {
        String input = SCANNER.nextLine();
        validateCommandInGaming(input);
        Command command = Command.from(input);
        if (command.isEnd() || command.isStatus()) {
            return new NotMoveRequest(command);
        }
        List<String> inputs = List.of(input.split(" "));
        Position source = Positions.findPositionBy(inputs.get(1));
        Position target = Positions.findPositionBy(inputs.get(2));
        return new MoveRequest(command, source, target);
    }

    public static void validateCommandInGaming(String input) {
        if (!END_MOVE_PATTERN.matcher(input).matches()) {
            throw new UnsupportedOperationException(String.format(NOT_SUPPORT_OPERATION_MESSAGE_FORMAT, input));
        }
    }

}
