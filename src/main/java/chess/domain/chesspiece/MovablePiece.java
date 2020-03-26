package chess.domain.chesspiece;

import chess.domain.MoveManager;
import chess.domain.Position;
import chess.domain.Team;

public abstract class MovablePiece extends ChessPiece {
	protected final MoveManager moveManager;

	public MovablePiece(Position position, Team team) {
		super(position, team);
		moveManager = new MoveManager(position);
	}


}
