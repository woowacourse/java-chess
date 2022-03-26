package chess.gamestate;

import static chess.domain.Color.WHITE;

import chess.domain.ChessBoard;

public class WhiteRunning extends Running {

    public WhiteRunning(ChessBoard chessBoard) {
        super(chessBoard, WHITE);
    }

    @Override
    protected final Running otherState(ChessBoard chessBoard) {
        return new BlackRunning(chessBoard);
    }
}
