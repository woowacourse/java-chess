package chess.domain;

import static chess.domain.TestUtils.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class BoardTest {
	@Test
	@DisplayName("목표하는 위치에 같은 팀의 말이 있으면 에러를 반환한다")
	void errorPosition_SameTeam() {
		Map<Square, Piece> board = createBlankBoard();
		board.put(new Square("c3"), WHITE_QUEEN);
		board.put(new Square("d4"), WHITE_QUEEN);
		Board chessBoard = new Board(board);
		assertThatThrownBy(() -> chessBoard.checkCanMove(new Square("c3"), new Square("d4")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 사격 중지!! 아군이다!! ><");
	}

	@Test
	@DisplayName("목표한 위치가 이동할 수 없는 곳이면 에러를 반환한다")
	void errorPosition_Incapable() {
		Map<Square, Piece> board = createBlankBoard();
		board.put(new Square("c3"), WHITE_QUEEN);
		Board chessBoard = new Board(board);
		assertThatThrownBy(() -> chessBoard.checkCanMove(new Square("c3"), new Square("e6")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 허걱... 거긴 못가... 미안..");
	}

	@Test
	@DisplayName("가는 길에 다른 피스가 있으면 에러를 반환한다")
	void errorDirection_Blocked() {
		Map<Square, Piece> board = createBlankBoard();
		board.put(new Square("c3"), WHITE_QUEEN);
		board.put(new Square("d4"), WHITE_QUEEN);
		Board chessBoard = new Board(board);
		assertThatThrownBy(() -> chessBoard.checkCanMove(new Square("c3"), new Square("e5")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 길이 막혔다...!");
	}
}
