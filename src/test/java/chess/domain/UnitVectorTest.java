package chess.domain;

import static chess.domain.UnitVector.DOWN;
import static chess.domain.UnitVector.DOWN_LEFT;
import static chess.domain.UnitVector.DOWN_RIGHT;
import static chess.domain.UnitVector.LEFT;
import static chess.domain.UnitVector.RIGHT;
import static chess.domain.UnitVector.UP;
import static chess.domain.UnitVector.UP_LEFT;
import static chess.domain.UnitVector.UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnitVectorTest {

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_up() {
        Position current = new Position(5, 1);
        Position target = new Position(4, 1);

        assertThat(UnitVector.of(current, target)).isEqualTo(UP);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_down() {
        Position current = new Position(0, 0);
        Position target = new Position(1, 0);

        assertThat(UnitVector.of(current, target)).isEqualTo(DOWN);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_left() {
        Position current = new Position(0, 1);
        Position target = new Position(0, 0);

        assertThat(UnitVector.of(current, target)).isEqualTo(LEFT);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_right() {
        Position current = new Position(0, 0);
        Position target = new Position(0, 1);

        assertThat(UnitVector.of(current, target)).isEqualTo(RIGHT);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_upRight() {
        Position current = new Position(1, 0);
        Position target = new Position(0, 1);

        assertThat(UnitVector.of(current, target)).isEqualTo(UP_RIGHT);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_downRight() {
        Position current = new Position(0, 0);
        Position target = new Position(1, 1);

        assertThat(UnitVector.of(current, target)).isEqualTo(DOWN_RIGHT);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_downLeft() {
        Position current = new Position(0, 1);
        Position target = new Position(1, 0);

        assertThat(UnitVector.of(current, target)).isEqualTo(DOWN_LEFT);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치를 받아 단위 벡터를 계산해준다.")
    void ofTest_upLeft() {
        Position current = new Position(1, 1);
        Position target = new Position(0, 0);

        assertThat(UnitVector.of(current, target)).isEqualTo(UP_LEFT);
    }
}
