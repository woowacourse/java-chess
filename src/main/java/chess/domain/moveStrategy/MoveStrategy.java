package chess.domain.moveStrategy;

import chess.domain.location.Position;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public interface MoveStrategy {
    List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition);
}
