package domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public List<Rank> getReverse() {
		List<Rank> originalBoard = board.getRanks();
		List<Rank> reverseBoard = new ArrayList<>(originalBoard);
		Collections.reverse(reverseBoard);
		return reverseBoard;
	}

	public List<Rank> getRanks() {
		return board.getRanks();
	}

	public List<Piece> getPieces() {
		return board.getPieces();
	}
}
