package chess.command;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class WhiteRunning extends Running {

    public WhiteRunning(final ChessBoard chessBoard) {
        super(chessBoard, Color.WHITE);
    }

    @Override
    protected final Running otherCommand(final ChessBoard chessBoard) {
        return new BlackRunning(chessBoard);
    }
}
