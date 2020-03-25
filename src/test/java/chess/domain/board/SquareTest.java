package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTest {
	@DisplayName("Square 클래스 생성할 때 정상적으로 생성되는지 확인")
	@Test
	void ofTest1() {
		assertThat(Square.of(File.A, Rank.ONE)).isInstanceOf(Square.class);
	}

	@DisplayName("File과 Rank가 같을 때 하나의 Square 인스턴스만 생성되는지 확인")
	@Test
	void ofTest2() {
		assertThat(Square.of(File.A, Rank.ONE)).isEqualTo(Square.of(File.A, Rank.ONE));
	}
}
