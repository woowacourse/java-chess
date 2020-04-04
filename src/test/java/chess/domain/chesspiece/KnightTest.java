package chess.domain.chesspiece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

public class KnightTest {

	@Test
	void validateMoveTest() {
		ChessPiece knight = new Knight(Position.of(4, 4), Team.BLACK);
		knight.validateCanGo(new Blank(Position.of(6, 5)));
		knight.validateCanGo(new Blank(Position.of(6, 3)));
		knight.validateCanGo(new Blank(Position.of(5, 6)));
		knight.validateCanGo(new Blank(Position.of(5, 2)));
		knight.validateCanGo(new Blank(Position.of(3, 2)));
		knight.validateCanGo(new Blank(Position.of(3, 6)));
		knight.validateCanGo(new Blank(Position.of(2, 3)));
		knight.validateCanGo(new Blank(Position.of(2, 5)));

		assertThatThrownBy(() -> knight.validateCanGo(new Blank(Position.of(5, 5))))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
