package chess.domain.move;

import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.position.Position;

public class MoveTest extends AbstractTestFixture {

    @SuppressWarnings("Convert2MethodRef")
    @DisplayName("빈 수 생성시 예외를 던진다")
    @Test
    void emptyMove_throws() {
        assertThatThrownBy(() -> createMove())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("양방향이 존재하면 예외를 던진다")
    @Test
    void bidirectional_throws() {
        assertThatThrownBy(() -> createMove(RIGHT, LEFT))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 수인지 확인한다")
    @Test
    void isSameWith() {
        Move move1 = createMove(RIGHT, UP, RIGHT);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        assertThat(move1).isEqualTo(move2);
    }

    @DisplayName("다른 수인지 확인한다")
    @Test
    void isNotSameWith() {
        Move move1 = createMove(RIGHT, UP);
        Move move2 = createMove(RIGHT, RIGHT, UP, UP);

        assertThat(move1).isNotEqualTo(move2);
    }

    @DisplayName("목적지를 찾을 수 있다")
    @Test
    void findDestination() {
        Move move = createMove(RIGHT, RIGHT, UP, UP, UP);
        Position destination = move.move(createPosition("A,ONE"));

        assertThat(destination).isEqualTo(createPosition("C,FOUR"));
    }

    @DisplayName("단위 수를 찾을 수 있다")
    @Test
    void getUnitMove() {
        Move move = createMove(RIGHT, UP, RIGHT, UP, RIGHT, UP);
        Move unitMove = move.getUnitMove();

        assertThat(createMove(RIGHT, UP)).isEqualTo(unitMove);
    }

    @DisplayName("n번 반복할 수 있다")
    @Test
    void repeat_nTimes() {
        Move expectedMove = createMove(RIGHT, RIGHT, UP, RIGHT, RIGHT, UP, RIGHT, RIGHT, UP);
        assertThat(createMove(RIGHT, RIGHT, UP).repeat(3)).isEqualTo(expectedMove);
    }

    @DisplayName("시작위치, 도착위치로 수를 만들 수 있다")
    @Test
    void createMoveFrom_sourceAndTargetPositions() {
        Position source = createPosition("G,ONE");
        Position target = createPosition("E,TWO");

        assertThat(Move.of(source, target)).isEqualTo(createMove(LEFT, LEFT, UP));
    }
}
