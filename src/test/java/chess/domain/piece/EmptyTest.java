package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyTest {

    @DisplayName("Empty 객체 생성 확인")
    @Test
    void 빈_기물_객체_생성_테스트() {
        Piece empty = new Empty(".", Color.NONE);

        assertThat(empty.getName()).isEqualTo(".");
    }
}
