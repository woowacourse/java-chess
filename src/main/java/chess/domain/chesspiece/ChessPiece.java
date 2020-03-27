package chess.domain.chesspiece;

import chess.domain.MoveManager;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public abstract class ChessPiece {
	protected final Team team;
	protected Position position;
	protected final MoveManager moveManager;

	public ChessPiece(Position position, Team team) {
		this.position = position;
		this.team = team;
		this.moveManager = new MoveManager(this.position);
	}

	public boolean isMatchTeam(Team team) {
		return this.team == team;
	}

	public boolean isNotMatchTeam(Team team) {
		return (this.team == team) == false;
	}

	public boolean equalsPosition(Position position) {
		return this.position.equals(position);
	}

	public boolean isSameTeam(ChessPiece chessPiece) {
		return chessPiece.isMatchTeam(this.team);
	}

	public void changePosition(Position position) {
		this.position = position;
		this.moveManager.changePosition(position);
	}
	public abstract String getName();

	public abstract boolean isNeedCheckPath();

	public abstract Positions makePath(ChessPiece chessPiece);

	public abstract void validateMove(ChessPiece chessPiece);

}
