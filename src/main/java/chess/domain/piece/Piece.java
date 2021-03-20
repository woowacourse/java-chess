package chess.domain.piece;

import java.util.List;
import java.util.Map;

public interface Piece {

    String symbol();

    double score();

    boolean isSameColor(Color color);

    Piece move(Position position, Map<Position, Piece> pieces);

    boolean isSameColumn(Point point);
}
