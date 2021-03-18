package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.PieceDoesNotExistException;

import java.util.List;

public class Blank extends Piece {
    private static final String BLANK_INITIAL = ".";

    public Blank() {
        super(Side.NONE, BLANK_INITIAL);
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        throw new PieceDoesNotExistException();
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        throw new PieceDoesNotExistException();
    }
}
