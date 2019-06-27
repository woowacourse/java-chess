package chess.domain.piece;

import chess.domain.Path;
import chess.domain.Player;
import chess.domain.Position;
import chess.exception.NotFoundPathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
	@Test
	void End로_갈_수_있는_경우() {
		Queen queen = Queen.valueOf(Player.BLACK, Position.getPosition(5, 5));
		Position end = Position.getPosition(8, 8);

		Path path = new Path();
		path.add(Position.getPosition(6, 6));
		path.add(Position.getPosition(7, 7));

		assertThat(queen.getMovablePath(end)).isEqualTo(path);
	}

	@Test
	void End로_갈_수_없는_경우() {
		Queen queen = Queen.valueOf(Player.BLACK, Position.getPosition(5, 5));
		Position end = Position.getPosition(4, 7);

		assertThrows(NotFoundPathException.class, () -> queen.getMovablePath(end));
	}
}