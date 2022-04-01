package chess.converter;

import java.util.Arrays;
import java.util.List;

import chess.domain.position.Position;

public class StringToPositionConverter {

	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;
	private static final int POSITION_INPUT_LENGTH = 2;
	private static final String INVALID_LENGTH = "좌표는 2글자여야 합니다.";

	public static Position from(String input) {
		List<String> fileAndRank = Arrays.asList(input.split(""));
		validatePositionInputLength(fileAndRank);

		Rank rank = Rank.from(fileAndRank.get(RANK_INDEX));
		File file = File.from(fileAndRank.get(FILE_INDEX));

		return new Position(rank.getRow(), file.getColumn());
	}

	private static void validatePositionInputLength(List<String> fileAndRank) {
		if (fileAndRank.size() != POSITION_INPUT_LENGTH) {
			throw new IllegalArgumentException(INVALID_LENGTH);
		}
	}
}
