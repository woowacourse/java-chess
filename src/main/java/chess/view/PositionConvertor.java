package chess.view;

import chess.domain.board.Position;

public class PositionConvertor {

	public static Position to(final String s) {
		if (Character.isDigit(s.charAt(1)) && Character.isAlphabetic(s.charAt(0))) {
			int col = s.charAt(0) - 'a' + 1;
			int row = s.charAt(1) - '0';
			return Position.of(row, col);
		}
		throw new IllegalArgumentException("올바르지 않은 좌표 입력입니다.");
	}
}
