package chess.domain.piece.movable;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;

import java.util.List;

public interface Movable {
	Positions createMovablePositions(Position position, List<Piece> pieces, Color color);
}
