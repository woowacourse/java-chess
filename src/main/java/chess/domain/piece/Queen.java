package chess.domain.piece;

import chess.domain.Position;

public class Queen extends Piece {
	public Queen(Position position, Team team) {
		super(position, team);
		this.representation = 'Q';
	}

	@Override
	public void move(Position destination) {

	}

}
