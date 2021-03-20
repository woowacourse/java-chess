package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.piece.Team;

public class StatusOnCommand implements Command {
    private static final String COMMAND_NAME = "status";
    private static final String COMMAND_MESSAGE = "BLACK 팀의 점수 : %f, WHITE 팀의 점수 : %f %s";
    private static final String BLACK_WIN = "BLACK 팀이 유리한 상황입니다.";
    private static final String WHITE_WIN = "WHITE 팀이 유리한 상황입니다.";
    private static final String DRAW = "동등한 상황입니다.";

    @Override
    public String run(String input, ChessGame chessGame) {
        return compositeFormat(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
    }

    private String compositeFormat(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return String.format(COMMAND_MESSAGE, blackScore, whiteScore, BLACK_WIN);
        }
        if (blackScore < whiteScore) {
            return String.format(COMMAND_MESSAGE, blackScore, whiteScore, WHITE_WIN);
        }
        return String.format(COMMAND_MESSAGE, blackScore, whiteScore, DRAW);
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command);
    }
}
