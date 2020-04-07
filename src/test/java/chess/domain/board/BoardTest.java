package chess.domain.board;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

class BoardTest {
	@Test
	void create_By_Factory() {
		assertThat(Board.of(BoardFactory.toList())).isInstanceOf(Board.class);
	}

	@Test
	void verifyMove_ThrowException_When_isNotSameTeam_Return_True() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(C8, new King(C8, Team.WHITE));
		setter.put(C7, new King(C7, Team.BLACK));

		setter.put(B1, new Rook(B1, Team.BLACK));

		Board board = Board.of(setter);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> board.verifyMove(B1, B2, Team.WHITE))
			.withMessage("아군 기물의 위치가 아닙니다.");
	}

	@Test
	void verifyMove_NotThrowException_When_isNotSameTeam_Return_False() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(C8, new King(C8, Team.WHITE));
		setter.put(C7, new King(C7, Team.BLACK));

		setter.put(B1, new Rook(B1, Team.BLACK));
		setter.put(B2, new Empty(B2, Team.NONE));
		setter.put(B3, new Empty(B3, Team.NONE));

		Board board = Board.of(setter);

		assertThatCode(() -> board.verifyMove(B1, B3, Team.BLACK))
			.doesNotThrowAnyException();
	}

	@Test
	void verifyMove_ThrowException_When_CanNotMoveTo_Return_True() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(C8, new King(C8, Team.WHITE));
		setter.put(C7, new King(C7, Team.BLACK));

		setter.put(B1, new Rook(B1, Team.BLACK));
		setter.put(D2, new Empty(D2, Team.NONE));

		Board board = Board.of(setter);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> board.verifyMove(B1, D2, Team.BLACK))
			.withMessage("이동할 수 없는 경로입니다.");
	}

	@Test
	void verifyMove_NotThrowException_When_CanNotMoveTo_Return_False() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(C8, new King(C8, Team.WHITE));
		setter.put(C7, new King(C7, Team.BLACK));

		setter.put(B1, new Rook(B1, Team.BLACK));
		setter.put(B2, new Empty(B2, Team.NONE));
		setter.put(B3, new Empty(B3, Team.NONE));

		Board board = Board.of(setter);

		assertThatCode(() -> board.verifyMove(B1, B3, Team.BLACK))
			.doesNotThrowAnyException();
	}

	@Test
	void verifyMove_ThrowException_When_hasPieceIn_Return_True() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(C8, new King(C8, Team.WHITE));
		setter.put(C7, new King(C7, Team.BLACK));

		setter.put(B1, new Rook(B1, Team.BLACK));
		setter.put(B2, new Rook(B2, Team.BLACK));
		setter.put(B3, new Empty(B3, Team.NONE));

		Board board = Board.of(setter);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> board.verifyMove(B1, B3, Team.BLACK))
			.withMessage("이동할 수 없는 경로입니다.");
	}

	@Test
	void verifyMove_NotThrowException_When_hasPieceIn_Return_False() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(C8, new King(C8, Team.WHITE));
		setter.put(C7, new King(C7, Team.BLACK));

		setter.put(B1, new Rook(B1, Team.BLACK));
		setter.put(B2, new Empty(B2));
		setter.put(B3, new Empty(B3));

		Board board = Board.of(setter);

		assertThatCode(() -> board.verifyMove(B1, B3, Team.BLACK))
			.doesNotThrowAnyException();
	}

	@Test
	void verifyMove_ThrowException_When_KingDead() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(A2, new King(A2, Team.BLACK));

		Board board = Board.of(setter);

		assertThatIllegalArgumentException()
			.isThrownBy(() -> board.verifyMove(A3, A4, Team.BLACK))
			.withMessage("게임 끝");
	}

	@Test
	void verifyMove_NotThrowException_When_BothKingAlive() {
		Map<Position, Piece> setter = new LinkedHashMap<>();
		setter.put(A3, new King(A3, Team.BLACK));
		setter.put(A4, new Empty(A4, Team.NONE));
		setter.put(A2, new King(A2, Team.WHITE));

		Board board = Board.of(setter);

		assertThatCode(() -> board.verifyMove(A3, A4, Team.BLACK))
			.doesNotThrowAnyException();
	}
}