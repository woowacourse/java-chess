package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @Test
    @DisplayName("빈공간 객체 생성")
    void create() {
        assertThat(Blank.INSTANCE.getPieceName()).isEqualTo(".");
    }

    @Test
    @DisplayName("공백은 움직일 수 없다.")
    void fail_move_position() {
        Piece piece = Blank.INSTANCE;

        assertThatThrownBy(() -> {
            piece.setCurrentPosition(Position.valueOf("b3"));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
