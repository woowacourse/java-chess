package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

public class PieceTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(BLACK);
		assertThat(pawn).isInstanceOf(Pawn.class);
	}

	@Test
	void rookPathTest() {
		Rook rook = new Rook(BLACK);
		List<Position> actual = rook.findReachablePositions(Position.of(B, FOUR), Position.of(B, SIX));
		assertThat(actual).containsExactlyInAnyOrder(Position.of(B, FOUR), Position.of(B, FIVE), Position.of(B, SIX));
	}
}
