package chess.service;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;

public class ChessService {

	private final Board board;
	private Turn turn;

	private ChessService(final Board board, final Turn turn) {
		this.board = board;
		this.turn = turn;
	}

	public static ChessService temp() {
		return new ChessService(Board.empty(), Turn.WHITE);
	}

	public static ChessService create() {
		return new ChessService(Board.create(), Turn.WHITE);
	}

	public void movePiece(Position source, Position target) {
		Team team = getCurrentTeam();
		board.checkIsMovable(team, source, target);
		board.movePiece(team, source, target);
		alternateTurn();
	}

	private Team getCurrentTeam() {
		if (turn == Turn.WHITE) {
			return Team.WHITE;
		}
		return Team.BLACK;
	}

	private void alternateTurn() {
		if (turn == Turn.WHITE) {
			turn = Turn.BLACK;
			return;
		}
		if (turn == Turn.BLACK) {
			turn = Turn.WHITE;
		}
	}

	public Map<Position, Piece> getBoard() {
		return board.getBoard();
	}
}
