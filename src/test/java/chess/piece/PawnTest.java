package chess.piece;

import static chess.piece.Team.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.position.Position;

public class PawnTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(BLACK);
		assertThat(pawn).isInstanceOf(Pawn.class);
	}

	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,P", "WHITE,p"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Pawn(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}

	@DisplayName("맨 처음 움직임 이후부터는 2칸을 움직였을때 예외가 발생하는지 테스트")
	@Test
	void afterInitialMoveTest() {
		Pawn pawn = new Pawn(BLACK);

	}
}
