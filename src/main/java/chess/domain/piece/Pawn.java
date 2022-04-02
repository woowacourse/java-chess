package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static final Row BLACK_INIT_ROW = Row.SEVEN;
    public static final Row WHITE_INIT_ROW = Row.TWO;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        Map<Direction, List<Position>> movable = new EnumMap<>(Direction.class);
        movable.put(Direction.pawnDirection(color), new ArrayList<>());
        putMovablePositions(movable, position);
        return movable;
    }

    private void putMovablePositions(Map<Direction, List<Position>> movable, Position position) {
        Position nextPosition = position.toDirection(Direction.pawnDirection(color));
        if (!hasMovablePosition(position, nextPosition)) {
            return;
        }
        movable.get(Direction.pawnDirection(color)).add(nextPosition);
        if (isFirstMove(position)) {
            putMovablePositions(movable, nextPosition);
        }
    }

    private boolean hasMovablePosition(Position position, Position nextPosition) {
        return nextPosition != position;
    }

    private boolean isFirstMove(Position position) {
        return isBlackFirstMovePawn(position) || isWhiteFirstMovePawn(position);
    }

    private boolean isBlackFirstMovePawn(Position position) {
        return color.isBlack() && position.isInRow(BLACK_INIT_ROW);
    }

    private boolean isWhiteFirstMovePawn(Position position) {
        return color.isWhite() && position.isInRow(WHITE_INIT_ROW);
    }
}
