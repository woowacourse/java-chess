package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.chessboard.ChessBoard;
import chess.domain.movetype.MoveType;

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

	public void move(MoveType moveType, ChessBoard chessBoard) {
		position.move(moveType, chessBoard);
	}

	public boolean isSameTeam(Piece targetPiece) {
		return teamStrategy.isSameTeam(targetPiece.teamStrategy);
	}

	public boolean isSameFile(File file) {
		return this.position.isSameFile(file);
	}
}

