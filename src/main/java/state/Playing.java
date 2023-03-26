package state;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Map;

abstract class Playing implements State {
    protected final Board board;

    public Playing(final Board board) {
        this.board = board;
    }

    protected abstract void validateTurn(final Position source);

    protected abstract State getNextTurn();

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isPlaying() {
        return true;
    }

    @Override
    public void move(final Position source, final Position destination) {
        validateTurn(source);
        board.move(source, destination);
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    @Override
    public State next() {
        return getNextTurn();
    }
}
