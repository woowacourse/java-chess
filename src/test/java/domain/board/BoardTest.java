package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.piece.position.InvalidPositionException;
import domain.piece.team.Team;

public class BoardTest {
	private static Board board;

	@BeforeEach
	void setUp() {
		board = BoardFactory.create();
	}

	@DisplayName("입력한 source 위치에 말이 없으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "c5", "f6"})
	void move_InvalidPosition_ExceptionThrown(String sourcePosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, "a1", Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_SOURCE_POSITION);
	}

	@DisplayName("현재 턴과 움직일 말의 팀이 일치하지 않으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "h1", "e2"})
	void move_InvalidTurn_ExceptionThrown(String sourcePosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, "b1", Team.BLACK))
			.isInstanceOf(InvalidTurnException.class)
			.hasMessage(InvalidTurnException.INVALID_TURN);
	}

	@Test
	void name() {
	}
}