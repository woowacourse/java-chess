package chess.domain.piece;

import chess.domain.board.Paths;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import java.util.Map;

public interface Piece {

    Paths possibleCoordinates(Coordinate currentCoordinate, Map<Coordinate, Position> boardPositions);

    boolean isColor(PieceColor color);

    boolean isPawn();

    boolean isKing();

    boolean isEmpty();
}
