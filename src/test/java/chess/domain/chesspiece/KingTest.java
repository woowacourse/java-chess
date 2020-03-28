package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

public class KingTest {
	@Test
	void validateMoveTest() {
		ChessPiece king = new King(Position.of(4, 4), Team.BLACK);
		king.validateCanGo(new Blank(Position.of(3, 4)));
		king.validateCanGo(new Blank(Position.of(4, 5)));
		king.validateCanGo(new Blank(Position.of(3, 3)));
		king.validateCanGo(new Blank(Position.of(4, 3)));
		king.validateCanGo(new Blank(Position.of(5, 3)));
		king.validateCanGo(new Blank(Position.of(5, 4)));
		king.validateCanGo(new Blank(Position.of(5, 5)));

		assertThatThrownBy(() -> king.validateCanGo(new Blank(Position.of(5, 6))))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
