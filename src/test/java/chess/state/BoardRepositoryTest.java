package chess.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Board;

class BoardRepositoryTest {
	@Test
	@DisplayName("BoardRepository 가 정상적으로 Board를 생성해주는지 테스트합니다.")
	void createBoardTest() {
		assertThat(BoardRepository.create()).isInstanceOf(Board.class);
	}
}