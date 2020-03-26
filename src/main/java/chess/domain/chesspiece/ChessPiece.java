package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public abstract class ChessPiece {
	protected final Team team;
	protected Position position;

	public ChessPiece(Position position, Team team) {
		this.position = position;
		this.team = team;
	}

	public boolean isMatchTeam(Team team) {
		return this.team == team;
	}

	public abstract String getName();

	public abstract boolean isNeedCheckPath();

	public abstract List<Position> makePath(ChessPiece chessPiece);

	public abstract void validateMove(ChessPiece chessPiece);

}
