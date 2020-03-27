package chess.domain.chesspiece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.Position;
import chess.domain.Team;

public class ChessPieceTest {

	@Test
	void equalsPositionTest() {
		ChessPiece pawn = new Pawn(Position.of(2, 2), Team.BLACK);

		assertThat(pawn.equalsPosition(Position.of(2, 2))).isTrue();
	}
}
