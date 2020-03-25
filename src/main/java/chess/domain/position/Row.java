package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Row {
	private static final Map<Integer, Row> ROW_CACHE = new HashMap<>();

	private final int value;

	static {
		ROW_CACHE.put(1, new Row(1, "8"));
		ROW_CACHE.put(2, new Row(2, "7"));
		ROW_CACHE.put(3, new Row(3, "6"));
		ROW_CACHE.put(4, new Row(4, "5"));
		ROW_CACHE.put(5, new Row(5, "4"));
		ROW_CACHE.put(6, new Row(6, "3"));
		ROW_CACHE.put(7, new Row(7, "2"));
		ROW_CACHE.put(8, new Row(8, "1"));
	}

	private final String name;

	private Row(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static Optional<Row> of(Integer value) {
		return Optional.ofNullable(ROW_CACHE.get(value));
	}

	public static Row of(String value) {
		return ROW_CACHE.values()
			.stream()
			.filter(column -> column.isSameName(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Row 값입니다."));
	}

	public static Row of(int value) {
		return ROW_CACHE.get(value);
	}

	public static List<Row> rowNames() {
		return new ArrayList<>(ROW_CACHE.values());
	}

	private boolean isSameName(String value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("입력값이 null입니다.");
		}
		return name.equals(value);
	}

	public String getName() {
		return name;
	}

	public Row nextRow(int rowDirection) {
		return ROW_CACHE.get(value + rowDirection);
	}

	@Override
	public String toString() {
		return "Row{" +
			"name='" + name + '\'' +
			'}';
	}
}
