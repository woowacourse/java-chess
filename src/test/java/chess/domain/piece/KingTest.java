package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"e4", "f4", "g4", "g5", "g6", "f6", "e6", "e5"})
    @DisplayName("이동경로에 기물이 존재하지 않으면 목표 지점에 이동한다.")
    void move(final String targetPositionValue) {
        //given
        final King king = new King(TeamColor.BLACK, Position.from("f5"));
        final Position targetPosition = Position.from(targetPositionValue);
        final Piece moved = king.move(new ArrayList<>(), targetPosition);
        //when
        final boolean actual = moved.hasPosition(targetPosition);
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목표 지점이 갈 수 없는 곳이면 예외를 발생시킨다.")
    void moveExceptionTarget() {
        //given
        final King king = new King(TeamColor.BLACK, Position.from("a1"));
        final Position targetPosition = Position.from("c1");
        //when, then
        assertThatThrownBy(() -> king.move(new ArrayList<>(), targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }
}
