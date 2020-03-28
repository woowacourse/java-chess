package chess.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import chess.domain.position.Position;
import chess.domain.position.Row;

public enum Side {
	BLACK(Row.SEVEN, Row.EIGHT, (source, target) -> source.compareToRow(target) > 0),
	WHITE(Row.TWO, Row.ONE, (source, target) -> source.compareToRow(target) < 0);

	private Row initPawnRow;
	private Row initNobleRow;
	private BiPredicate<Position, Position> forwardAttackFilter;

	Side(Row initPawnRow, Row initNobleRow, BiPredicate<Position, Position> forwardAttackFilter) {
		this.initPawnRow = initPawnRow;
		this.initNobleRow = initNobleRow;
		this.forwardAttackFilter = forwardAttackFilter;
	}

	public boolean isInitPawnRow(Position position) {
		return position.isSameRow(initPawnRow);
	}

	public boolean isForwardAttack(Position source, Position target) {
		return forwardAttackFilter.test(source, target);
	}

	public Side reverse() {
		return Arrays.stream(values())
				.filter(side -> side != this)
				.findAny()
				.orElseThrow(AssertionError::new);
	}

	public Row getInitPawnRow() {
		return initPawnRow;
	}

	public Row getInitNobleRow() {
		return initNobleRow;
	}
}
