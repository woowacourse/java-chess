package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("File과 Rank를 받아서 Position을 생성한다.")
    void generatePosition() {
        // when, then
        Assertions.assertDoesNotThrow(() -> new Position(File.A, Rank.ONE));
    }

    //TODO : MethodSource로 구현
    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - UP")
    void getDirectionTo_Up() {
        // given
        final Position position = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.A, Rank.FIVE);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.UP);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - UP_RIGHT")
    void getDirectionTo_Up_Right() {
        // given
        final Position position = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.B, Rank.TWO);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.UP_RIGHT);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - RIGHT")
    void getDirectionTo_Right() {
        // given
        final Position position = new Position(File.A, Rank.ONE);
        final Position targetPosition = new Position(File.B, Rank.ONE);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.RIGHT);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - DOWN_RIGHT")
    void getDirectionTo_Down_Right() {
        // given
        final Position position = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.C, Rank.ONE);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.DOWN_RIGHT);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - DOWN")
    void getDirectionTo_Down() {
        // given
        final Position position = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.B, Rank.ONE);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.DOWN);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - DOWN_LEFT")
    void getDirectionTo_Down_Left() {
        // given
        final Position position = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.A, Rank.ONE);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.DOWN_LEFT);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - LEFT")
    void getDirectionTo_Left() {
        // given
        final Position position = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.A, Rank.TWO);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.LEFT);
    }

    @Test
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다. - UP_LEFT")
    void getDirectionTo_Up_Left() {
        // given
        final Position position = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.A, Rank.THREE);

        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(Direction.UP_LEFT);
    }

    @Test
    @DisplayName("대상 Position을 받아서 현재 Position과의 Slope를 구한다. - 0.5")
    void getSlope_Zero_Five() {
        // given
        final Position position = new Position(File.C, Rank.TWO);
        final Position targetPosition = new Position(File.D, Rank.FOUR);

        // when, then
        assertThat(position.getSlope(targetPosition)).isEqualTo(0.5);
    }

    @Test
    @DisplayName("대상 Position을 받아서 현재 Position과의 Slope를 구한다. - 2.0")
    void getSlope_Two() {
        // given
        final Position position = new Position(File.D, Rank.FOUR);
        final Position targetPosition = new Position(File.B, Rank.THREE);

        // when, then
        assertThat(position.getSlope(targetPosition)).isEqualTo(2.0);
    }

    @Test
    @DisplayName("이동할 횟수를 반환한다. - 수평")
    void getMoveCount_Horizontal() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FOUR);
        final Position targetPosition = new Position(File.F, Rank.FOUR);
        final Direction rightDirection = Direction.RIGHT;

        // when
        int moveCount = sourcePosition.getMoveCount(targetPosition, rightDirection);

        // then
        assertThat(moveCount).isEqualTo(2);
    }

    @Test
    @DisplayName("이동할 횟수를 반환한다. - 수직")
    void getMoveCount_Vertical() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FOUR);
        final Position targetPosition = new Position(File.D, Rank.TWO);
        final Direction rightDirection = Direction.DOWN;

        // when
        int moveCount = sourcePosition.getMoveCount(targetPosition, rightDirection);

        // then
        assertThat(moveCount).isEqualTo(2);
    }

    @Test
    @DisplayName("이동할 횟수를 반환한다. - 대각선")
    void getMoveCount_Diagonal() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FOUR);
        final Position targetPosition = new Position(File.F, Rank.SIX);
        final Direction rightDirection = Direction.UP_RIGHT;

        // when
        int moveCount = sourcePosition.getMoveCount(targetPosition, rightDirection);

        // then
        assertThat(moveCount).isEqualTo(2);
    }
}
