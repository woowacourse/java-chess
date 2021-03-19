package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.RookStrategy;
import chess.domain.position.Position;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Rook extends Piece {

    public Rook(PieceColor pieceColor) {
        super(PieceType.ROOK, pieceColor, new RookStrategy());
    }

    public List<Direction> directions(){
        return Direction.straightDirection();
    }

    public List<Position> findAllPath(Position currentPosition) {
        return directions().stream()
                .map(findPathInDirection(currentPosition))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList())
                ;
    }

    private Function<Direction, List<Position>> findPathInDirection(Position currentPosition) {
        return direction -> {
            return Positions.POSITION_CACHE.stream()
                    .filter(currentPosition::isStraight)
                    .collect(Collectors.toList());
        };
    }
}
