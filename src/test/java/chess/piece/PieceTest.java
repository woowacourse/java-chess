package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.position.Position;

public class PieceTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(BLACK, Position.of(A, ONE));
		assertThat(pawn).isInstanceOf(Pawn.class);
	}
}
