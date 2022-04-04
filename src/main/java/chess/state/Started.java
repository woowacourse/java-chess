package chess.state;

import chess.chessboard.Board;
import chess.chessboard.position.Position;
import chess.piece.Piece;

import java.util.Map;

public abstract class Started implements State {

    protected final Board board;

    protected Started(final Board board) {
        this.board = board;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
