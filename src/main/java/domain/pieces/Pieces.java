package domain.pieces;

import domain.pieces.exceptions.PiecesException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pieces {
	private Set<Piece> pieces;

	public Pieces(Set<Piece> pieces) {
		this.pieces = new HashSet<>(pieces);
	}

	public static Pieces of(PiecesFactory piecesFactory) {
		return new Pieces(piecesFactory.getInstance());
	}

	public void move(Point from, Point to) {
		Piece subject = find(from);
		Direction direction = Direction.of(from, to);
		subject.canMove(direction);

		if (direction != Direction.ELSE) {
			Point point = from.add(direction.getRowIndex(), direction.getColumnIndex());
			while (!point.equals(to)) {
				subject = moveOnly(subject, point);
				point = point.add(direction.getRowIndex(), direction.getColumnIndex());
			}
		}

		try {
			Piece target = find(to);
			attack(subject, target);
		} catch (CanNotMoveException e) {
			moveOnly(subject, to);
		}
	}

	private Piece find(Point point) {
		return pieces.stream()
					.filter(piece -> piece.getPoint().equals(point))
					.findFirst()
					.orElseThrow(() -> new CanNotMoveException("대상이 없습니다."));
	}

	private Piece moveOnly(Piece subject, Point to) {
		if (isEmptyPoint(to)) {
			Piece moved = subject.move(to);
			pieces.remove(subject);
			pieces.add(moved);
			return moved;
		}
		throw new CanNotMoveException("움직이려는 곳에 대상이 있습니다.");
	}

	private boolean isEmptyPoint(Point point) {
		return pieces.stream()
				.noneMatch(piece -> piece.getPoint().equals(point));
	}

	private void attack(Piece subject, Piece target) {
		pieces.remove(subject);
		pieces.remove(target);
		pieces.add(subject.move(target.getPoint()));
	}

	public Set<Piece> getSet() {
		return Collections.unmodifiableSet(pieces);
	}
}
