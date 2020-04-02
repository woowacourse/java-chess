package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.PieceFactory;

public class PieceFactoryTest {

	@Test
	@DisplayName("PieceFactory가 제대로 32개의 말들을 생성하는지")
	void size() {
		Pieces pieces = PieceFactory.getPieces();
		assertThat(pieces.getAlivePieces().size()).isEqualTo(32);
	}
}
