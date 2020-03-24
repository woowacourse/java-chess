package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.PieceFactory;

public class PieceFactoryTest {
	@Test
	@DisplayName("말이 32개가 제대로 생성되는지 테스트")
	void pieceFactoryCreate() {
		PieceFactory pieceFactory = PieceFactory.getInstance();
		assertThat(pieceFactory.getPieces().size()).isEqualTo(32);
	}
}
