package chess.service;

import java.util.Map;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;

public class BoardAndTurn {

	private final Map<Position, Piece> board;
	private final Team turn;

	public BoardAndTurn(final Map<Position, Piece> board, final Team turn) {
		this.board = board;
		this.turn = turn;
	}

	public Map<Position, Piece> getBoard() {
		return board;
	}

	public Team getTurn() {
		return turn;
	}
}
