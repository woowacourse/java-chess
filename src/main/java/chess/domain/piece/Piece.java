package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
	protected Position position;
	protected final Team team;
	protected final MoveStrategy moveStrategy;

	public Piece(Position position, Team team, MoveStrategy moveStrategy) {
		this.position = position;
		this.team = team;
		this.moveStrategy = moveStrategy;
	}

	public void moveTo(Position destination) {
		if (moveStrategy.isNotMovableTo(position, destination)) {
			throw new IllegalArgumentException("기물의 이동 범위에 속하지 않습니다.");
		}
		position = destination;
	}

	public Position getPosition() {
		return position;
	}
}
