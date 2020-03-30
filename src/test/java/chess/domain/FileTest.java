package chess.domain;

import chess.domain.chessPiece.position.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FileTest {
	@DisplayName("of 테스트")
	@Test
	void ofTest() {
		String input = "b";
		assertThat(File.of(input)).isEqualTo(File.B);
	}
}
