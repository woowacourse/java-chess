package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
	@DisplayName("File과 Rank가 같을 때 하나의 Position 인스턴스만 생성되는지 확인")
	@Test
	void newTest() {
		assertThat(Position.of(File.A, Rank.ONE)).isEqualTo(Position.of(File.A, Rank.ONE));
	}
}
