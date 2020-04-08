package chess.domain;

import java.util.Map;
import java.util.Objects;

import chess.domain.board.Board;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.exception.PieceMoveFailedException;

public class ChessGame {
	private final Board board;
	private Color turn;

	public ChessGame(Board board, Color turn) {
		this.board = Objects.requireNonNull(board, "board가 존재하지 않습니다.");
		this.turn = Objects.requireNonNull(turn, "turn이 존재하지 않습니다.");
	}

	public ChessGame(Board board) {
		this(board, Color.WHITE);
	}

	public Piece move(Coordinates from, Coordinates to) {
		validateTurn(from);
		Piece movedPiece = board.movePiece(from, to);
		nextTurn();
		return movedPiece;
	}

	private void nextTurn() {
		turn = turn.reverse();
	}

	public GameResult calculateScore() {
		ScoreRule scoreRule = new ScoreRule(board.getPieces());
		return scoreRule.calculateScore();
	}

	public boolean isEndOfGame() {
		return !(board.isKingAliveOf(Color.BLACK) && board.isKingAliveOf(Color.WHITE));
	}

	private void validateTurn(Coordinates from) {
		board.findPieceBy(from)
				.filter(piece -> piece.isTeamOf(turn))
				.orElseThrow(() -> new PieceMoveFailedException(turn + "의 차례입니다."));
	}

	public Board getBoard() {
		return board;
	}

	public Color getEnemyColor() {
		return turn.reverse();
	}

	public String getTurn() {
		return turn.name();
	}

	public Map<Coordinates, Piece> getPieces() {
		return board.getPieces();
	}
}
