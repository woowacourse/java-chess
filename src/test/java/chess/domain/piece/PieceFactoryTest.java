package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceFactoryTest {
	@Test
	@DisplayName("폰 생성")
	void create_pawn() {
		assertThat(PieceFactory.create("p", Position.from("a2"), Color.WHITE)).isInstanceOf(Pawn.class);
	}
	@Test
	@DisplayName("룩 생성")
	void create_rook() {
		assertThat(PieceFactory.create("r", Position.from("a1"), Color.WHITE)).isInstanceOf(Rook.class);
	}
	@Test
	@DisplayName("나이트 생성")
	void create_knight() {
		assertThat(PieceFactory.create("n", Position.from("b1"), Color.WHITE)).isInstanceOf(Knight.class);
	}
	@Test
	@DisplayName("비숍 생성")
	void create_bishop() {
		assertThat(PieceFactory.create("b", Position.from("c1"), Color.WHITE)).isInstanceOf(Bishop.class);
	}
	@Test
	@DisplayName("퀸 생성")
	void create_queen() {
		assertThat(PieceFactory.create("q", Position.from("d1"), Color.WHITE)).isInstanceOf(Queen.class);
	}
	@Test
	@DisplayName("킹 생성")
	void create_king() {
		assertThat(PieceFactory.create("k", Position.from("e1"), Color.WHITE)).isInstanceOf(King.class);
	}
}
