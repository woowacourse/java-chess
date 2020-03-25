package chess.domain.piece.movable;

import chess.domain.board.position.Position;

import java.util.Set;

public interface Movable {
	Set<Position> move(Position position);
}
