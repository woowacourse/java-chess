package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.position.Position;

public class TurnTest {
	private Turn turn;

	@BeforeEach
	void setUp() {
		turn = new Turn(true);
	}

	@Test
	void validateTurnTest() {
		ChessPiece whitePawn = new Pawn(Position.of(2, 2), Team.WHITE);

		turn.validateTurn(whitePawn);
		turn.changeTurn();

		assertThatThrownBy(() -> turn.validateTurn(whitePawn))
			.isInstanceOf(UnsupportedOperationException.class);
	}
}
