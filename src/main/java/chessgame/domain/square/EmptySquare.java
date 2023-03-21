package chessgame.domain.square;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.piece.Piece;

import java.util.Optional;

public class EmptySquare extends Square {

    @Override
    public boolean canReap() {
        return false;
    }

    @Override
    public boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return false;
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public Optional<Piece> getPiece() {
        return Optional.empty();
    }

    @Override
    public boolean isSameCamp(Camp camp) {
        return false;
    }
}
