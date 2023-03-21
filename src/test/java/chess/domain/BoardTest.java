package chess.domain;

import static chess.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = Board.create();
	}

	@Nested
	@DisplayName("movePiece 예외 발생 테스트")
	class MovePieceFailTest {

		@Test
		@DisplayName("움직이려는 source 위치에 말이 없다면 예외를 반환해야 한다.")
		void checkIsSourceEmptyTest() {
			Exception e = assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.WHITE, A3, A4));
			assertEquals("source 위치에 조작할 수 있는 말이 없습니다.", e.getMessage());
		}

		@Test
		@DisplayName("입력된 팀과 움직이려는 위치의 말이 다르다면 예외를 반환해야 한다.")
		void checkIsSourceCorrectTeamTest() {
			Exception e = assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.BLACK, A2, A4));
			assertEquals("다른 팀의 말을 조작할 수 없습니다.", e.getMessage());
		}

		@Test
		@DisplayName("목표 위치에 같은 팀이 존재한다면 예외를 반환해야 한다. 폰이 이동 후 룩이 같은 위치로 이동하는 경우")
		void checkIsSameTeamExistOnTargetTest() {
			board.movePiece(Team.WHITE, A2, A4);

			Exception e = assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.WHITE, A1, A4));
			assertEquals("같은 팀의 말의 위치로 이동할 수 없습니다.", e.getMessage());
		}

		@Test
		@DisplayName("움직일 말이 이동 가능한 움직임이어야 한다. 폰이 이동 후 룩이 대각선으로 이동하려는 경우")
		void checkIsMovableDirectionTest() {
			board.movePiece(Team.WHITE, B2, B4);

			Exception e = assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.WHITE, A1, B2));
			assertEquals("말이 해당 방향으로 이동할 수 없습니다.", e.getMessage());
		}

		@Test
		@DisplayName("나이트는 장애물에 관계 없이 움직일 수 있어야 한다.")
		void checkIsThereAnyObstacleTest2() {
			assertDoesNotThrow(() -> board.movePiece(Team.WHITE, B1, A3));
		}

		@Test
		@DisplayName("이동 방향으로 장애물이 존재하면 예외가 발생해야 한다. 초기 위치에서 비숍이 좌상단으로 두 칸 이동하려 하는 경우")
		void checkIsThereAnyObstacleTest1() {
			Exception e = assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.WHITE, C1, A3));
			assertEquals("말이 이동하려는 방향에 장애물이 있습니다.", e.getMessage());
		}

		@Test
		@DisplayName("적이 없는 상태에서 일반 폰이 대각선으로 이동하려 하면 예외가 발생해야 한다.")
		void checkIsSourcePawnMovingProperDiagonalTest1() {
			board.movePiece(Team.WHITE, B2, B3);

			Exception e = assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.WHITE, B3, A4));
			assertEquals("폰은 적이 존재할 때만 대각선으로 이동할 수 있습니다.", e.getMessage());
		}

		@Test
		@DisplayName("적이 있는 상태에선 폰이 대각선으로 이동할 수 있다. 백팀 폰이 흑팀 폰을 잡는 경우")
		void checkIsSourcePawnMovingProperDiagonalTest2() {
			board.movePiece(Team.BLACK, A7, A5);
			board.movePiece(Team.WHITE, B2, B4);

			assertDoesNotThrow(() -> board.movePiece(Team.WHITE, B4, A5));
		}

		@Test
		@DisplayName("적이 있는 상태에선 폰이 앞으로 이동할 수 없다.")
		void checkIsSourcePawnTargetingForwardEnemyTest() {

			board.movePiece(Team.BLACK, A7, A5);
			board.movePiece(Team.WHITE, A2, A4);

			assertThrows(IllegalArgumentException.class, () -> board.movePiece(Team.WHITE, A4, A5));
		}
	}

	@Nested
	@DisplayName("모든 폰의 초기 움직임 테스트")
	class PawnTest {

		@Test
		@DisplayName("처음에는 한 칸을 움직일 수 있어야 한다.")
		void pawnFirst1MoveTest() {
			int distance = 1;
			assertDoesNotThrow(() -> moveEveryPawnForward(distance));
		}

		@Test
		@DisplayName("처음에는 두 칸을 움직일 수 있어야 한다.")
		void pawnFirst2MoveTest() {
			int distance = 2;
			assertDoesNotThrow(() -> moveEveryPawnForward(distance));
		}

		@Test
		@DisplayName("처음이 아니라면 두 칸을 움직일 수 없어야 한다.")
		void pawnInvalid2MoveTest() {
			int initialMoveDistance = 1;
			moveEveryPawnForward(initialMoveDistance);

			int nextMoveDistance = 2;
			for (int i = 0; i < 7; i++) {
				int column = i;
				assertThrows(IllegalArgumentException.class, () ->
					board.movePiece(
						Team.WHITE,
						new Position(column, 2),
						new Position(column, 2 + nextMoveDistance)
					));
				assertThrows(IllegalArgumentException.class, () ->
					board.movePiece(
						Team.BLACK,
						new Position(column, 5),
						new Position(column, 5 - nextMoveDistance)
					));
			}
		}
	}

	private void moveEveryPawnForward(final int distance) {
		for (int i = 0; i < 7; i++) {
			board.movePiece(Team.WHITE, new Position(i, 1), new Position(i, 1 + distance));
			board.movePiece(Team.BLACK, new Position(i, 6), new Position(i, 6 - distance));
		}
	}
}
