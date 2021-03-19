package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
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
                .map(direction -> findPathInDirection(direction, currentPosition))
                .flatMap(List::stream)
//                .distinct()
                .collect(Collectors.toList())
                ;
    }

    private List<Position> findPathInDirection(Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        while(!currentPosition.isBlockedWhenGoToThis(direction)){
            positions.add(currentPosition.moveTo(direction));
            currentPosition = currentPosition.moveTo(direction);
        }
        return positions;
        // if current is boundary stop
        // if not, add current position and move one direction
        // but do not add initial position
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
