package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;
import chess.exception.PieceDoesNotExistException;
import java.util.List;

public class Blank extends Piece {

    private static final String INITIAL = ".";
    private static final Blank CACHED_BLANK = new Blank();

    public Blank() {
        super(Side.NONE, INITIAL);
    }

    public static Blank getBlank() {
        return CACHED_BLANK;
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        throw new PieceDoesNotExistException();
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
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
    public boolean diagonal(Position from, Position to) {
        throw new InvalidMethodCallException();
    }

    @Override
    public boolean forward(Position from, Position to) {
        throw new InvalidMethodCallException();
    }
}
