package chess.domain.chesspiece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

public class KnightTest {

	@Test
	void validateMoveTest() {
		ChessPiece knight = new Knight(Position.of(4, 4), Team.BLACK);
		knight.validateMove(new Blank(Position.of(6, 5)));
		knight.validateMove(new Blank(Position.of(6, 3)));
		knight.validateMove(new Blank(Position.of(5, 6)));
		knight.validateMove(new Blank(Position.of(5, 2)));
		knight.validateMove(new Blank(Position.of(3, 2)));
		knight.validateMove(new Blank(Position.of(3, 6)));
		knight.validateMove(new Blank(Position.of(2, 3)));
		knight.validateMove(new Blank(Position.of(2, 5)));

		assertThatThrownBy(() -> knight.validateMove(new Blank(Position.of(5, 5))))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
