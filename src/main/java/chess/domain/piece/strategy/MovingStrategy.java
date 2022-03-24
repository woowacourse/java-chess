package chess.domain.piece.strategy;

import chess.domain.Position;

public interface MovingStrategy {

	boolean check(Position from, Position to);

}
