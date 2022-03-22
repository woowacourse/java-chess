package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Symbol;
import chess.domain.piece.Team;

class InitialBoardTest {
	//todo : 나중에 바꿔보기
	private Map<Coordinate, Piece> map = InitialBoard.init();

	@ParameterizedTest(name = "{0}{1}에 {2}가 위치한다.")
	@MethodSource("provideParameters")
	@DisplayName("A")
	void test(List<Column> columns, List<Row> rows, Piece piece) {
		for (Column column : columns) {
			for (Row row : rows) {
				assertThat(map.get(Coordinate.of(column, row))).isEqualTo(piece);
			}
		}
	}

	private static Stream<Arguments> provideParameters() {
		return Stream.of(
			Arguments.arguments(Arrays.asList(Column.A, Column.H),
				Arrays.asList(Row.EIGHT), new Rook(Symbol.ROOK, Team.BLACK)
			)
		);
	}
}
