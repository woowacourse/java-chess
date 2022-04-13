package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import chess.domain.board.Board;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;

class KingTest {

	private final King king = King.createBlack();

	@ParameterizedTest
	@CsvSource(value = {"3:4", "4:5", "5:5", "5:4", "3:3", "4:3", "3:5", "5:3"}, delimiter = ':')
	@DisplayName("King을 이동시킨다.")
	void moveKing(int row, int column) {
		Direction direction = king.matchDirection(
			new Position(4, 4),
			new Position(row, column));
		assertThat(direction).isInstanceOfAny(BasicDirection.class, DiagonalDirection.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"6:4", "6:5", "7:5", "2:4", "1:3", "4:1", "6:7", "1:2"}, delimiter = ':')
	@DisplayName("King을 이동시킬 수 없는 곳으로 이동시킨다.")
	void moveKingException(int row, int column) {
		assertThatThrownBy(() -> king.matchDirection(
			new Position(4, 4),
			new Position(row, column))
		).isInstanceOf(IllegalArgumentException.class);
	}
}