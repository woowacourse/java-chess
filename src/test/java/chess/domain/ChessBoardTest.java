package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.piece.Piece;

class ChessBoardTest {
	@DisplayName("생성자를 호출한 경우 ChessBoard 인스턴스 생성")
	@ParameterizedTest
	@NullAndEmptySource
	void construct(List<Piece> pieces) {
		assertThatThrownBy(() -> new ChessBoard(pieces))
				.isInstanceOf(IllegalArgumentException.class);
	}
}