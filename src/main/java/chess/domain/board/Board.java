package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import chess.domain.ChessScore;
import chess.domain.direction.Direction;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.UnitPosition;

public class Board {

	private static final String NO_PIECE = "해당 위치에 말이 없습니다.";
	private static final String PIECE_BLOCK = "가려는 위치 중간에 말이 존재합니다.";
	private static final String INVALID_MOVE = "갈 수 없는 위치입니다.";

	private static final int CRITERIA_COUNT_OF_PAWN_SCORE = 1;

	private final Map<Position, Piece> pieces;

	public Board() {
		this.pieces = BoardInitializer.generate();
	}

	public Board(Map<Position, Piece> pieces) {
		this.pieces = new HashMap<>(pieces);
	}

	public boolean isSameColor(Position position, Color color) {
		return checkPositionEmpty(position).isSameColor(color);
	}

	public Optional<Piece> findPiece(Position position) {
		return Optional.ofNullable(pieces.get(position));
	}

	public void movePiece(Position from, Position to) {
		Piece fromPiece = checkPositionEmpty(from);
		Direction direction = fromPiece.checkMovableRange(from, to);
		searchPiece(from, to, direction);
		validateMovableToTarget(from, to, fromPiece);

		move(from, to, fromPiece);
	}

	private Piece checkPositionEmpty(Position from) {
		return findPiece(from)
			.orElseThrow(() -> new NoSuchElementException(NO_PIECE));
	}

	private void searchPiece(Position from, Position to, Direction direction) {
		Position step = from.convert(new UnitPosition(0, 0));
		while (!step.equals(to)) {
			step = step.convert(direction.getUnitPosition());
			validateExistPiece(to, step);
		}
	}

	private void validateExistPiece(Position to, Position step) {
		if (findPiece(step).isPresent() && !step.equals(to)) {
			throw new IllegalArgumentException(PIECE_BLOCK);
		}
	}

	private void validateMovableToTarget(Position from, Position to, Piece fromPiece) {
		Optional<Piece> nullablePiece = findPiece(to);
		if (nullablePiece.isPresent() && !fromPiece.isMovable(from, to, nullablePiece.get())) {
			throw new IllegalArgumentException(INVALID_MOVE);
		}

		if (nullablePiece.isEmpty() && !fromPiece.isMovable(from, to)) {
			throw new IllegalArgumentException(INVALID_MOVE);
		}
	}

	private void move(Position from, Position to, Piece piece) {
		this.pieces.remove(from);
		this.pieces.put(to, piece);
	}

	public ChessScore calculateScore() {
		double whiteScore = 0;
		double blackScore = 0;
		for (int column = Position.MIN; column <= Position.MAX; column++) {
			whiteScore += calculateColumnScore(column, (Piece::isWhite));
			blackScore += calculateColumnScore(column, (piece -> !piece.isWhite()));
		}
		return new ChessScore(whiteScore, blackScore);
	}

	private double calculateColumnScore(int column, Predicate<Piece> colorCondition) {
		Map<Boolean, List<Piece>> pawnOrNot = groupByPawn(column, colorCondition);

		List<Piece> pawns = pawnOrNot.computeIfAbsent(true, key -> new ArrayList<>());
		List<Piece> notPawn = pawnOrNot.computeIfAbsent(false, key -> new ArrayList<>());

		double pawnSum = sumScoreOfPawn(pawns);
		double notPawnSum = sumScores(notPawn);

		return notPawnSum + pawnSum;
	}

	private Map<Boolean, List<Piece>> groupByPawn(int column, Predicate<Piece> colorCondition) {
		return this.pieces.entrySet().stream()
			.filter(entry -> entry.getKey().isSameColumn(column))
			.map(Map.Entry::getValue)
			.filter(colorCondition)
			.collect(groupingBy(Piece::isPawn));
	}

	private double sumScoreOfPawn(List<Piece> pawns) {
		int pawnSize = pawns.size();
		if (pawnSize == CRITERIA_COUNT_OF_PAWN_SCORE) {
			return sumScores(pawns);
		}
		return pawnSize * Piece.PAWN_LOW_SCORE;
	}

	private double sumScores(List<Piece> pieces) {
		return pieces.stream()
			.mapToDouble(Piece::getScore)
			.sum();
	}

	public boolean isKingNotExist(Color color) {
		return this.pieces.values().stream()
			.noneMatch(piece -> piece.isKing() && piece.isSameColor(color));
	}

	public Map<Position, Piece> getPieces() {
		return new HashMap<>(pieces);
	}
}
