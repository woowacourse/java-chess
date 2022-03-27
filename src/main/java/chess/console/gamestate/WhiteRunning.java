package chess.console.gamestate;

import static chess.domain.Color.WHITE;

import chess.domain.ChessBoard;

public final class WhiteRunning extends Running {

    public WhiteRunning(ChessBoard chessBoard) {
        super(chessBoard, WHITE);
    }

    @Override
    public Running otherState(ChessBoard chessBoard) {
        return new BlackRunning(chessBoard);
    }
}
