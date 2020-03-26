package chess.domain.piece.state;

import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Started implements State {
	protected Position source;
	protected Position target;

	public Started(Position position) {
		// this.position = position;
	}

	@Override
	public State move() {
		return null;
	}
}
