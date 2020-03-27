package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;

public abstract class Piece implements PieceAbility {
	protected Position position;
	protected final TeamStrategy teamStrategy;

	public Piece(Position position, TeamStrategy teamStrategy) {
		this.position = position;
		this.teamStrategy = teamStrategy;
	}

	@Override
	public boolean isEqualPosition(Position position) {
		return this.position.equals(position);
	}

	@Override
	public boolean isBlackTeam() {
		return teamStrategy.isBlackTeam();
	}

	@Override
	public boolean isWhiteTeam() {
		return teamStrategy.isWhiteTeam();
	}

	@Override
	public void move(Position position) {
		this.position = position;
	}

	;

	@Override
	public boolean isKnight() {
		return this instanceof Knight;
	}

	public boolean isSameTeam(Piece targetPiece) {
		return teamStrategy.isSameTeam(targetPiece.teamStrategy);
	}

	public boolean isSameFile(File file) {
		return this.position.isSameFile(file);
	}
}

