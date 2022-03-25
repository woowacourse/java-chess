package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public abstract class Ready implements State {

    protected final Board board;

    protected Ready(Board board) {
        this.board = board;
    }

    public static State start(Board board) {
        return new WhiteTurn(board);
    }

    @Override
    public final Map<Position, Piece> getBoard() {
        return board.getValue();
    }
}
