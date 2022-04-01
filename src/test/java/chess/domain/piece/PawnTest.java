package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;

public class PawnTest {

	@ParameterizedTest
	@CsvSource(value = {
		"c2,c3",
		"c2,c4",
		"c3,c4"
	})
	void move(String from, String to) {
		Board board = Board.create();
		Pawn pawn = new Pawn(Symbol.PAWN, Team.WHITE);
		boolean actual = pawn.isMovable(board.getValue(), Coordinate.of(from), Coordinate.of(to));
		assertThat(actual).isTrue();
	}

	@Test
	void move2() {
		Board board = Board.create();
		Pawn pawn = new Pawn(Symbol.PAWN, Team.WHITE);
		boolean actual = pawn.isMovable(board.getValue(), Coordinate.of("c6"), Coordinate.of("c5"));
		assertThat(actual).isFalse();
	}

	@Test
	void move3() {
		Board board = Board.create();
		Pawn pawn = new Pawn(Symbol.PAWN, Team.WHITE);
		board = board.move("c2", "c4");
		board = board.move("c4", "c5");
		board = board.move("c5", "c6");

		boolean actual = pawn.isMovable(board.getValue(), Coordinate.of("c6"), Coordinate.of("d7"));

		assertThat(actual).isTrue();
	}
}
