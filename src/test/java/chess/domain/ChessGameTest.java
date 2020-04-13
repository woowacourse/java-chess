package chess.domain;

import static chess.domain.Side.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class ChessGameTest {
	private ChessGame chessGame;

	@BeforeEach
	void setUp() {
		chessGame = ChessGame.start();
	}

	@DisplayName("move에서 같은 팀의 기물을 이동")
	@Test
	void move() {
		Position b2 = new Position("b", 2);
		Position b4 = new Position("b", 4);
		chessGame.move(b2, b4);
	}

	@DisplayName("validateTurn에서 다른 팀의 기물을 이동하려고 하는 경우 예외 발생")
	@Test
	void validateTurn() {
		Position b7 = new Position("b", 7);
		Position b5 = new Position("b", 5);
		assertThatThrownBy(() -> chessGame.move(b7, b5)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("changeTurn에서 이동 후 turn변경")
	@Test
	void changeTurn() {
		Position b2 = new Position("b", 2);
		Position b4 = new Position("b", 4);
		chessGame.move(b2, b4);
		assertThat(BLACK).isEqualTo(chessGame.getTurn());
	}

	@DisplayName("status에서 현재 점수 반환")
	@Test
	void status() {
		assertThat(chessGame.status(WHITE)).isEqualTo(38.0);
	}
}