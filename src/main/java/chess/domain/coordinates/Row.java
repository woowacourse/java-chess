package chess.domain.coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Row {
	private static final int MIN_ROW = 1;
	private static final int MAX_ROW = 8;
	private static final Map<Integer, Row> ROW_CACHE = new HashMap<>();

	static {
		IntStream.rangeClosed(MIN_ROW, MAX_ROW)
				.forEach(row -> ROW_CACHE.put(row, new Row(row)));
	}

	private final int value;

	private Row(int value) {
		validateRange(value);
		this.value = value;
	}

	public static Row of(int value) {
		return ROW_CACHE.getOrDefault(value, new Row(value));
	}

	public static Row of(String value) {
		return of(Integer.parseInt(value));
	}

	public static List<Row> values() {
		return new ArrayList<>(ROW_CACHE.values());
	}

	private void validateRange(int value) {
		if (value < MIN_ROW || value > MAX_ROW) {
			throw new IllegalArgumentException("row의 범위를 벗어났습니다.");
		}
	}

	public int calculateGap(Row that) {
		return that.value - this.value;
	}

	public Row next(int rowDirection) {
		return ROW_CACHE.get(value + rowDirection);
	}

	public String getName() {
		return String.valueOf(value);
	}
}
