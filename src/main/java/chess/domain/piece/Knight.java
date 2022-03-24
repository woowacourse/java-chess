package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    public static List<String> BLACK_INIT_LOCATIONS = List.of("b8", "g8");
    public static List<String> WHITE_INIT_LOCATIONS = List.of("b1", "g1");

    public Knight(Color color) {
        super(color, PieceName.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return null;
    }
}
