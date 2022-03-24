package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

@DisplayName("Pawn 테스트")
class PawnTest {

	@DisplayName("입력된 방향에 대해")
	@Nested
	class DirectionTest {

		@DisplayName("유효하지 않으면 예외를 반환한다.")
		@Test
		void invalid_Direction() {
			Position current = new Position(Row.SECOND, Column.a);
			Position invalidTarget = new Position(Row.THIRD, Column.c);
			Pawn pawn = new Pawn(Color.BLACK);

			assertThatThrownBy(() -> pawn.findValidDirection(current, invalidTarget));
		}

		@DisplayName("유효하면 방향 객체를 반환한다.")
		@Test
		void valid_Direction() {
			Position current = new Position(Row.FIRST, Column.a);
			Position target = new Position(Row.SECOND, Column.a);
			Pawn pawn = new Pawn(Color.WHITE);

			Direction actual = pawn.findValidDirection(current, target);

			assertThat(actual).isEqualTo(Direction.N);
		}
	}

	@DisplayName("입력된 범위에 대해")
	@Nested
	class RangeTest {

		@DisplayName("유효하지 않으면 예외를 반환한다.")
		@Test
		void invalid_Range() {
			Position current = new Position(Row.FIRST, Column.a);
			Position invalidTarget = new Position(Row.THIRD, Column.a);
			Pawn pawn = new Pawn(Color.WHITE);

			assertThatThrownBy(() -> pawn.findValidDirection(current, invalidTarget));
		}

		@DisplayName("유효하면 방향 객체를 반환한다.")
		@ParameterizedTest
		@CsvSource(value = {"SECOND, FOURTH", "FOURTH, FIFTH"})
		void valid_Direction(Row start, Row end) {
			Position current = new Position(start, Column.a);
			Position target = new Position(end, Column.a);
			Pawn pawn = new Pawn(Color.WHITE);

			Direction actual = pawn.findValidDirection(current, target);

			assertThat(actual).isEqualTo(Direction.N);
		}
	}
}