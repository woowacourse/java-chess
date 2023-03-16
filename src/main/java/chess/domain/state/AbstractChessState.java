package chess.domain.state;

import chess.domain.board.ChessBoard;

public abstract class AbstractChessState implements ChessState {

    protected final ChessBoard chessBoard;

    protected AbstractChessState(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
}
