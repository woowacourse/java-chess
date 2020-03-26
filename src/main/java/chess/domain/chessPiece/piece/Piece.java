package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.chessboard.ChessBoard;
import chess.domain.movepattern.MovePattern;

public abstract class Piece implements PieceAbility {
	protected final TeamStrategy teamStrategy;
	protected Position position;

	public Piece(Position position, TeamStrategy teamStrategy) {
		this.position = position;
		this.teamStrategy = teamStrategy;
	}

	public boolean isEqualPosition(Position position) {
		return this.position.equals(position);
	}

	public void move(MovePattern movePattern, ChessBoard chessBoard) {
		position.move(movePattern, chessBoard);
	}

	public boolean isSameTeam(Piece targetPiece) {
		return teamStrategy.isSameTeam(targetPiece.teamStrategy);
	}

	public boolean isSameFile(File file) {
		return this.position.isSameFile(file);
	}
}

