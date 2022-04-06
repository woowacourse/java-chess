package chess.domain;

import java.util.List;
import chess.domain.board.Direction;
import chess.domain.board.Position;

public class Blank extends Piece {

    public Blank(Position position) {
        super(PieceType.NO_PIECE, PieceColor.NONE, position);
    }

    @Override
    protected Direction findByDirection(Position from, Position to) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected List<Direction> findByMovableDirection(Piece piece, Direction direction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
