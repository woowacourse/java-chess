package chess.web;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WebChessPieceTest {

	@Test
	void of_ChessPieceName_ReturnInstance() {
		assertThat(WebChessPiece.of("P")).isInstanceOf(WebChessPiece.class);
	}

	@Test
	void of_InvalidChessPieceName_ExceptionThrown() {
		assertThatThrownBy(() -> WebChessPiece.of("M"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 피스가 유효하지 않습니다.");
	}

}