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
        Assertions.assertEquals("A", new Name("a", BLACK).getName());
        Assertions.assertEquals("a", new Name("A", WHITE).getName());
        Assertions.assertEquals("a", new Name("a", NOTHING).getName());
    }
}