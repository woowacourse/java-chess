package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @Test
    @DisplayName("위치를 생성할 수 있다.")
    void createPosition() {
        Position position = new Position(File.A, Rank.ONE);

        assertThat(position).isEqualTo(new Position(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("문자열을 통해서 위치를 생성할 수 있다.")
    void createStringToPosition() {
        String attribute = "a1";

        assertDoesNotThrow(() -> Position.from(attribute));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "z1", "a0", "a", "1"})
    @DisplayName("잘못된 위치를 입력한 경우 예외가 발생한다")
    void createInvalidStringToPosition(String value) {
        assertThatThrownBy(() -> Position.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
