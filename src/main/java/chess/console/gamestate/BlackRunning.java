package chess.console.gamestate;

import static chess.domain.Color.BLACK;

import chess.domain.ChessBoard;

public final class BlackRunning extends Running {

    public BlackRunning(ChessBoard chessBoard) {
        super(chessBoard, BLACK);
    }

    @Override
    public Running otherState(ChessBoard chessBoard) {
        return new WhiteRunning(chessBoard);
    }
}
