package chess.piece;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
	@DisplayName("팀과 위치를 입력받아 Pawn객체 생성 테스트")
	@Test
	void pawnCreateTest() {
		Pawn pawn = new Pawn(Team.BLACK, Position.of(File.A, Rank.ONE));
		assertThat(pawn).isInstance(Pawn.class);
	}
}
