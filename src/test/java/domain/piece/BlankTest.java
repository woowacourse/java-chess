package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @Test
    @DisplayName("빈공간 객체 생성")
    void create() {
        assertThat(Blank.INSTANCE.getPieceName()).isEqualTo(".");

    }
}
