package chess.domain.board;

import static chess.domain.piece.Direction.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.piece.Direction;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"b1", "B1"})
    @DisplayName("from 생성")
    void createFrom(String point) {
        assertThat(Position.from(point)).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("of 생성")
    void createOf() {
        assertThat(Position.of(Column.C, Row.SEVEN)).isEqualTo(Position.from("C7"));
    }

    @Test
    @DisplayName("잘못된 Point 인자")
    void createWithInvalidName() {
        assertThatThrownBy(() -> {
            Position.from("z1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(".+는 잘못된 입력입니다.");
    }

    @Test
    @DisplayName("점 대칭")
    void opposite() {
        assertThat(Position.from("b1").opposite()).isEqualTo(Position.from("g8"));
    }

    @Test
    @DisplayName("Position 전체 list 크기")
    void list() {
        assertThat(Position.list()).hasSize(64);
    }

    @Test
    @DisplayName("가로축 기준 대칭")
    void horizontalFlip() {
        assertThat(Position.from("b1").horizontalFlip()).isEqualTo(Position.from("b8"));
    }

    @ParameterizedTest
    @DisplayName("주어진 방향으로 이동했을 때의 포지션")
    @MethodSource("createDirection")
    void destinationOf(Direction direction, Position expected) {
        Position pivot = Position.from("d5");
        assertThat(pivot.nextPositionOf(direction).orElse(null)).isEqualTo(expected);
    }

    static Stream<Arguments> createDirection() {
        return Stream.of(
                Arguments.of(N, Position.from("d6")),
                Arguments.of(NE, Position.from("e6")),
                Arguments.of(E, Position.from("e5")),
                Arguments.of(SE, Position.from("e4")),
                Arguments.of(S, Position.from("d4")),
                Arguments.of(SW, Position.from("c4")),
                Arguments.of(W, Position.from("c5")),
                Arguments.of(NW, Position.from("c6"))
        );
    }

    @ParameterizedTest
    @DisplayName("주어진 방향으로 이동할 수 없을 경우")
    @MethodSource("createOutOfIndex")
    void destinationOutOfIndex(Direction direction, Position position) {
        assertThat(position.nextPositionOf(direction).orElse(null)).isEqualTo(null);
    }

    static Stream<Arguments> createOutOfIndex() {
        return Stream.of(
                Arguments.of(N, Position.from("h8")),
                Arguments.of(NE, Position.from("h8")),
                Arguments.of(E, Position.from("h8")),
                Arguments.of(SE, Position.from("h1")),
                Arguments.of(S, Position.from("h1")),
                Arguments.of(SW, Position.from("a2")),
                Arguments.of(W, Position.from("a5")),
                Arguments.of(NW, Position.from("a8"))
        );
    }
}