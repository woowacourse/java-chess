package chess.domain.position;

import static java.lang.Character.*;

import java.util.Arrays;
import java.util.Objects;

public enum ChessRank {

	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8);

	private final int chessRank;

	ChessRank(final int chessRank) {
		this.chessRank = chessRank;
	}

	public static ChessRank from(final int chessRank) {
		return Arrays.stream(values())
			.filter(value -> value.chessRank == chessRank)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 랭크가 존재하지 않습니다."));
	}

	public static ChessRank from(final char chessRank) {
		return Arrays.stream(values())
			.filter(value -> value.chessRank == getNumericValue(chessRank))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 랭크가 존재하지 않습니다."));
	}

	public ChessRank move(final int movingRankValue) {
		return from(this.chessRank + movingRankValue);
	}

	public int gapTo(final ChessRank targetChessRank) {
		Objects.requireNonNull(targetChessRank, "체스 랭크가 null입니다.");
		return targetChessRank.chessRank - this.chessRank;
	}

	@Override
	public String toString() {
		return String.valueOf(chessRank);
	}

}
