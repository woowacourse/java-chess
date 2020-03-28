package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.MovePattern;

import java.util.Optional;

public abstract class Piece implements PieceAbility {
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다";

	protected Position position;
	protected final TeamStrategy teamStrategy;

	public Piece(Position position, TeamStrategy teamStrategy) {
		this.position = position;
		this.teamStrategy = teamStrategy;
	}

	@Override
	public void validateMovePattern(MovePattern movePattern, Optional<Piece> targetPiece) {
		if (isMovablePattern(movePattern, targetPiece)) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	protected abstract boolean isMovablePattern(MovePattern movePattern, Optional<Piece> targetPiece);

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

	@Override
	public boolean isNotKnight() {
		return !(this instanceof Knight);
	}

	public boolean isSameTeam(Piece targetPiece) {
		return teamStrategy.isSameTeam(targetPiece.teamStrategy);
	}

	public boolean isSameFile(File file) {
		return this.position.isSameFile(file);
	}
}

