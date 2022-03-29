package chess.domain.board;

import static chess.domain.board.Position.MAX_POSITION;
import static chess.domain.board.Position.MIN_POSITION;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {

	private static final String BLOCK_ERROR = "해당 위치로 기물을 옮길 수 없습니다.";
	private static final String BLANK_ERROR = "해당 위치에 기물이 없습니다.";
	private static final int SAME_COLUMN_PAWN_COUNT = 2;
	private static final double DUPLICATION_PAWN_SCORE = 0.5;

	private final Map<Position, Piece> board;

	public Board(final Map<Position, Piece> board) {
		this.board = new HashMap<>(board);
	}

	public void move(Position source, Position target) {
		Piece piece = board.get(source);
		validateMove(source, target, piece);
		board.put(target, piece);
		board.put(source, new Blank());
	}

	private void validateMove(final Position source, final Position target, final Piece piece) {
		validateBlank(piece);
		piece.validateMovement(source, target, board.get(target));
		validateBlocking(source, target, piece);
	}

	private void validateBlocking(final Position source, final Position target, final Piece piece) {
		Direction direction = piece.getDirection(source, target);
		Position checkPosition = source;
		while (checkPosition != target) {
			checkPosition = checkPosition.addDirection(direction);
			Piece currentPiece = board.get(checkPosition);
			checkBlocking(target, checkPosition, currentPiece);
		}
	}

	private void checkBlocking(final Position target, final Position checkPosition, final Piece currentPiece) {
		if (checkPosition != target && !currentPiece.isBlank()) {
			throw new IllegalArgumentException(BLOCK_ERROR);
		}
	}

	private void validateBlank(final Piece piece) {
		if (piece.isBlank()) {
			throw new IllegalArgumentException(BLANK_ERROR);
		}
	}

	public double calculateScore(Team team) {
		double score = 0;
		for (int column = MIN_POSITION; column <= MAX_POSITION; column++) {
			List<Piece> columnPieces = findColumnPieces(team, column);
			score += calculateColumnScore(columnPieces);
		}
		return score;
	}

	private double calculateColumnScore(final List<Piece> columnPieces) {
		long pawnCount = columnPieces.stream()
				.filter(Piece::isPawn)
				.count();
		double sum = columnPieces.stream()
				.mapToDouble(Piece::getScore)
				.sum();
		if (pawnCount >= SAME_COLUMN_PAWN_COUNT) {
			sum -= DUPLICATION_PAWN_SCORE * pawnCount;
		}
		return sum;
	}

	private List<Piece> findColumnPieces(Team team, final int column) {
		List<Piece> pieces = new ArrayList<>();
		for (int row = MIN_POSITION; row <= MAX_POSITION; row++) {
			Position position = Position.of(row, column);
			if (board.get(position).isAlly(team)) {
				pieces.add(board.get(position));
			}
		}
		return pieces;
	}

	public Map<Position, Piece> getBoard() {
		return new HashMap<>(board);
	}
}
