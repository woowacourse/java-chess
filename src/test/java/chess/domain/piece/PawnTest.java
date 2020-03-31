package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Position;

public class PawnTest {
	@ParameterizedTest
	@DisplayName("폰이 움직일 수 없는 위치가 들어갔을 때 예외를 잘 처리하는지 확인")
	@CsvSource(value = {"WHITE,d2", "BLACK,d4", "WHITE,e3", "BLACK,a7"})
	void invalidQueenMove(Team team, String destination) {
		Pawn pawn = new Pawn(new Position("d3"), team);
		assertThatThrownBy(() ->
			pawn.move(new Position(destination))
		).isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@DisplayName("폰이 제대로 움직이는지 확인")
	@CsvSource(value = {"WHITE,e3", "WHITE,e4", "BLACK,e1"})
	void pawnMove(Team team, String destination) {
		Pawn pawn = new Pawn(new Position("e2"), team);
		pawn.move(new Position(destination));
	}
}
