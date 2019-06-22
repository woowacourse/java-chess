package chess.piece;

import chess.Path;
import chess.Player;
import chess.Position;
import chess.exception.NotFoundPathException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RookTest {
	@Test
	void End로_갈_수_있는_경우() {
		Rook rook = Rook.valueOf(Player.BLACK, Position.getPosition(5,5));
		Position end = Position.getPosition(8,5);

		Path path = new Path();
		path.add(Position.getPosition(6,5));
		path.add(Position.getPosition(7,5));

		assertThat(rook.getMovablePath(end)).isEqualTo(path);
	}

	@Test
	void End로_갈_수_없는_경우() {
		Rook rook = Rook.valueOf(Player.BLACK, Position.getPosition(5,5));
		Position end = Position.getPosition(4,7);

		assertThrows(NotFoundPathException.class, () -> rook.getMovablePath(end));
	}
}