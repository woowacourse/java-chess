package chess.domain.piece;

import java.util.List;
import java.util.Map;

public interface Piece {

    String symbol();

    double score();

    boolean isSameColor(final Color color);

    boolean isSameColor(final Piece piece);

    Piece move(final Position position, final Map<Position, Piece> pieces);

    boolean isSameColumn(final Point point);

    boolean isPawn();

    boolean isKing();

    List<Position> movablePositions(Map<Position, Piece> pieces);
}
