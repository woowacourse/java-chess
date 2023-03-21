package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @DisplayName("C3 와 같은 형식으로 생성할 수 있다")
    @Test
    void createPosition_formLike_C3() {
        Position position = new Position("C3");

        assertThat(position)
                .isEqualTo(new Position(File.C, Rank.THREE));
    }

    @DisplayName("c3 와 같은 형식으로 생성할 수 있다")
    @Test
    void createPosition_formLike_c3() {
        Position position = new Position("c3");

        assertThat(position)
                .isEqualTo(new Position(File.C, Rank.THREE));
    }

    @DisplayName("올바르지 않은 형식의 포지션 입력시 예외를 던진다")
    @ParameterizedTest
    @ValueSource(strings = {"3c", "c10", "33", "cc"})
    void invalidFormPosition_throws(String position) {
        assertThatThrownBy(() -> new Position(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }

    @DisplayName("파일 변화량과 랭크 변화량으로 움직일 수 있다")
    @Test
    void move() {
        Position position = new Position("c3");

        assertThat(position.move(3, 3))
                .isEqualTo(new Position("f6"));
    }
}
