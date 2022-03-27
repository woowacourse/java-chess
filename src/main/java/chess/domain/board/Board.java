package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Board {

	private static final String BLOCK_ERROR = "해당 위치로 기물을 옮길 수 없습니다.";
	private static final String BOARD_RANGE_ERROR = "체스 판의 범위를 벗어 났습니다.";
	private static final String BLANK_ERROR = "해당 위치에 기물이 없습니다.";
	private static final String NOT_FINISHED_ERROR = "아직 종료되지 않은 게임입니다.";

	private final Map<Position, Piece> board;
	private State state;

	public Board(final Map<Position, Piece> board) {
		this.board = new HashMap<>(board);
		this.state = new WhiteTurn();
	}

	public void move(Position source, Position target) {
		Piece piece = board.get(source);
		validateMove(source, target, piece);
		state = state.play(piece, board.get(target));
		board.put(target, piece);
		board.put(source, new Blank());
	}

	private void validateMove(final Position source, final Position target, final Piece piece) {
		validateBlank(piece);
		piece.validateMovement(source, target);
		validateBlocking(source, target, piece);
	}

	private void validateBlocking(final Position source, final Position target, final Piece piece) {
		Direction direction = piece.getDirection(source, target);
		Position checkPosition = source;
		while (checkPosition != target) {
			checkPosition = moveNextPosition(direction, checkPosition);
			Piece currentPiece = board.get(checkPosition);
			checkBlocking(target, checkPosition, currentPiece);
			piece.validateCatch(currentPiece, direction);
		}
	}

	private void checkBlocking(final Position target, final Position checkPosition, final Piece currentPiece) {
		if (checkPosition != target && !currentPiece.isBlank()) {
			throw new IllegalArgumentException(BLOCK_ERROR);
		}
	}

	private Position moveNextPosition(final Direction direction, Position checkPosition) {
		Optional<Position> position = checkPosition.addDirection(direction);
		if (position.isEmpty()) {
			throw new IllegalArgumentException(BOARD_RANGE_ERROR);
		}
		return position.get();
	}

	private void validateBlank(final Piece piece) {
		if (piece.isBlank()) {
			throw new IllegalArgumentException(BLANK_ERROR);
		}
	}

	public double calculateScore(Team team) {
		double score = 0;
		for (int column = 1; column <= 8; column++) {
			List<Piece> columnPieces = findColumnPieces(team, column);
			score += calculateColumnScore(columnPieces);
		}
		return score;
	}

	private double calculateColumnScore(final List<Piece> columnPieces) {
		double sum = 0;
		long pawnCount = columnPieces.stream()
				.filter(Piece::isPawn)
				.count();
		for (Piece piece : columnPieces) {
			sum += piece.getScore();
		}
		if (pawnCount >= 2) {
			sum -= 0.5 * pawnCount;
		}

		return sum;
	}

	private List<Piece> findColumnPieces(Team team, final int column) {
		List<Piece> pieces = new ArrayList<>();
		for (int row = 1; row <= 8; row++) {
			Position position = Position.of(row, column);
			if (board.get(position).isSameTeam(team)) {
				pieces.add(board.get(position));
			}
		}
		return pieces;
	}

	public boolean isFinished() {
		return state.isFinished();
	}

	public Team judgeWinner() {
		if (state.isFinished()) {
			return state.getTeam();
		}
		throw new IllegalArgumentException(NOT_FINISHED_ERROR);
	}

	public Map<Position, Piece> getBoard() {
		return new HashMap<>(board);
	}
}
