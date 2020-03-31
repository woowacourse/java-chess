package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.PiecesManager;
import chess.domain.piece.WhitePiecesFactory;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class GameTest {
	@DisplayName("첫 턴에 길이 막힌 기물을 움직이려고 할 때 예외처리 확인")
	@Test
	void movePieceFromTo() {
		Game game = new Game(new PiecesManager(WhitePiecesFactory.create(), BlackPiecesFactory.create()),
			Board.getInstance());

		assertThatThrownBy(() -> game.movePieceFromTo(Position.of("a1"), Position.of("a5")))
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessageContaining("해당 위치");
	}

	@DisplayName("왕이 살아있을 때 false를 반환하는지 확인")
	@Test
	void isKingDie() {
		Game game = new Game(new PiecesManager(WhitePiecesFactory.create(), BlackPiecesFactory.create()),
			Board.getInstance());

		assertThat(game.isKingDie()).isFalse();
	}
}