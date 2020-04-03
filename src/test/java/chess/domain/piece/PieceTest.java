package chess.domain.piece;

import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {
	@DisplayName("해당 기물이 인자값을 Team으로 가지고 있으면 참 반환")
	@ParameterizedTest
	@CsvSource({"WHITE,true", "BLACK,false", "NONE,false"})
	void isRightTeamTest(Team team, boolean expected) {
		Piece piece = new Pawn(WHITE);
		boolean actual = piece.isRightTeam(team);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("해당 기물이 킹인 경우, true 반환")
	@ParameterizedTest
	@MethodSource("isKingTestpieceSet")
	void isKingTest(Piece target, boolean expected) {
		boolean actual = target.isKing();
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> isKingTestpieceSet() {
		return Stream.of(
			Arguments.of(new King(BLACK), true),
			Arguments.of(new King(WHITE), true),
			Arguments.of(new Queen(BLACK), false),
			Arguments.of(new Queen(WHITE), false),
			Arguments.of(new Rook(BLACK), false),
			Arguments.of(new Rook(WHITE), false),
			Arguments.of(new Bishop(BLACK), false),
			Arguments.of(new Bishop(WHITE), false),
			Arguments.of(new Knight(BLACK), false),
			Arguments.of(new Knight(WHITE), false),
			Arguments.of(new Pawn(BLACK), false),
			Arguments.of(new Pawn(WHITE), false)
		);
	}

	@DisplayName("해당 기물이 폰인 경우, true 반환")
	@ParameterizedTest
	@MethodSource("isPawnTeestpieceSet")
	void isPawnTest(Piece target, boolean expected) {
		boolean actual = target.isPawn();
		assertThat(actual).isEqualTo(expected);
	}

	private static Stream<Arguments> isPawnTeestpieceSet() {
		return Stream.of(
			Arguments.of(new King(BLACK), false),
			Arguments.of(new King(WHITE), false),
			Arguments.of(new Queen(BLACK), false),
			Arguments.of(new Queen(WHITE), false),
			Arguments.of(new Rook(BLACK), false),
			Arguments.of(new Rook(WHITE), false),
			Arguments.of(new Bishop(BLACK), false),
			Arguments.of(new Bishop(WHITE), false),
			Arguments.of(new Knight(BLACK), false),
			Arguments.of(new Knight(WHITE), false),
			Arguments.of(new Pawn(BLACK), true),
			Arguments.of(new Pawn(WHITE), true)
		);
	}
}