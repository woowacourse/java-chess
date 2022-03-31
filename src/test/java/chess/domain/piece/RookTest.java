package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;

public class RookTest {

	private final Piece rook = Rook.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"1:4", "8:4", "6:4"}, delimiter = ':')
	@DisplayName("Rook을 이동시킨다.")
	void moveRook(int row, int column) {
		Direction direction = rook.matchDirection(
			new Position(4, 4),
			new Position(row, column));
		assertThat(direction).isInstanceOf(BasicDirection.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"2:3", "3:5"}, delimiter = ':')
	@DisplayName("Rook은 대각선으로 이동할 수 없다.")
	void moveRookInvalid(int row, int column) {
		assertThatThrownBy(() -> rook.matchDirection(
			new Position(4, 4),
			new Position(row, column))
		).isInstanceOf(IllegalArgumentException.class);
	}
}
