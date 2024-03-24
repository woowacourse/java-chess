package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import chess.domain.chessboard.attribute.Direction;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends UnslidingPiece {

    public King(final Color color, Square square) {
        super(color, PieceType.KING, square);
    }

    @Override
    public Set<Square> movableSquaresFrom(final Square source) {
        Set<Direction> directions = Direction.all();
        return directions.stream()
                .map(source::move)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toUnmodifiableSet());
    }
}
