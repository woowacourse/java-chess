package chess.domain;

import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Side {
	BLACK(Row.SEVEN, Row.EIGHT, (source, target) -> source.compareToRow(target) > 0),
	WHITE(Row.TWO, Row.ONE, (source, target) -> source.compareToRow(target) < 0);

	private Row initPawnRow;
	private Row initNobleRow;
	private BiPredicate<Position, Position> attackForwardFilter;

	Side(Row initPawnRow, Row initNobleRow, BiPredicate<Position, Position> attackForwardFilter) {
		this.initPawnRow = initPawnRow;
		this.initNobleRow = initNobleRow;
		this.attackForwardFilter = attackForwardFilter;
	}

	public boolean isInitPawnRow(Position position) {
		return position.isSameRow(initPawnRow);
	}

	public boolean isAttackForward(Position source, Position target) {
		return attackForwardFilter.test(source, target);
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
