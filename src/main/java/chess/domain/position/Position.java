package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;
	private static final int POSITION_KEY_VALID_LENGTH = 2;
	private static final Map<String, Position> POSITION_CACHE = new HashMap<>();

	private final ChessFile chessFile;
	private final ChessRank chessRank;

	private Position(ChessFile chessFile, ChessRank chessRank) {
		this.chessFile = chessFile;
		this.chessRank = chessRank;
	}

	static {
		for (ChessFile chessFile : ChessFile.values()) {
			for (ChessRank chessRank : ChessRank.values()) {
				POSITION_CACHE.put(key(chessFile, chessRank), new Position(chessFile, chessRank));
			}
		}
	}

	private static String key(ChessFile chessFile, ChessRank chessRank) {
		return String.format("%s%s", chessFile, chessRank);
	}

	public static Position of(ChessFile chessFile, ChessRank chessRank) {
		Objects.requireNonNull(chessFile, "체스 파일이 null입니다.");
		Objects.requireNonNull(chessRank, "체스 랭크가 null입니다.");

		return POSITION_CACHE.getOrDefault(key(chessFile, chessRank), new Position(chessFile, chessRank));
	}

	public static Position of(String key) {
		validate(key);
		return POSITION_CACHE.getOrDefault(key,
			new Position(ChessFile.from(key.charAt(FILE_INDEX)), ChessRank.from(key.charAt(RANK_INDEX))));
	}

	private static void validate(String key) {
		validateEmpty(key);
		validateLength(key);
	}

	private static void validateEmpty(String key) {
		if (Objects.isNull(key) || key.isEmpty()) {
			throw new IllegalArgumentException("유효한 위치 입력이 아닙니다.");
		}
	}

	private static void validateLength(String key) {
		if (key.length() != POSITION_KEY_VALID_LENGTH) {
			throw new IllegalArgumentException("유효한 위치 입력이 아닙니다.");
		}
	}

	Position move(int movingFileValue, int movingRankValue) {
		return Position.of(chessFile.move(movingFileValue), chessRank.move(movingRankValue));
	}

	public int calculateChessFileGapTo(Position targetPosition) {
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
		return this.chessFile.gapTo(targetPosition.chessFile);
	}

	public int calculateChessRankGapTo(Position targetPosition) {
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
		return this.chessRank.gapTo(targetPosition.chessRank);
	}

	public boolean isSame(ChessFile chessFile) {
		Objects.requireNonNull(chessFile, "체스 파일이 null입니다.");
		return this.chessFile.equals(chessFile);
	}

}
