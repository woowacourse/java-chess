package chess.domain.piece;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.chessboard.attribute.Direction;

public class King extends UnslidingPiece {

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public Set<Position> movablePositionsFrom(final Position source) {
        Set<Direction> directions = Direction.all();
        return directions.stream()
                .map(source::moveTo)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
