package chess.domain.piece.blank;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankTest {

    @Test
    @DisplayName("Blank를 움직이려하면 예외가 발생한다.")
    void moveBlank() {
        Blank blank = new Blank(new Position(1, 1));

        assertThatThrownBy(() -> blank.findPathTo(new Position(1, 2)))
                .hasMessage("해당 위치에 말이 없습니다.");
    }

    @Test
    @DisplayName("Blank를 업데이트하려고 하면 예외가 발생한다")
    void updateBlank() {
        Blank blank = new Blank(new Position(1, 1));

        assertThatThrownBy(() -> blank.update(new Position(1, 2)))
                .hasMessage("해당 위치에 말이 없습니다.");
    }
}
