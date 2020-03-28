package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KnightTest {
	@Test
	@DisplayName("나이트 생성")
	void constructor() {
		assertThat(new Knight(Position.from("b1"), Color.WHITE)).isInstanceOf(Knight.class);
	}

	@ParameterizedTest
	@DisplayName("나이트 이동")
	@CsvSource(value = {"d4,b5", "d4,c6", "d4,e6", "d4,f5", "d4,f3", "d4,e2", "d4,c2", "d4,b3"})
	void move(String source, String target) {
		Piece piece = new Knight(Position.from(source), Color.WHITE);
		piece.move(new Blank(Position.from(target)));
		assertThat(piece.getPosition()).isEqualTo(Position.from(target));
	}

	@ParameterizedTest
	@DisplayName("나이트가 이동할 수 없는 위치인 경우 예외 발생")
	@CsvSource(value = {"d4,c4", "d4,e4", "d4,d3", "d4,d5", "d4,c5", "d4,e5", "d4,e3", "d4,c3"})
	void move_invalid_direction(String source, String target) {
		Piece piece = new Knight(Position.from(source), Color.WHITE);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(
				() -> piece.move(new Blank(Position.from(target))));
	}
}
