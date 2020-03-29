package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessgame.ChessMenu;
import chess.domain.position.Position;

public class ChessMenuTest {

	@Test
	void create() {
		assertThatThrownBy(() -> new ChessMenu("asd"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void validateStartTest() {
		ChessMenu chessMenu = new ChessMenu("move b1 b2");
		assertThatThrownBy(() -> chessMenu.validateStart())
			.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void getPositionTest() {
		ChessMenu chessMenu = new ChessMenu("move h1 c5");

		assertThat(chessMenu.createStartPosition()).isEqualTo(Position.of(1, 8));
		assertThat(chessMenu.createTargetPosition()).isEqualTo(Position.of(5, 3));
	}
}
