package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
	@DisplayName("of 메서드에 대해서 File과 Rank가 같을 때 하나의 Position 인스턴스만 생성되는지 확인")
	@Test
	void ofTest() {
		assertThat(Position.of("a1")).isEqualTo(Position.of("a1"));
		assertThat(Position.of(File.A, Rank.ONE)).isEqualTo(Position.of("a1"));
		assertThat(Position.of(8, 1)).isEqualTo(Position.of("a1"));
	}
}
