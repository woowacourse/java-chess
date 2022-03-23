package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;

public class Rook extends Piece {

    public final static List<Direction> DIRECTIONS = List.of(Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH);

    public final static List<String> BLACK_INIT_LOCATIONS = List.of("a8", "h8");
    public final static List<String> WHITE_INIT_LOCATIONS = List.of("a1", "h1");

    public Rook(Color color) {
        super(color, PieceName.ROOK);
    }

//    public Map<Direction, List<Position>> getMovablePositions(Position position) {
//
//    }
}
