package chess.domain.piece;

import java.util.Map;

import chess.domain.position.Position;

public interface MovingStrategy {

	boolean canMove(Position source, Position target, Map<Position, Piece> boardDto);

	void move(Position source, Position target, Map<Position, Piece> boardDto);
}
