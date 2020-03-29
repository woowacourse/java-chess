package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class BoardTest {
	@DisplayName("보드가 하나의 인스턴스만 생성하는지 확인")
	@Test
	void getInstance() {
		assertThat(Board.getInstance()).isEqualTo(Board.getInstance());
	}

	@DisplayName("움직여야하는 경로가 주어졌을 때 보드가 다른 말로 막혀있지 않으면 true 반환하는지 확인")
	@Test
	void canMoveBy() {
		Board board = Board.getInstance();
		List<Position> trace = new LinkedList<>();

		trace.add(Position.of("a5"));
		trace.add(Position.of("b5"));
		trace.add(Position.of("c5"));
		trace.add(Position.of("d5"));

		assertThat(board.canMoveBy(trace)).isTrue();
	}

	@DisplayName("Source와 Target 포지션이 주어졌을 떄 change가 올바르게 되는지 확인")
	@Test
	void change() {
		Map<Position, Piece> board = Board.getInstance().getBoard();
		Piece sourcePiece = board.get(Position.of("a5"));
		Piece targetPiece = board.get(Position.of("b5"));

		Board.getInstance().change(Position.of("a5"), Position.of("b5"));

		assertThat(board.get(Position.of("a5"))).isEqualTo(targetPiece);
		assertThat(board.get(Position.of("b5"))).isEqualTo(sourcePiece);
	}
}