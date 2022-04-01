package chess.domain.piece.movestrategy;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public interface MoveStrategy {

	boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to);
}
