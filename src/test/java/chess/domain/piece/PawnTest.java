package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {
	@Test
	@DisplayName("폰 생성")
	void constructor() {
		assertThat(new Pawn(Position.from("a2"), Color.WHITE)).isInstanceOf(Pawn.class);
	}

	@Test
	@DisplayName("폰의 이동 가능한 경로가 존재")
	void pathTo() {
		Piece piece = new Pawn(Position.from("a2"), Color.WHITE);
		assertThat(piece.pathTo(new Blank(Position.from("a3")))).isInstanceOf(Path.class);
	}

	@Test
	@DisplayName("폰의 대각선에 적이 있는 경우 경로가 존재")
	void pathTo_enemy() {
		Piece piece = new Pawn(Position.from("a2"), Color.WHITE);
		assertThat(piece.pathTo(new Pawn(Position.from("b3"), Color.BLACK))).isInstanceOf(Path.class);
	}

	@ParameterizedTest
	@DisplayName("폰의 이동 가능한 경로가 아닌 경우 예외 발생")
	@CsvSource(value = {"a2,a4", "a2,b3"})
	void pathTo_invalid_direction(String source, String target) {
		Piece piece = new Pawn(Position.from(source), Color.WHITE);
		assertThatExceptionOfType(RuntimeException.class).isThrownBy(
				() -> piece.pathTo(new Blank(Position.from(target))));
	}
}
