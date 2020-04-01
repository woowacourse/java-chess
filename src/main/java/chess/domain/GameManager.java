package chess.domain;

import java.util.Objects;

import chess.domain.board.Board;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;

public class GameManager {
	private final Board board;
	private Color turn;

	public GameManager(Board board, Color turn) {
		this.board = Objects.requireNonNull(board, "board가 존재하지 않습니다.");
		this.turn = Objects.requireNonNull(turn, "turn이 존재하지 않습니다.");
	}

	public GameManager(Board board) {
		this(board, Color.WHITE);
	}

	public void move(Coordinates from, Coordinates to) {
		validateTurn(from);
		board.movePiece(from, to);
		nextTurn();
	}

	private void nextTurn() {
		turn = turn.reverse();
	}

	public GameResult calculateScore() {
		ScoreRule scoreRule = new ScoreRule(board.getPieces());
		return scoreRule.calculateScore();
	}

	public boolean isEndOfGame() {
		return !board.isKingAliveOf(turn);
	}

	private void validateTurn(Coordinates from) {
		board.findPieceBy(from)
				.filter(piece -> piece.isTeamOf(turn))
				.orElseThrow(() -> new IllegalArgumentException(turn + "의 차례입니다."));
	}

	public Board getBoard() {
		return board;
	}

	public Color getEnemyColor() {
		return turn.reverse();
	}
}
