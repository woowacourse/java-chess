package chess.domain.piece;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceScoreTest {

	@ParameterizedTest
	@MethodSource("createColumn")
	void calculateScoreOf(List<Piece> column, double expected) {
		assertThat(PieceScore.calculateScoreOf(column)).isEqualTo(expected);
	}

	private static Stream<Arguments> createColumn() {
		return Stream.of(
			Arguments.of(List.of(
				new Queen(A1, Team.WHITE),
				new Pawn(A2, Team.WHITE)), 10.0),
			Arguments.of(List.of(
				new King(A1, Team.WHITE),
				new Pawn(A2, Team.WHITE),
				new Knight(A3, Team.WHITE)), 3.5),
			Arguments.of(List.of(
				new Pawn(A2, Team.WHITE),
				new Pawn(A3, Team.WHITE),
				new Pawn(A4, Team.WHITE)), 1.5)
		);
	}
}