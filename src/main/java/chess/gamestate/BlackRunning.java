package chess.gamestate;

import static chess.domain.Color.BLACK;

import chess.domain.ChessBoard;

public class BlackRunning extends Running {

    public BlackRunning(ChessBoard chessBoard) {
        super(chessBoard, BLACK);
    }

    @Override
    protected final Running otherState(ChessBoard chessBoard) {
        return new WhiteRunning(chessBoard);
    }
}
