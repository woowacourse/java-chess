package chess.domain.command;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;

public class Status implements Command {
    @Override
    public Command move(ChessBoard chessBoard, Color turn) {
        throw new UnsupportedOperationException(NOT_EXECUTE_ERROR);
    }

    @Override
    public Command changeWaiting() {
        return new Waiting();
    }

    @Override
    public Command changeRunningCommand(String input) {
        throw new UnsupportedOperationException(NOT_EXECUTE_ERROR);
    }
}
