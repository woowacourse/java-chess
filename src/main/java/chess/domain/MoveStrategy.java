package chess.domain;

public interface MoveStrategy {
	boolean isNotMovableTo(Position start, Position destination);
	boolean isSameColorIn(Position position);
}
