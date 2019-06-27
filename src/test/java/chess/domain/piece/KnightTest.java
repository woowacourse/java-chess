package chess.domain.piece;

import chess.domain.Path;
import chess.domain.Player;
import chess.domain.Position;
import chess.exception.NotFoundPathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
	@Test
	void End로_갈_수_있는_경우() {
		Knight knight = Knight.valueOf(Player.BLACK, Position.getPosition(5, 5));
		Position end = Position.getPosition(6, 7);

		Path path = new Path();

		assertThat(knight.getMovablePath(end)).isEqualTo(path);
	}

	@Test
	void End로_갈_수_없는_경우() {
		Knight knight = Knight.valueOf(Player.BLACK, Position.getPosition(5, 5));
		Position end = Position.getPosition(4, 8);

		assertThrows(NotFoundPathException.class, () -> knight.getMovablePath(end));
	}
}