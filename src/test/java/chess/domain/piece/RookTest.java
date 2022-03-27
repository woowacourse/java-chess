package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;

public class RookTest {

	@ParameterizedTest
	@CsvSource(value = {
		"c4,c5",
		"c4,c3",
		"c4,d4",
		"c4,b4"
	})
	void move(String from, String to) {
		Board board = Board.create();
		Rook rook = new Rook(Symbol.ROOK, Team.WHITE);
		boolean actual = rook.isMovable(board.getValue(), Coordinate.of(from), Coordinate.of(to));
		assertThat(actual).isTrue();
	}
}
