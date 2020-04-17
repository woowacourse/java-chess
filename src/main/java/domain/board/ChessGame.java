package domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.command.MoveCommand;
import domain.piece.Piece;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class ChessGame {
	private Board board;
	private Team turn;

	public ChessGame(Board board, Team turn) {
		this.board = board;
		this.turn = turn;
	}

	public ChessGame(Board board) {
		this(board, Team.WHITE);
	}

	public ChessGame() {
		this(BoardFactory.create());
	}

	public ChessGame(List<Piece> pieces, String turn) {
		this(BoardFactory.create(pieces), Team.of(turn));
	}

	public void move(MoveCommand moveCommand) {
		Position sourcePosition = moveCommand.getSourcePosition();
		Position targetPosition = moveCommand.getTargetPosition();

		Piece piece = board.findPiece(sourcePosition)
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_SOURCE_POSITION));
		piece.move(targetPosition, turn, board);
		turn = Team.changeTurn(turn);
	}

	public boolean isKingAlive() {
		return board.isKingAlive();
	}

	public Board getBoard() {
		return board;
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

	public Team getTurn() {
		return turn;
	}
}
