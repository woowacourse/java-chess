package chess.domain.board;

import java.util.Arrays;

/**
 *    체스판 행을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum Rank {
	EIGHT(8),
	SEVEN(7),
	SIX(6),
	FIVE(5),
	FOUR(4),
	THREE(3),
	TWO(2),
	ONE(1);

	private int row;

	Rank(int row) {
		this.row = row;
	}

	public static Rank of(int row) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.getRow() == row)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 rank입니다."));
	}

	public int getRow() {
		return row;
	}
}
