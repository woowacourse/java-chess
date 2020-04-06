package domain.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.command.MoveCommand;
import domain.piece.Piece;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BoardGame {
	private Board board;

	public BoardGame(Board board) {
		this.board = board;
	}

	public BoardGame() {
		this(BoardFactory.create());
	}

	public void move(MoveCommand moveCommand, Team turn) {
		Position sourcePosition = moveCommand.getSourcePosition();
		Position targetPosition = moveCommand.getTargetPosition();

		Piece piece = board.findPiece(sourcePosition)
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_SOURCE_POSITION));
		piece.move(targetPosition, turn, board);
	}

	public boolean isKingAlive() {
		return board.isKingAlive();
	}

	public List<Rank> getRanks() {
		return board.getRanks();
	}
}
