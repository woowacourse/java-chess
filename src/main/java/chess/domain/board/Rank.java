package chess.domain.board;

/**
 *    체스판 행을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum Rank {
	EIGHT(8),
	SEVEN(7),
	THREE(3),
	SIX(6),
	FIVE(5),
	FOUR(4),
	TWO(2),
	ONE(1);

	private int rank;

	Rank(int rank) {
		this.rank = rank;
	}
}
