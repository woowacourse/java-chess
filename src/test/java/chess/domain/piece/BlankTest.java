package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import chess.domain.piece.blank.Blank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class BlankTest {
	@Test
	@DisplayName("Blank가 움직이는 경우 정상적으로 예외를 반환하는지")
	void moveTest() {
		Blank blank = new Blank(Position.of(3, 4));
		assertThatThrownBy(() -> blank.move(Position.of(3, 4), Position.of(3, 5), new HashMap<Position, Team>()))
			.isInstanceOf(UnsupportedOperationException.class);
	}
}