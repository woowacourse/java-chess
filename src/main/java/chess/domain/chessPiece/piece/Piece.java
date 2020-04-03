package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.MovePattern;

import java.util.Objects;
import java.util.Optional;

public abstract class Piece implements PieceAbility {
	public static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다";

	protected final String name;
	protected Position position;
	protected final TeamStrategy teamStrategy;

	public Piece(Position position, TeamStrategy teamStrategy) {
		this.position = position;
		this.teamStrategy = teamStrategy;
		this.name = this.getPieceName();
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

	public Position getPosition() {
		return position;
	}

	public TeamStrategy getTeamStrategy() {
		return teamStrategy;
	}

	public String getFile() {
		return position.getFile();
	}

	public String getRank() {
		return position.getRank();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Piece piece = (Piece) o;
		return Objects.equals(name, piece.name) &&
				Objects.equals(position, piece.position) &&
				Objects.equals(teamStrategy, piece.teamStrategy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, position, teamStrategy);
	}
}

