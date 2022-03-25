package chess.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class BlackRunning extends Running {

    public BlackRunning(final ChessBoard chessBoard) {
        super(chessBoard, Color.BLACK);
    }

    @Override
    protected final Running otherState(final ChessBoard chessBoard) {
        return new WhiteRunning(chessBoard);
    }
}
