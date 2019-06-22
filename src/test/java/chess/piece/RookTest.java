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
		Rook rook = Rook.valueOf(Player.BLACK, new Position(5, 5));
		Position end = new Position(8, 5);

		Path path = new Path();
		path.add(new Position(6, 5));
		path.add(new Position(7, 5));

		assertThat(rook.getMovablePath(end)).isEqualTo(path);
	}

	@Test
	void End로_갈_수_없는_경우() {
		Rook rook = Rook.valueOf(Player.BLACK, new Position(5, 5));
		Position end = new Position(4, 7);

		assertThrows(NotFoundPathException.class, () -> rook.getMovablePath(end));
	}
}