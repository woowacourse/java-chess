package chess.piece;

import chess.domain.piece.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Color.*;

class NameTest {
    @DisplayName("이름 생성 확인")
    @Test
    void create() {
        Assertions.assertEquals(new Name("a", BLACK).getName(), "A");
        Assertions.assertEquals(new Name("A", WHITE).getName(), "a");
        Assertions.assertEquals(new Name("a", NOTHING).getName(), "a");
    }
}