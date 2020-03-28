package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Column;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Pieces {
	private final Set<Piece> pieces;

	public Pieces(Set<Piece> pieces) {
		this.pieces = new HashSet<>(pieces);
	}

	public void move(Point from, Point to) {
		Piece subject = find(from);
		Direction direction = Direction.of(from, to);
		Distance distance = Distance.of(from, to);
		subject.canReach(distance);

		if (direction.isLinearDirection()) {
			subject = moveProgressivelyBeforeTo(from, to, subject, direction);
		}

		try {
			Piece target = find(to);
			attack(subject, target, direction);
		} catch (CanNotMoveException e) {
			moveOnly(subject, to, direction);
		}
	}

	private Piece moveProgressivelyBeforeTo(Point from, Point to, Piece subject, Direction direction) {
		Point point = from.add(direction.getRowIndex(), direction.getColumnIndex());
		while (!point.equals(to)) {
			subject = moveOnly(subject, point, direction);
			point = point.add(direction.getRowIndex(), direction.getColumnIndex());
		}
		return subject;
	}

	private Piece find(Point point) {
		return pieces.stream()
					.filter(piece -> piece.matchPoint(point))
					.findFirst()
					.orElseThrow(() -> new CanNotMoveException("대상이 없습니다."));
	}

	private Piece moveOnly(Piece subject, Point to, Direction direction) {
		if (isEmptyPoint(to)) {
			subject.canMove(direction);
			Piece moved = subject.move(to);
			pieces.remove(subject);
			pieces.add(moved);
			return moved;
		}
		throw new CanNotMoveException("움직이려는 곳에 대상이 있습니다.");
	}

	private boolean isEmptyPoint(Point point) {
		return pieces.stream()
				.noneMatch(piece -> piece.matchPoint(point));
	}

	private void attack(Piece subject, Piece target, Direction direction) {
		subject.canAttack(direction, target);
		pieces.remove(subject);
		pieces.remove(target);
		pieces.add(subject.move(target.getPoint()));
	}

	public double computeBlackTeamScore() {
		double simpleSum = pieces.stream()
				.filter(Piece::isBlack)
				.mapToDouble(Piece::getScore)
				.sum();
		return simpleSum - sumHalfScoreOfBlackPawn();
	}

	private double sumHalfScoreOfBlackPawn() {
		return countHalfScoreBlackPawnNumber() * Pawn.HALF_SCORE;
	}

	public double computeWhiteTeamScore() {
		double simpleSum = pieces.stream()
				.filter(Piece::isWhite)
				.mapToDouble(Piece::getScore)
				.sum();
		return simpleSum - sumHalfScoreOfWhitePawn();
	}

	private double sumHalfScoreOfWhitePawn() {
		return countHalfScoreWhitePawnNumber() * Pawn.HALF_SCORE;
	}

	public boolean isBlackKingKilled() {
		return pieces.stream()
				.filter(Piece::isBlack)
				.noneMatch(Piece::isKing);
	}

	public boolean isWhiteKingKilled() {
		return pieces.stream()
				.filter(Piece::isWhite)
				.noneMatch(Piece::isKing);
	}

	private int countHalfScoreBlackPawnNumber() {
		int halfScorePawnNumber = 0;
		for (Column column : Column.values()) {
			halfScorePawnNumber += countBlackPawnInColumnIfMoreThanTwo(column);
		}
		return halfScorePawnNumber;
	}

	private int countBlackPawnInColumnIfMoreThanTwo(Column column) {
		int count = 0;
		for (Piece piece : pieces) {
			count += addBlackPawnCount(column, piece);
		}

		return decideZeroOrCount(count);
	}

	private int addBlackPawnCount(Column column, Piece piece) {
		if (piece.isWhite()) {
			return 0;
		}
		if (piece.isNotPawn()) {
			return 0;
		}
		if (piece.matchColumnPoint(column)) {
			return 1;
		}
		return 0;
	}

	private int decideZeroOrCount(int count) {
		if (count > 1) {
			return count;
		}
		return 0;
	}

	private int countHalfScoreWhitePawnNumber() {
		int halfScorePawnNumber = 0;
		for (Column column : Column.values()) {
			halfScorePawnNumber += countWhitePawnInColumnIfMoreThanTwo(column);
		}
		return halfScorePawnNumber;
	}

	private int countWhitePawnInColumnIfMoreThanTwo(Column column) {
		int count = 0;
		for (Piece piece : pieces) {
			count += addWhitePawnCount(column, piece);
		}

		return decideZeroOrCount(count);
	}

	private int addWhitePawnCount(Column column, Piece piece) {
		if (piece.isBlack()) {
			return 0;
		}
		if (piece.isNotPawn()) {
			return 0;
		}
		if (piece.matchColumnPoint(column)) {
			return 1;
		}
		return 0;
	}

	public Set<Piece> getSet() {
		return Collections.unmodifiableSet(pieces);
	}
}
