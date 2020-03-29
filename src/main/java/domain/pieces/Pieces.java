package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Column;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pieces {
	private static final int MIN_COLUMN_DUPLICATION_NUM = 2;
	private static final int START_KING_NUM = 2;

	private final Set<Piece> pieces;

	public Pieces(Set<Piece> pieces) {
		this.pieces = new HashSet<>(pieces);
	}

	public void move(Point from, Point to) {
		Piece subject = find(from);

		Distance distance = Distance.of(from, to);
		subject.validateReach(distance);

		Direction direction = Direction.of(from, to);
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
		Point next = from.add(direction.getRowIndex(), direction.getColumnIndex());
		while (!next.equals(to)) {
			subject = moveOnly(subject, next, direction);
			next = next.add(direction.getRowIndex(), direction.getColumnIndex());
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
		if (isNotEmptyPoint(to)) {
			throw new CanNotMoveException("움직이려는 곳에 대상이 있습니다.");
		}

		subject.validateMoveDirection(direction);
		Piece moved = subject.move(to);
		pieces.remove(subject);
		pieces.add(moved);
		return moved;
	}

	private boolean isNotEmptyPoint(Point point) {
		return pieces.stream()
				.anyMatch(piece -> piece.matchPoint(point));
	}

	private void attack(Piece subject, Piece target, Direction direction) {
		subject.validateAttack(direction, target);
		pieces.remove(subject);
		pieces.remove(target);
		pieces.add(subject.move(target.getPoint()));
	}

	public double sumTeamScore(Team team) {
		double simpleSum = pieces.stream()
				.filter(piece -> piece.isTeam(team))
				.mapToDouble(Piece::getScore)
				.sum();
		return simpleSum - sumHalfPawnScoresOfTeam(team);
	}

	private double sumHalfPawnScoresOfTeam(Team team) {
		return countHalfScorePawnOfTeam(team) * Pawn.HALF_SCORE;
	}

	private int countHalfScorePawnOfTeam(Team team) {
		int halfScorePawnNumber = 0;
		for (Column column : Column.values()) {
			halfScorePawnNumber += countPawnOfTeamInColumnIfMoreThanTwo(team, column);
		}
		return halfScorePawnNumber;
	}

	private int countPawnOfTeamInColumnIfMoreThanTwo(Team team, Column column) {
		int count = (int) pieces.stream()
				.filter(piece -> piece.isKindOf(PieceType.PAWN))
				.filter(piece -> piece.isTeam(team))
				.filter(piece -> piece.matchColumnPoint(column))
				.count();

		return decideZeroOrCount(count);
	}


	private int decideZeroOrCount(int count) {
		if (count >= MIN_COLUMN_DUPLICATION_NUM) {
			return count;
		}
		return 0;
	}

	public boolean isKingKilled() {
		return pieces.stream()
				.filter(piece -> piece.isKindOf(PieceType.KING))
				.count() < START_KING_NUM;
	}

	public boolean isKingKilled(Team team) {
		return pieces.stream()
				.filter(piece -> piece.isTeam(team))
				.noneMatch(piece -> piece.isKindOf(PieceType.KING));
	}

	public Set<Piece> getSet() {
		return Collections.unmodifiableSet(pieces);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pieces pieces1 = (Pieces) o;
		return Objects.equals(pieces, pieces1.pieces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pieces);
	}

	@Override
	public String toString() {
		return "Pieces{" +
				"pieces=" + pieces +
				'}';
	}
}

