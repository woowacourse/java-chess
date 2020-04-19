package chess.web;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PieceNameConverterTest {

	@Test
	void of_InvalidChessPieceName_ExceptionThrown() {
		assertThatThrownBy(() -> PieceNameConverter.of("M"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스가 유효하지 않습니다.");
	}

	@Test
	void of_ChessPieceName_ReturnInstance() {
		assertThat(PieceNameConverter.of("P")).isInstanceOf(PieceNameConverter.class);
	}

}