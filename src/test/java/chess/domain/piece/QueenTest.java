package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {
	@Test
	@DisplayName("퀸 생성")
	void constructor() {
		assertThat(new Queen(Position.from("d1"), Color.WHITE)).isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@DisplayName("퀸 이동")
	@CsvSource(value = {"e5,c5", "e5,e7", "e5,g7", "e5,e3", "e5,c3", "e5,c7", "e5,g5", "e5,g3"})
	void move(String source, String target) {
		Piece piece = new Queen(Position.from(source), Color.WHITE);
		piece.move(new Blank(Position.from(target)));
		assertThat(piece.getPosition()).isEqualTo(Position.from(target));
	}

	@ParameterizedTest
	@DisplayName("퀸이 이동할 수 없는 위치인 경우 예외 발생")
	@CsvSource(value = {"e5,c6", "e5,d7", "e5,f7", "e5,g6", "e5,g4", "e5,f3", "e5,c4", "e5,d3"})
	void move_invalid_direction(String source, String target) {
		Piece piece = new Queen(Position.from(source), Color.WHITE);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(
				() -> piece.move(new Blank(Position.from(target))));
	}
}
