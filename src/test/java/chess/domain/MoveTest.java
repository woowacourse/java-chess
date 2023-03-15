package chess.domain;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoveTest extends AbstractTestFixture {

    @DisplayName("상하 대칭 할 수 있다.")
    @Test
    void flipHorizontal() {
        Move move = createMove(RIGHT, UP, UP);
        Move move2 = createMove(RIGHT, DOWN, DOWN);

        assertThat(move.flipHorizontal()).isEqualTo(move2);
    }

    @DisplayName("좌우 대칭 할 수 있다.")
    @Test
    void flipVertical() {
        Move move = createMove(RIGHT, RIGHT, UP);
        Move move2 = createMove(LEFT, LEFT, UP);

        assertThat(move.flipVertical()).isEqualTo(move2);
    }

    @DisplayName("기울기가 같은지 알 수 있다")
    @Test
    void isSameAngle() {
        Move move = createMove(RIGHT, UP);
        Move move2 = createMove(RIGHT, RIGHT, UP, UP);

        assertThat(move.isSameAngle(move2)).isTrue();
    }

    @DisplayName("기울기가 다른지 알 수 있다")
    @Test
    void isNotSameAngle() {
        Move move = createMove(RIGHT, UP);
        Move move2 = createMove(RIGHT, RIGHT, UP);

        assertThat(move.isSameAngle(move2)).isFalse();
    }

    @SuppressWarnings("Convert2MethodRef")
    @DisplayName("빈 수 생성시 예외를 던진다")
    @Test
    void emptyMove_throws() {
        assertThatThrownBy(() -> createMove())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("방향이 존재해야합니다.");
    }

    @DisplayName("양방향이 존재하면 예외를 던진다")
    @Test
    void bidirectional_throws() {
        assertThatThrownBy(() -> createMove(RIGHT, LEFT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("양방향이 존재하면 안됩니다");
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
        Position destination = move.findDestination(createPosition("A,ONE"));

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
}
