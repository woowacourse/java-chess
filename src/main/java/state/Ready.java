package state;

import domain.board.Board;
import domain.board.InitialChessAlignment;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

public final class Ready implements State {

    @Override
    public void move(final Position source, final Position destination) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPlaying() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Position, Piece> getPieces() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State next() {
        final Board board = Board.create(new InitialChessAlignment());
        return new White(board);
    }
}
