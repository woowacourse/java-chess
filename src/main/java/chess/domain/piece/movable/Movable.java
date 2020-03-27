package chess.domain.piece.movable;

import chess.domain.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Set;

public interface Movable {
	Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color);
}
