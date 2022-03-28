package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Rank;

public abstract class Piece {
	private static final String ERROR_MESSAGE_POSITION_SAME_TEAM = "[ERROR] 사격 중지!! 아군이다!! ><";

	final Color color;
	double score;

	Piece(Color color, double score) {
		this.color = color;
		this.score = score;
	}

	public static Piece from(File file, Rank rank) {
		return PieceGenerator.generatePiece(file, rank);
	}

	abstract public String getEmoji();

	abstract public boolean canMove(Direction direction, Piece otherPiece);

	void checkSameTeam(Piece otherPiece) {
		if (isSameColor(otherPiece.color)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_SAME_TEAM);
		}
	}

	public boolean isSameColor(Color color) {
		return color == this.color;
	}

	public double addScore(double sum) {
		return sum + score;
	}

	public boolean isNone() {
		return false;
	}

	public boolean isPawn() {
		return false;
	}

	public boolean isKing() {
		return false;
	}
}
