package chess.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Color;

public class WhiteRunning extends Running {

    public WhiteRunning(ChessBoard chessBoard) {
        super(chessBoard, Color.WHITE);
    }

    @Override
    protected final Running otherState(ChessBoard chessBoard) {
        return new BlackRunning(chessBoard);
    }
}
