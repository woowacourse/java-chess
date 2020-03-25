package chess.domain.position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoveDirectionTest {
    @Test
    void isSameDirection_SourcePosition_SamePositionResult() {
        Position sourcePosition = Position.of("d4");
        Position targetPosition = Position.of("d6");

        assertThat(MoveDirection.N.isSameDirection(sourcePosition, targetPosition)).isTrue();
    }

    @Test
    void move_SourcePosition_MovedPosition() {
        Position sourcePosition = Position.of("d4");

        assertThat(MoveDirection.N.move(sourcePosition)).isEqualTo(Position.of("d5"));
        assertThat(MoveDirection.NE.move(sourcePosition)).isEqualTo(Position.of("e5"));
        assertThat(MoveDirection.E.move(sourcePosition)).isEqualTo(Position.of("e4"));
        assertThat(MoveDirection.SE.move(sourcePosition)).isEqualTo(Position.of("e3"));
        assertThat(MoveDirection.S.move(sourcePosition)).isEqualTo(Position.of("d3"));
        assertThat(MoveDirection.SW.move(sourcePosition)).isEqualTo(Position.of("c3"));
        assertThat(MoveDirection.W.move(sourcePosition)).isEqualTo(Position.of("c4"));
        assertThat(MoveDirection.NW.move(sourcePosition)).isEqualTo(Position.of("c5"));
    }
}