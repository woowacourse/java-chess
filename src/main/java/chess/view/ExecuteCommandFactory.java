package chess.view;

import chess.controller.command.execute.EndCommand;
import chess.controller.command.execute.ExecuteCommand;
import chess.controller.command.execute.MoveCommand;
import chess.controller.command.execute.StatusCommand;
import java.util.regex.Pattern;

public class ExecuteCommandFactory {

    private static final String STATUS_PATTERN = "status";
    private static final String MOVE_PATTERN = "^move [a-h][1-8]\\s[a-h][1-8]$";
    private static final String END_PATTERN = "end";

    public static ExecuteCommand getInstance(final String input) {
        if (Pattern.matches(STATUS_PATTERN, input)) {
            return new StatusCommand();
        }
        if (Pattern.matches(MOVE_PATTERN, input)) {
            return new MoveCommand(input);
        }
        if (Pattern.matches(END_PATTERN, input)) {
            return new EndCommand();
        }
        throw new IllegalArgumentException("유효하지 않은 게임 명령입니다.");
    }
}
