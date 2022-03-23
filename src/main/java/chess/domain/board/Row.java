package chess.domain.board;

import java.util.Arrays;

public enum Row {

<<<<<<< HEAD
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static Row from(final int value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
    }

    public static Row from(final String row) {
        try {
            return from(Integer.parseInt(row));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("행은 숫자여야 합니다.");
        }
    }

    public int calculateDifference(final Row target) {
        return this.value - target.value;
    }

    public Row move(final int rowDifference) {
        return from(value + rowDifference);
    }
=======
	FIRST(1),
	SECOND(2),
	THIRD(3),
	FOURTH(4),
	FIFTH(5),
	SIXTH(6),
	SEVENTH(7),
	EIGHTH(8);

	private final int value;

	Row(int value) {
		this.value = value;
	}

	public static Row of(int value) {
		return Arrays.stream(values())
			.filter(row -> row.value == value)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
	}
>>>>>>> refactor(domain/piece): 말 종류에 따라 클래스 분리
}
