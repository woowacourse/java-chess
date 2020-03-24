package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

public enum Row {
	A(1),
	B(2),
	C(3),
	D(4),
	E(5),
	F(6),
	G(7),
	H(8);

	private final int value;

	Row(int value) {
		this.value = value;
	}

	public static Row of(String value) {
		return Arrays.stream(values())
			.filter(row -> row.isEquals(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Row 값입니다."));
	}

	private boolean isEquals(String value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("입력값이 null입니다.");
		}
		return name().equals(value.toUpperCase());
	}
}
