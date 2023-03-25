package chess.view;

import chess.controller.command.chess.ChessGameCommand;
import chess.controller.command.chess.EndChessGameCommand;
import chess.controller.command.chess.EnterChessGameCommand;
import chess.controller.command.chess.NewChessGameCommand;
import java.util.regex.Pattern;

public class ChessGameCommandFactory {

    private static final String START_PATTERN = "start";
    private static final String ENTER_PATTER = "^enter \\d+";
    private static final String END_PATTER = "end";

    public static ChessGameCommand getInstance(final String input) {
        if (Pattern.matches(START_PATTERN, input)) {
            return new NewChessGameCommand();
        }
        if (Pattern.matches(ENTER_PATTER, input)) {
            return new EnterChessGameCommand(input);
        }
        if (Pattern.matches(END_PATTER, input)) {
            return new EndChessGameCommand();
        }
        throw new IllegalArgumentException("체스 게임에 입장하지 않았거나, 유효하지 않은 게임 명령입니다.");
    }
}
