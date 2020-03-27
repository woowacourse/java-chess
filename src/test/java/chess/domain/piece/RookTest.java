package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {
	@Test
	@DisplayName("룩 생성")
	void constructor() {
		assertThat(new Rook(Position.from("a1"), Color.WHITE)).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@DisplayName("룩 이동")
	@CsvSource(value = {"d4,d6", "d4,f4", "d4,d2", "d4,b4"})
	void move(String source, String target) {
		Piece piece = new Rook(Position.from(source), Color.WHITE);
		piece.move(new Blank(Position.from(target)));
		assertThat(piece.getPosition()).isEqualTo(Position.from(target));
	}

	@ParameterizedTest
	@DisplayName("룩이 이동할 수 없는 위치인 경우 예외 발생")
	@CsvSource(value = {"d4,c5", "d4,e5", "d4,e3", "d4,c3"})
	void move_invalid_direction(String source, String target) {
		Piece piece = new Rook(Position.from(source), Color.WHITE);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(
				() -> piece.move(new Blank(Position.from(target))));
	}

}
