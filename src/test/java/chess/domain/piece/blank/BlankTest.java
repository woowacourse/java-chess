package chess.domain.piece.blank;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankTest {

    @Test
    @DisplayName("Blank를 움직이려하면 예외가 발생한다.")
    void moveBlank() {
        Blank blank = new Blank();

        assertThatThrownBy(() -> blank.findPathTo(new Position(1, 1), new Position(1, 2)))
                .hasMessage("해당 위치에 말이 없습니다.");
    }
}
