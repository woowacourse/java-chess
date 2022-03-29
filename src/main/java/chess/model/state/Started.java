package chess.model.state;

import chess.model.board.Board;
import chess.model.position.Position;
import chess.model.piece.Piece;

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
