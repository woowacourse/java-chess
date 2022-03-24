package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"4d", "3e", "4h", "3g", "6h", "7g", "6d", "7e"})
    @DisplayName("이동경로에 기물이 존재하지 않으면 목표 지점에 이동한다.")
    void move(final String targetPositionValue) {
        //given
        final Knight knight = new Knight(TeamColor.BLACK, Position.from("5f"));
        final Position targetPosition = Position.from(targetPositionValue);
        final Piece moved = knight.move(new ArrayList<>(), targetPosition);
        //when
        final boolean actual = moved.hasPosition(targetPosition);
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목표 지점이 갈 수 없는 곳이면 예외를 발생시킨다.")
    void moveExceptionTarget() {
        //given
        final Knight knight = new Knight(TeamColor.BLACK, Position.from("1a"));
        final Position targetPosition = Position.from("3c");
        //when, then
        assertThatThrownBy(() -> knight.move(new ArrayList<>(), targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }
}
