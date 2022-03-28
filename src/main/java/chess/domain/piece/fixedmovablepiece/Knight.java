package chess.domain.piece.fixedmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Knight extends FixedMovablePiece {

    private final static List<Direction> DIRECTIONS = List.of(
            Direction.SSE, Direction.SSW, Direction.NNE, Direction.NNW,
            Direction.EES, Direction.EEN, Direction.WWS, Direction.WWN);

    public static List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("b8"), Position.of("g8"));
    public static List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("b1"), Position.of("g1"));

    public Knight(Color color) {
        super(color, PieceName.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return 2.5;
    }
}
