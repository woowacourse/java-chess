package chess.piece;

import static chess.domain.piece.Color.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Name;

class NameTest {
	@DisplayName("이름 생성 확인")
	@Test
	void create() {
		Assertions.assertEquals(new Name("a", BLACK).getName(), "A");
		Assertions.assertEquals(new Name("A", WHITE).getName(), "a");
		Assertions.assertEquals(new Name("a", NOTHING).getName(), "a");
	}
}