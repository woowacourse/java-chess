package chess.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import chess.domain.Position;

class ChessServiceTest {

	private ChessService service;

	@BeforeEach
	void beforeEach() {
		service = new ChessService();
	}

	@Nested
	@DisplayName("서비스 상태 관련 테스트")
	class ServiceStateTest {

		@Test
		@DisplayName("초기화 이전에 move를 시도할 경우 예외가 발생해야 한다.")
		void moveBeforeInitializeErrorTest() {
			Position b1 = new Position(1, 1);
			Position b3 = new Position(1, 3);

			Exception e = assertThrows(IllegalStateException.class,
				() -> service.movePiece(b1, b3));
			assertEquals("현재 상태에서 불가능한 명령입니다.", e.getMessage());
		}

		@Test
		@DisplayName("초기화 이후에는 각 팀이 폰을 두 칸 전진시킬 수 있어야 한다.")
		void movePawnForward2EachTest() {
			service.initialize();
			Position b2 = new Position(1, 1);
			Position b4 = new Position(1, 3);
			Position b7 = new Position(1, 6);
			Position b5 = new Position(1, 4);

			assertDoesNotThrow(() -> {
				service.movePiece(b2, b4);
				service.movePiece(b7, b5);
			});
		}
	}
}
