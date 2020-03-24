package chess.domain.board;

/**
 *    체스판 행을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum Rank {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8);

	private int rank;

	Rank(int rank) {
		this.rank = rank;
	}
}
