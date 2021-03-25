package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;
import chess.exception.PieceDoesNotExistException;
import java.util.List;

public final class Blank implements Piece {

    private static final String INITIAL = ".";
    private static final Blank CACHED_BLANK = new Blank();

    public static Blank getBlank() {
        return CACHED_BLANK;
    }

    @Override
    public List<Position> route(Position from, Position to, Piece targetPiece,
            Side playerSide) {
        throw new PieceDoesNotExistException();
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        throw new PieceDoesNotExistException();
    }

    @Override
    public void moved() {
        throw new PieceDoesNotExistException();
    }

    @Override
    public boolean isSideEqualTo(Side side) {
        return side == Side.NONE;
    }

    @Override
    public boolean diagonal(Position from, Position to) {
        throw new InvalidMethodCallException();
    }

    @Override
    public boolean forward(Position from, Position to) {
        throw new InvalidMethodCallException();
    }

    @Override
    public String getInitial() {
        return INITIAL;
    }
}
