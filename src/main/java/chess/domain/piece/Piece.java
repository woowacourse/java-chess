package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.position.Position;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Piece {

    protected PieceType pieceType;
    protected PieceColor pieceColor;
    protected MoveStrategy moveStrategy;

    protected Piece(PieceType pieceType, PieceColor pieceColor, MoveStrategy moveStrategy) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.moveStrategy = moveStrategy;
    }

    public abstract List<Direction> directions();

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
                    .filter(position -> moveStrategy.canGoFrom(position, currentPosition))
                    .collect(Collectors.toList());
        };
    }

    public boolean isColor(PieceColor color) {
        return pieceColor.equals(color);
    }

    public boolean isEnemy(Piece that) {
        return !isColor(that.pieceColor);
    }

    public String getName() {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return pieceType.toBlack();
        }
        return pieceType.getType();
    }
}
