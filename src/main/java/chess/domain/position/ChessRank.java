package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// TODO: 2020/03/28 Enum으로 변경하기
public class ChessRank {

	private static final int LOWER_BOUND = 1;
	private static final int UPPER_BOUND = 8;
	private static final Map<Integer, ChessRank> CHESS_RANK_CACHE = new HashMap<>();

	static {
		for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
			CHESS_RANK_CACHE.put(i, new ChessRank(i));
		}
	}

	private final int chessRank;

	private ChessRank(int chessRank) {
		validate(chessRank);
		this.chessRank = chessRank;
	}

	public static ChessRank from(int chessRank) {
		return CHESS_RANK_CACHE.getOrDefault(chessRank, new ChessRank(chessRank));
	}

	public static ChessRank from(char chessRank) {
		int parsedChessRank = Character.getNumericValue(chessRank);
		return CHESS_RANK_CACHE.getOrDefault(parsedChessRank, new ChessRank(parsedChessRank));
	}

	public static List<ChessRank> values() {
		return new ArrayList<>(CHESS_RANK_CACHE.values());
	}

	private void validate(int chessRank) {
		if (chessRank < LOWER_BOUND || chessRank > UPPER_BOUND) {
			throw new IllegalArgumentException("유효한 체스 랭크가 아닙니다.");
		}
	}

	public ChessRank move(int rankMovingUnit) {
		return from(this.chessRank + rankMovingUnit);
	}

	public int intervalTo(ChessRank targetRank) {
		Objects.requireNonNull(targetRank, "비교할 타겟 랭크가 존재하지 않습니다.");
		return targetRank.chessRank - this.chessRank;
	}

	@Override
	public String toString() {
		return String.valueOf(chessRank);
	}
}
