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

    public List<Path> findAllPath(Position currentPosition) {
        return directions().stream()
                .map(direction -> findPathInDirection(direction, currentPosition))
                .collect(Collectors.toList())
                ;
    }

    private Path findPathInDirection(Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        while(!currentPosition.isBlockedWhenGoTo(direction)){
            positions.add(currentPosition.moveTo(direction));
            currentPosition = currentPosition.moveTo(direction);
        }
        return new Path(positions);
    }

    public boolean isNotMyPiece(Piece that) {
        return that.equals(PieceType.EMPTY) || isEnemyOrEmpty(that);
    }

    public boolean isColor(PieceColor color) {
        return pieceColor.equals(color);
    }

    public boolean isEnemy(Piece that){
        return this.pieceColor.equals(that.pieceColor.reversed());
    }

    public boolean isEnemyOrEmpty(Piece that) {
        return !this.pieceColor.equals(that.pieceColor);
    }

    public String getName() {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return pieceType.toBlack();
        }
        return pieceType.getType();
    }
}
