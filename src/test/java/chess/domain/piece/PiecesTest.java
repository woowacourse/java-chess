package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class PiecesTest {
	@DisplayName("정상적으로 생성되는지 확인")
	@Test
	void constructorTest() {
		assertThat(new Pieces(WhitePiecesFactory.create(), BlackPiecesFactory.create()))
			.isInstanceOf(Pieces.class)
			.isNotNull();
	}

	@DisplayName("화이트 폰의 source와 target포지션이 주어졌을 때 정상적으로 이동 경로를 반환하는지 확인")
	@Test
	void movingTraceTest() {
		Pieces pieces = new Pieces(WhitePiecesFactory.create(), BlackPiecesFactory.create());
		Position source = Position.of("a2");
		Position target = Position.of("a4");

		List<Position> trace = pieces.movingTrace(source, target);

		assertThat(trace.size()).isEqualTo(2);
		assertThat(trace.get(0)).isEqualTo(source);
		assertThat(trace.get(1)).isEqualTo(target);
	}

	@DisplayName("화이트 폰이 a2에서 a4로 이동할 수 있는지 확인")
	@Test
	void canMoveTest() {
		Pieces pieces = new Pieces(WhitePiecesFactory.create(), BlackPiecesFactory.create());
		Position source = Position.of("a2");
		Position target = Position.of("a4");

		assertThat(pieces.canMove(source, target)).isTrue();
	}

	@DisplayName("화이트 폰(a4)이 블랙폰(b5)의 위치로 이동할 수 있는지 확인")
	@Test
	void canMoveTest2() {
		Pieces pieces = new Pieces(WhitePiecesFactory.create(), BlackPiecesFactory.create());
		pieces.move(Position.of("a2"), Position.of("a4"));
		pieces.move(Position.of("b7"), Position.of("b5"));

		Position source = Position.of("a4");
		Position target = Position.of("b5");

		assertThat(pieces.canMove(source, target)).isTrue();
	}

	@DisplayName("생성하고 바로 왕이 죽었는지 물어봤을 때 false를 반환하는지 확인")
	@Test
	void isKingDieTest() {
		Pieces pieces = new Pieces(WhitePiecesFactory.create(), BlackPiecesFactory.create());

		assertThat(pieces.isKingDie()).isFalse();
	}
}