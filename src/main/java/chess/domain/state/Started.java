package chess.domain.state;

import chess.domain.ChessBoard;

public abstract class Started implements State {

    protected final ChessBoard chessBoard;

    protected Started(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public final ChessBoard chessBoard() {
        return chessBoard;
    }
}
