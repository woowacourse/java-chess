package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("rank 또는 file이 1~8이 아니면 예외가 발생한다.")
    void validateRange() {
        assertThatThrownBy(() -> new Position(0, 9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 위치입니다.");
    }

    @Test
    @DisplayName("새로운 위치를 반환한다.")
    void moveBy() {
        Position position = new Position(1, 2);
        assertThat(position.moveBy(1, 1)).isEqualTo(new Position(2, 3));
    }

    @Test
    @DisplayName("변경된 위치의 rank 또는 file 이 1~8이 아니면 예외가 발생한다.")
    void validateAddedPositionRange() {
        assertThatThrownBy(() -> new Position(8, 8).moveBy(1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 위치입니다.");
    }

    @Test
    @DisplayName("file 사이의 거리를 반환한다.")
    void fileDiff() {
        Position source = new Position(1, 1);
        Position target = new Position(1, 4);

        assertThat(source.fileDiff(target)).isEqualTo(3);
    }

    @Test
    @DisplayName("rank 사이의 거리를 반환한다.")
    void rankDiff() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 1);

        assertThat(source.rankDiff(target)).isEqualTo(3);
    }

    @Test
    @DisplayName("rank 가 같으면 true, 다르면 false 를 반환한다.")
    void isSameRank() {
        Position source = new Position(1, 1);

        assertAll(
                () -> assertThat(source.isSameRank(1)).isTrue(),
                () -> assertThat(source.isSameRank(2)).isFalse()
        );
    }
}
