package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

import chess.domain.board.Position;

public class PieceFactoryTest {
	Map<Position, Piece> board;

	@BeforeEach
	void setUp() {
		board = new HashMap<>();
		PieceFactory.create(board);
	}

/*	@DisplayName("폰이 정상적으로 초기화됐는지 위치 확인")
	@Test
	void initPawnTest() {
		assertThat()
	}

	@DisplayName("룩이 정상적으로 초기화됐는지 위치 확인")
	@Test
	void initRookTest() {
		assertThat()
	}

	@DisplayName("비숍이 정상적으로 초기화됐는지 위치 확인")
	@Test
	void initTest() {
		assertThat()
	}

	@DisplayName("나이트이 정상적으로 초기화됐는지 위치 확인")
	@Test
	void initTest() {
		assertThat()
	}

	@DisplayName("퀸이 정상적으로 초기화됐는지 위치 확인")
	@Test
	void initTest() {
		assertThat()
	}

	@DisplayName("킹이 정상적으로 초기화됐는지 위치 확인")
	@Test
	void initTest() {
		assertThat()
	}*/
}
