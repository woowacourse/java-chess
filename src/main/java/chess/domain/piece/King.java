package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    public static String BLACK_INIT_LOCATION = "e8";
    public static String WHITE_INIT_LOCATION = "e1";

    public King(Color color) {
        super(color, PieceName.KING);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return null;
    }
}
