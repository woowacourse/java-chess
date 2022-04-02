package chess.controller;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import java.util.Arrays;
import java.util.List;

public class PositionConvertor {

	private static final String WRONG_LENGTH_ERROR = "움직일 기물의 위치는 2글자로 입력해야 합니다.";
	private static final String DELIMITER = "";
	private static final int FORMAT_LENGTH = 2;
	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;

	public static Position to(final String rawPosition) {
		validateLength(rawPosition);
		List<String> fileAndRank = Arrays.asList(rawPosition.split(DELIMITER));

		Rank rank = Rank.of(fileAndRank.get(RANK_INDEX));
		File file = File.of(fileAndRank.get(FILE_INDEX));

		return Position.of(rank, file);
	}

	private static void validateLength(final String input) {
		if (input.length() != FORMAT_LENGTH) {
			throw new IllegalArgumentException(WRONG_LENGTH_ERROR);
		}
	}
}
