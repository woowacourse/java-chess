package chess.domain.board;

import java.util.Arrays;

/**
 *    체스판 행을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
// row
public enum Row {
	EIGHT(8),
	SEVEN(7),
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	ONE(1);

	private int row;

	Row(int row) {
		this.row = row;
	}

	public static Row of(int row) {
		return Arrays.stream(Row.values())
			.filter(rank -> rank.row == row)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못입력하셨습니다!"));
	}

	public int getRow() {
		return row;
	}
}
